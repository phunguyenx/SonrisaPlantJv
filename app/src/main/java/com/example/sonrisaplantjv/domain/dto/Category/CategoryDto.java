package com.example.sonrisaplantjv.domain.dto.Category;

import com.example.sonrisaplantjv.common.ActionResponse;

import java.util.Collection;
import java.util.UUID;

public class CategoryDto extends ActionResponse<CategoryDto> {
    public UUID Id;
    public String Name;
    public Collection<PlantImage> Plants;
}
