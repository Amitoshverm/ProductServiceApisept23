package com.amitosh.productservice.Service;

import com.amitosh.productservice.Model.Category;
import com.amitosh.productservice.Model.Price;
import com.amitosh.productservice.Model.Product;
import com.amitosh.productservice.Repository.CategoryRepository;
import com.amitosh.productservice.Repository.ProductRepository;
import com.amitosh.productservice.dtos.CategoryDto;
import com.amitosh.productservice.dtos.GenericProductDto;
import com.amitosh.productservice.exceptions.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public GenericProductDto convertProductToGenericProduct(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setPrice(product.getPrice().getPrice());
        return genericProductDto;
    }
    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
       Product product = this.productRepository.findById(id).get();
        return convertProductToGenericProduct(product);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = new Product();

        Category category = categoryRepository.findDistinctByName(genericProductDto.getCategory());
        category.setName(genericProductDto.getCategory());

        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        price.setCurrency("Rupees");

        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(genericProductDto.getDescription());
        product.setId(genericProductDto.getId());
        product.setTitle(genericProductDto.getTitle());
        product.setImage(genericProductDto.getImage());

        Product savedProduct = this.productRepository.save(product);
        return convertProductToGenericProduct(savedProduct);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (Product product : products) {
            genericProductDtos.add(convertProductToGenericProduct(product));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) throws NotFoundException {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("product with id " + id + " not found");
        }
        this.productRepository.deleteById(id);
        return convertProductToGenericProduct(product.get());
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto, UUID id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("product with id "+ id +" not found");
        }

        Product updatedProduct = new Product();
        Category category = new Category();
        category.setName(genericProductDto.getCategory());
        updatedProduct.setCategory(category);
        updatedProduct.setTitle(genericProductDto.getTitle());
        updatedProduct.setDescription(genericProductDto.getDescription());
        updatedProduct.setImage(genericProductDto.getImage());
        Price price = new Price("Rupees", genericProductDto.getPrice());
        updatedProduct.setPrice(price);
        Product product1 = productRepository.save(updatedProduct);
        return convertProductToGenericProduct(product1);
    }

    @Override
    public List<GenericProductDto> getAllProductsWithCategory(UUID category_id) throws NotFoundException {
        List<GenericProductDto> productDtos = new ArrayList<>();
        Optional<Category> category = categoryRepository.findById(category_id);
        if (category.isEmpty()) {
            throw new NotFoundException("category with id "+ category_id +" not found");
        }
        List<Product> products = productRepository.findAllByCategoryEquals(category.get());
        for (Product product : products) {
            productDtos.add(convertProductToGenericProduct(product));
        }
        return productDtos;
    }
}