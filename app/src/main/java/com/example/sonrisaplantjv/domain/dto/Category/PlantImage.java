package com.example.sonrisaplantjv.domain.dto.Category;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

public class PlantImage
{
    public UUID Id;
    public String Name;
    public String Image;
    public BigDecimal Price;
    @Nullable
    public BigDecimal RatingPoint;
}
