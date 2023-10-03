package com.amitosh.productservice.Service;

import com.amitosh.productservice.dtos.GenericProductDto;
import com.amitosh.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    GenericProductDto getProductById(UUID id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProduct(UUID id) throws NotFoundException;
    GenericProductDto updateProduct(GenericProductDto genericProductDto, UUID id) throws NotFoundException;
    List<GenericProductDto> getAllProductsWithCategory(UUID category_id) throws NotFoundException;

}
