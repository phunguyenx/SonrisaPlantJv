package com.example.sonrisaplantjv.data.remote.api;

import com.example.sonrisaplantjv.domain.dto.Plant.ListOfPlant;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantDto;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantRequestParameters;
import com.example.sonrisaplantjv.domain.dto.ResponseUUID;
import com.example.sonrisaplantjv.domain.dto.User.UserDto;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IPlantService {
    @POST("plant/getall-paging")
    Call<ListOfPlant> getAllPaging(@Body PlantRequestParameters request);
    @POST("wishlist/add-to-wish-list")
    Call<ResponseUUID> addToWishList(@Body UUID idPlant);
    @DELETE("wishlist/remove-from-wish-list/{id}")
    Call<ResponseUUID> removeFromWishList(@Path("id") UUID idPlant);
}
