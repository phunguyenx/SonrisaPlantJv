package com.example.sonrisaplantjv.presentation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.data.repositoryImpl.CategoryRepositoryImpl;
import com.example.sonrisaplantjv.domain.dto.Category.CategoryDto;
import com.example.sonrisaplantjv.domain.usecase.Category.GetAllCategoryUseCase;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private final GetAllCategoryUseCase useCase;
    private LiveData<List<CategoryDto>> categories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        useCase = new GetAllCategoryUseCase(new CategoryRepositoryImpl(), application);
        categories = new MutableLiveData<>(new ArrayList<>());
    }
    public LiveData<List<CategoryDto>> getAll() {
        return useCase.execute();
    }
}
