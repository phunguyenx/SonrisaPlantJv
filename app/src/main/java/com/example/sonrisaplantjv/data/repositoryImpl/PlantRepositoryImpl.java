package com.example.sonrisaplantjv.data.repositoryImpl;

import com.example.sonrisaplantjv.common.RetrofitInstance;
import com.example.sonrisaplantjv.data.remote.api.IPlantService;
import com.example.sonrisaplantjv.domain.dto.Plant.ListOfPlant;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantRequestParameters;
import com.example.sonrisaplantjv.domain.dto.ResponseUUID;
import com.example.sonrisaplantjv.domain.repository.Plant.PlantRepository;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;

public class PlantRepositoryImpl implements PlantRepository {
    @Override
    public Call<ListOfPlant> getAllPaging(PlantRequestParameters request) {
        return RetrofitInstance.getRetrofitInstance().create(IPlantService.class).getAllPaging(request);
    }

    @Override
    public Call<ResponseUUID> addToWishList(UUID idPlant) {
        return RetrofitInstance.getRetrofitInstance().create(IPlantService.class).addToWishList(idPlant);
    }

    @Override
    public Call<ListOfPlant> getAllPlantWishlist(PlantRequestParameters request) {
        return RetrofitInstance.getRetrofitInstance().create(IPlantService.class).getAllPlantWishlist(request);
    }

    @Override
    public Call<ResponseUUID> removeFromWishList(UUID idPlant) {
        return RetrofitInstance.getRetrofitInstance().create(IPlantService.class).removeFromWishList(idPlant);
    }
}
