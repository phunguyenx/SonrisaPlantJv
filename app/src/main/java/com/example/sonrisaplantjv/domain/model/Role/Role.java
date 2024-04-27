package com.example.sonrisaplantjv.domain.model.Role;

import java.util.UUID;

public class Role{
    public UUID Id;
    public String Name;
    public String ConcurrencyStamp;

    public Role() {
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

    public String getConcurrencyStamp() {
        return ConcurrencyStamp;
    }

    public void setConcurrencyStamp(String concurrencyStamp) {
        ConcurrencyStamp = concurrencyStamp;
    }
}
