package com.example.sonrisaplantjv.domain.repository.Plant;


import com.example.sonrisaplantjv.domain.dto.Plant.ListOfPlant;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantDto;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantRequestParameters;
import com.example.sonrisaplantjv.domain.dto.ResponseUUID;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;

public interface PlantRepository {
    public Call<ListOfPlant> getAllPaging(PlantRequestParameters request);
    public Call<ResponseUUID> addToWishList(UUID idPlant);
    public Call<ListOfPlant> getAllPlantWishlist(PlantRequestParameters request);
    public Call<ResponseUUID> removeFromWishList(UUID idPlant);
}
