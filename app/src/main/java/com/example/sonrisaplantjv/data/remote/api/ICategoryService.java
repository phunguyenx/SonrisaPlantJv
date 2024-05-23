package com.example.sonrisaplantjv.data.remote.api;

import com.example.sonrisaplantjv.domain.dto.Category.ListOfCategory;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICategoryService {
    @GET("category/getAll")
    Call<ListOfCategory> getAll();
}
