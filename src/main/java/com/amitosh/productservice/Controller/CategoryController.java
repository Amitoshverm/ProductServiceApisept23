package com.amitosh.productservice.Controller;

import com.amitosh.productservice.Service.CategoryService;
import com.amitosh.productservice.dtos.CategoryDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }
}
