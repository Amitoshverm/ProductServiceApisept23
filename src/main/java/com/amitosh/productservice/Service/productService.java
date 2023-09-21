package com.amitosh.productservice.Service;

import com.amitosh.productservice.Model.Product;
import com.amitosh.productservice.dtos.FakeStoreProductDto;
import com.amitosh.productservice.dtos.GenericProductDto;
import com.amitosh.productservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface productService {

    GenericProductDto getProductById(UUID id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProduct(UUID id) throws NotFoundException;
    GenericProductDto update(GenericProductDto genericProductDto, UUID id);
}
