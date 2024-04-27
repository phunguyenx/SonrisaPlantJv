package com.example.sonrisaplantjv.data.remote.dto.CartItem;

import com.example.sonrisaplantjv.common.ActionResponse;

import java.util.UUID;

public class AddItemCart extends ActionResponse<AddItemCart> {
    public UUID IdPlant;
    public int Quantity;

}
