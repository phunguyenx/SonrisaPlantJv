package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.domain.dto.Category.CategoryDto;
import com.example.sonrisaplantjv.domain.model.Category.Category;

public class CategoryMapper implements Mapper<CategoryDto, Category>{
    @Override
    public Category Map(CategoryDto categoryDto) {
        Category category = new Category();
        category.Id = categoryDto.Id;
        category.Name = category.Name;
        return category;
    }
}
