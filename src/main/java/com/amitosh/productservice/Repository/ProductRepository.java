package com.amitosh.productservice.Repository;

import com.amitosh.productservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
