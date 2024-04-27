package com.example.sonrisaplantjv.data.remote.dto.Order;

import androidx.annotation.Nullable;

import com.example.sonrisaplantjv.common.ActionResponse;
import com.example.sonrisaplantjv.domain.model.Order.Order;
import com.example.sonrisaplantjv.domain.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public class OrderDto extends ActionResponse<OrderDto> {
    public UUID Id;
    public BigDecimal TotalAmount;
    public OrderStatus Status;
    public LocalDateTime CreatedAt;
    @Nullable
    public Collection<OrderItemDto> OrderItems;

    public OrderDto() {
    }
}

