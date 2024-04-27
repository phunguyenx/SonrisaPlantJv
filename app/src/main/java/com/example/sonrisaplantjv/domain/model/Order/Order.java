package com.example.sonrisaplantjv.domain.model.Order;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    public UUID Id;
    public BigDecimal TotalAmount;
    public OrderStatus Status;
    public LocalDateTime CreatedAt;
    public UUID IdPromo;
    public UUID IdShippingAddress;
    public UUID IdUser;

    public Order() {
    }

    public UUID getIdOrder() {
        return Id;
    }

    public void setIdOrder(UUID idOrder) {
        Id = idOrder;
    }

    public BigDecimal getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        TotalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return Status;
    }

    public void setStatus(OrderStatus status) {
        Status = status;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }

    public UUID getIdPromo() {
        return IdPromo;
    }

    public void setIdPromo(UUID idPromo) {
        IdPromo = idPromo;
    }

    public UUID getIdShippingAddress() {
        return IdShippingAddress;
    }

    public void setIdShippingAddress(UUID idShippingAddress) {
        IdShippingAddress = idShippingAddress;
    }

    public UUID getIdUser() {
        return IdUser;
    }

    public void setIdUser(UUID idUser) {
        IdUser = idUser;
    }
}

