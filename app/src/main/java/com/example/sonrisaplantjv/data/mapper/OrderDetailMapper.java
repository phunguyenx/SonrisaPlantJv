package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.data.remote.dto.Order.OrderDetail;
import com.example.sonrisaplantjv.domain.model.Order.Order;

public class OrderDetailMapper implements Mapper<OrderDetail, Order> {
    @Override
    public Order Map(OrderDetail orderDetail) {
        Order order = new Order();
        order.Id = orderDetail.Id;
        order.TotalAmount = orderDetail.TotalAmount;
        order.Status = orderDetail.Status;
        order.IdShippingAddress = orderDetail.ShippingAddress.Id;
        if (orderDetail.Promo != null) {
            order.IdPromo = orderDetail.Promo.Id;
        }
        return order;
    }
}
