package com.example.sonrisaplantjv.domain.dto.Category;

import com.example.sonrisaplantjv.common.ActionResponse;

import java.util.Collection;
import java.util.UUID;

public class CategoryDto{
    public UUID id;
    public String name;
    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto() {
    }
}
