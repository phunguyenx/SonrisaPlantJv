package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.domain.dto.Order.OrderDto;
import com.example.sonrisaplantjv.domain.model.Order.Order;

public class OrderMapper implements Mapper<OrderDto, Order>{
    @Override
    public Order Map(OrderDto orderDto) {
        Order order = new Order();
        order.Id = orderDto.Id;
        order.TotalAmount = orderDto.TotalAmount;
        order.Status = orderDto.Status;
        order.CreatedAt = orderDto.CreatedAt;
        return order;
    }
}
