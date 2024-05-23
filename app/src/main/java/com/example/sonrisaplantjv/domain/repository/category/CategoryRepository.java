package com.example.sonrisaplantjv.domain.repository.category;

import com.example.sonrisaplantjv.domain.dto.Category.ListOfCategory;
import com.example.sonrisaplantjv.domain.dto.Plant.ListOfPlant;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantRequestParameters;

import retrofit2.Call;

public interface CategoryRepository {
    public Call<ListOfCategory> getAll();
}
