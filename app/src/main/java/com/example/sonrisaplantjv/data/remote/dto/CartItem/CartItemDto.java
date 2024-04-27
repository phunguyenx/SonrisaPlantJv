package com.example.sonrisaplantjv.data.remote.dto.CartItem;

import com.example.sonrisaplantjv.common.ActionResponse;
import com.example.sonrisaplantjv.data.remote.dto.Category.PlantImage;

public class CartItemDto extends ActionResponse<CartItemDto> {
    public int Quantity;
    public PlantImage PlantImage;
}
