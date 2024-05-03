package com.example.sonrisaplantjv.domain.dto.CartItem;

import com.example.sonrisaplantjv.common.ActionResponse;
import com.example.sonrisaplantjv.domain.dto.Category.PlantImage;

public class CartItemDto extends ActionResponse<CartItemDto> {
    public int Quantity;
    public PlantImage PlantImage;
}
