package com.example.sonrisaplantjv.domain.dto.Order;

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
