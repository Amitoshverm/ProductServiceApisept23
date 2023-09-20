package com.amitosh.productservice;

import com.amitosh.productservice.Model.Category;
import com.amitosh.productservice.Model.Product;
import com.amitosh.productservice.Repository.CategoryRepository;
import com.amitosh.productservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductServiceApplication(CategoryRepository categoryRepository,
                                     ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Category category = new Category();
        category.setName("Apple Product");
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setTitle("iPhone 15");
        product.setDescription("This product is launching in sept23");
        product.setPrice(75000);
        product.setCategory(savedCategory);
        productRepository.save(product);
    }
}
