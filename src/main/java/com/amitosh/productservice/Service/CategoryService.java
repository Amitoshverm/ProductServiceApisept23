package com.amitosh.productservice.Service;

import com.amitosh.productservice.Model.Category;
import com.amitosh.productservice.dtos.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategory();
}
