package com.example.sonrisaplantjv.data.repositoryImpl;

import com.example.sonrisaplantjv.common.RetrofitInstance;
import com.example.sonrisaplantjv.data.remote.api.ICategoryService;
import com.example.sonrisaplantjv.data.remote.api.IPlantService;
import com.example.sonrisaplantjv.domain.dto.Category.ListOfCategory;
import com.example.sonrisaplantjv.domain.repository.category.CategoryRepository;

import retrofit2.Call;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Call<ListOfCategory> getAll() {
        return RetrofitInstance.getRetrofitInstance().create(ICategoryService.class).getAll();
    }
}
