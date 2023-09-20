package com.amitosh.productservice.Repository;

import com.amitosh.productservice.dtos.GenericProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericProductRepository extends JpaRepository<GenericProductDto, Long> {
}
