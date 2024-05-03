package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.domain.dto.CartItem.CartItemDto;
import com.example.sonrisaplantjv.domain.model.CartItem.CartItem;

public class CartItemMapper implements Mapper<CartItemDto, CartItem> {
    @Override
    public CartItem Map(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.Quantity = cartItemDto.Quantity;
        cartItem.IdPlant = cartItemDto.PlantImage.Id;
        return cartItem;
    }
}
