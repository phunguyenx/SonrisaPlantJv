package com.example.sonrisaplantjv.domain.model.Category;

import java.util.UUID;

public class Category {
    public UUID Id;
    public String Name;
    public String IdUser;


    public Category() {

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

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }
}
