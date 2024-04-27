package com.example.sonrisaplantjv.domain.model.Order;

import java.util.UUID;

public class OrderItem {
    public UUID IdOrder;
    public UUID IdPlant;
    public int Quantity;

    public OrderItem() {
    }

    public UUID getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(UUID idOrder) {
        IdOrder = idOrder;
    }

    public UUID getIdPlant() {
        return IdPlant;
    }

    public void setIdPlant(UUID idPlant) {
        IdPlant = idPlant;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
