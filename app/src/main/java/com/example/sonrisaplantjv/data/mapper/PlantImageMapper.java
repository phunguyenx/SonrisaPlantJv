package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.data.remote.dto.Category.PlantImage;
import com.example.sonrisaplantjv.domain.model.Plant.Plant;

public class PlantImageMapper implements Mapper<PlantImage, Plant>{
    @Override
    public Plant Map(PlantImage plantImage) {
        Plant plant = new Plant();
        plant.Id = plantImage.Id;
        plant.Name = plantImage.Name;
        plant.Price = plantImage.Price;
        plant.Image = plantImage.Image;
        plant.RatingPoint = plantImage.RatingPoint;
        return plant;
    }
}
