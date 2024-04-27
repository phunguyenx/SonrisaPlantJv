package com.example.sonrisaplantjv.domain.model.Promo;

import java.util.UUID;

public class Promo {
    public UUID Id;
    public String Name;
    public int ValueDiscount;
    public UUID IdUser;

    public Promo() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getValueDiscount() {
        return ValueDiscount;
    }

    public void setValueDiscount(int valueDiscount) {
        ValueDiscount = valueDiscount;
    }

    public UUID getIdUser() {
        return IdUser;
    }

    public void setIdUser(UUID idUser) {
        IdUser = idUser;
    }
}
