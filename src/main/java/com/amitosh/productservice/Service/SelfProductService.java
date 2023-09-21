package com.amitosh.productservice.Service;

import com.amitosh.productservice.Model.Category;
import com.amitosh.productservice.Model.Price;
import com.amitosh.productservice.Model.Product;
import com.amitosh.productservice.Repository.ProductRepository;
import com.amitosh.productservice.dtos.GenericProductDto;
import com.amitosh.productservice.exceptions.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Primary
@Service("selfProductService")
public class SelfProductService implements productService {

    private ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public GenericProductDto convertProductToGenericProduct(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setCategory(String.valueOf(product.getCategory()));
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        return genericProductDto;
    }
    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
       Product product = this.productRepository.findById(id).get();
       GenericProductDto genericProductDto = convertProductToGenericProduct(product);
       return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = new Product();

        Category category = new Category();
        category.setName(genericProductDto.getCategory());

        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());

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
        Product product = this.productRepository.findById(id).get();
        this.productRepository.deleteById(id);
        return convertProductToGenericProduct(product);
    }

    @Override
    public GenericProductDto update(GenericProductDto genericProductDto, UUID id) {
        Product product = new Product();
        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        Category category = new Category();
        category.setName(genericProductDto.getCategory());

        product.setImage(genericProductDto.getImage());
        product.setTitle(genericProductDto.getTitle());
        product.setPrice(price);
        product.setDescription(genericProductDto.getDescription());
        product.setCategory(category);

        this.productRepository.save(product);
        return convertProductToGenericProduct(product);
    }
}