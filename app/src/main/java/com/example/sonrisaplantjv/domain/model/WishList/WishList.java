package com.example.sonrisaplantjv.domain.model.WishList;

import com.example.sonrisaplantjv.domain.model.ShippingAddress.AddressStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class WishList {
    public UUID IdUser;
    public UUID IdPlant;

    public WishList() {
    }

    public UUID getIdUser() {
        return IdUser;
    }

    public void setIdUser(UUID idUser) {
        IdUser = idUser;
    }

    public UUID getIdPlant() {
        return IdPlant;
    }

    public void setIdPlant(UUID idPlant) {
        IdPlant = idPlant;
    }
}
