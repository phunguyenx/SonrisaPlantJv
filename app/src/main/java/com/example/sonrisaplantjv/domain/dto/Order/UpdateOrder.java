package com.example.sonrisaplantjv.domain.dto.Order;

import androidx.annotation.Nullable;

import com.example.sonrisaplantjv.domain.model.Order.OrderStatus;

import java.util.UUID;

public class UpdateOrder
{
    @Nullable
    public UUID IdShippingAddress;
    public OrderStatus Status;
}
