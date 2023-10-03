package com.amitosh.productservice.Repository;

import com.amitosh.productservice.Model.Category;
import com.amitosh.productservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByCategoryEquals(Category category);

}
