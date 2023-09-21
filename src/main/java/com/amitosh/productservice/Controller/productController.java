package com.amitosh.productservice.Controller;

import com.amitosh.productservice.Model.Product;
import com.amitosh.productservice.Service.FakeProductServiceImpl;
import com.amitosh.productservice.Service.productService;
import com.amitosh.productservice.dtos.ExceptionDto;
import com.amitosh.productservice.dtos.GenericProductDto;
import com.amitosh.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class productController {

    private productService service;

    public productController(@Qualifier("selfProductService") productService service) {
        this.service = service;
    }


    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return List.of(
                new GenericProductDto(),new GenericProductDto()
        );
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return service.getProductById(id);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return new ResponseEntity<>(
                service.deleteProduct(id), HttpStatus.OK
        );
    }

    @PostMapping
    public String createProduct(@RequestBody GenericProductDto product) {
        return "created new product with name "+ product.getTitle();
    }

    @PutMapping
    public ResponseEntity<GenericProductDto> updateProductById(@RequestBody GenericProductDto productDto, @PathVariable UUID id) {
        return new ResponseEntity<>(service.update(productDto, id), HttpStatus.OK);
    }
}
