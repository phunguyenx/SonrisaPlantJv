package com.example.sonrisaplantjv.data.remote.dto.Order;

import java.util.List;
import java.util.UUID;

public class CreateOrder
{
    public UUID IdPromo;
    public UUID IdShippingAddress;
    public List<CreateOrderItemDto> CreateOrderItemDto;
}

class CreateOrderItemDto
{
    public UUID IdPlant;
    public int Quantity;
}
