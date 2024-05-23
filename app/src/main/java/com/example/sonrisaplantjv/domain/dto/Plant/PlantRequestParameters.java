package com.example.sonrisaplantjv.domain.dto.Plant;

import androidx.annotation.Nullable;

import com.example.sonrisaplantjv.domain.dto.PagingParameters;

import java.util.UUID;

public class PlantRequestParameters extends PagingParameters {
    @Nullable
    public int categoryName;
    public String Name = "";
    @Nullable
    public UUID IdCategory;
    public static class CategoryName{
        public static final int SpecialOffer = 0;
        public static final int MostPopular = 1;

    }
}
