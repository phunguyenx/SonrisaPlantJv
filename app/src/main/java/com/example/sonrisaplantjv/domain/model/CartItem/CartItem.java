package com.example.sonrisaplantjv.domain.model.CartItem;

import java.util.UUID;

public class CartItem{
    public UUID Id;
    public UUID IdPlant;
    public int Quantity;
    public UUID IdUser;

    public CartItem() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
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

    public UUID getIdUser() {
        return IdUser;
    }

    public void setIdUser(UUID idUser) {
        IdUser = idUser;
    }
}
