package com.amitosh.productservice.Service;

import com.amitosh.productservice.Repository.GenericProductRepository;
import com.amitosh.productservice.Repository.ProductRepository;
import com.amitosh.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductService implements productService{

    private final GenericProductRepository genericProductRepository;

    public SelfProductService(GenericProductRepository genericProductRepository) {
        this.genericProductRepository = genericProductRepository;
    }


    @Override
    public GenericProductDto getProductById(Long id) {
        return this.genericProductRepository.findById(id).get();
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {

        GenericProductDto productDto = new GenericProductDto();
        productDto.setTitle(product.getTitle());;
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        GenericProductDto saveProduct = genericProductRepository.save(productDto);
        return saveProduct;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return this.genericProductRepository.findAll();
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        genericProductRepository.deleteById(id);
        return null;
        // unable to return the deleted product
    }

    @Override
    public GenericProductDto update(GenericProductDto genericProductDto, Long id) {
        return this.genericProductRepository.save(genericProductDto);
    }
}
