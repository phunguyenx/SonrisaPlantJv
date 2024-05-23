package com.example.sonrisaplantjv.domain.dto.Plant;

import com.example.sonrisaplantjv.common.ActionResponse;

import java.math.BigDecimal;
import java.util.UUID;

public class PlantDto{
    public UUID id;
    public String name;
    public BigDecimal price;
    public String description;
    public String image;
    public BigDecimal ratingPoint;
    public int quantitySold;
    public boolean isLove;
}
