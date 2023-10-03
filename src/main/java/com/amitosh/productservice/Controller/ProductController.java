package com.amitosh.productservice.Controller;

import com.amitosh.productservice.Service.ProductService;
import com.amitosh.productservice.dtos.GenericProductDto;
import com.amitosh.productservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    // ....;
    // ...;



    // constructor injection
//    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto,
                                               @PathVariable UUID id) throws NotFoundException {
        return this.productService.updateProduct(genericProductDto, id);
    }
}
