package com.amitosh.productservice.Repository;

import com.amitosh.productservice.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
