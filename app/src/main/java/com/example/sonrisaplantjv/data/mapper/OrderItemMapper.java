package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.domain.dto.Order.OrderItemDto;
import com.example.sonrisaplantjv.domain.model.Order.OrderItem;

public class OrderItemMapper implements Mapper<OrderItemDto, OrderItem> {
    @Override
    public OrderItem Map(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
         orderItem.Quantity = orderItemDto.Quantity;
         orderItem.IdPlant = orderItemDto.Plant.Id;
         return orderItem;
    }
}
