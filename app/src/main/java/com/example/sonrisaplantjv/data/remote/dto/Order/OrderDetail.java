package com.example.sonrisaplantjv.data.remote.dto.Order;

import androidx.annotation.Nullable;

import com.example.sonrisaplantjv.common.ActionResponse;
import com.example.sonrisaplantjv.data.remote.dto.Promo.PromoDto;
import com.example.sonrisaplantjv.data.remote.dto.ShippingAddress.ShippingAddressDto;
import com.example.sonrisaplantjv.domain.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

public class OrderDetail extends ActionResponse<OrderDetail>
{
    public UUID Id;
    public OrderStatus Status;
    public BigDecimal TotalAmount;
    public Collection<OrderItemDto> OrderItems;
    public ShippingAddressDto ShippingAddress;
    @Nullable
    public PromoDto Promo;
}
