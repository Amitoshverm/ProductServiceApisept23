package com.amitosh.productservice.Service;

import com.amitosh.productservice.Model.Category;
import com.amitosh.productservice.Repository.CategoryRepository;
import com.amitosh.productservice.dtos.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto ConvertCategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(categoryDto.getName());
        return categoryDto;
    }
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(ConvertCategoryToCategoryDto(category));
        }
        return categoryDtos;
    }
}
