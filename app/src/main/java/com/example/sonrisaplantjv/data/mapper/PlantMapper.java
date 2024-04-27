package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.data.remote.dto.Plant.PlantDto;
import com.example.sonrisaplantjv.domain.model.Plant.Plant;

public class PlantMapper implements Mapper<PlantDto, Plant>{
    @Override
    public Plant Map(PlantDto plantDto) {
        Plant plant = new Plant();
        plant.Id = plantDto.Id;
        plant.Name = plantDto.Name;
        plant.Price = plantDto.Price;
        plant.Description = plantDto.Description;
        plant.Image = plantDto.Image;
        plant.RatingPoint = plantDto.RatingPoint;
        return plant;
    }
}
