package com.example.sonrisaplantjv.data.remote.dto.Plant;

import com.example.sonrisaplantjv.common.ActionResponse;

import java.math.BigDecimal;
import java.util.UUID;

public class PlantDto extends ActionResponse<PlantDto> {
    public UUID Id;
    public String Name;
    public BigDecimal Price;
    public String Description;
    public String Image;
    public BigDecimal RatingPoint;

}
