package com.example.sonrisaplantjv.domain.model.ShippingAddress;


import java.time.LocalDateTime;
import java.util.UUID;

public class ShippingAddress {
    public UUID Id;
    public String Name;
    public String Phone;
    public String Description;
    public LocalDateTime Time;
    public AddressStatus Status;
    public UUID IdUser;

    public ShippingAddress() {
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDateTime getTime() {
        return Time;
    }

    public void setTime(LocalDateTime time) {
        Time = time;
    }

    public AddressStatus getStatus() {
        return Status;
    }

    public void setStatus(AddressStatus status) {
        Status = status;
    }

    public UUID getIdUser() {
        return IdUser;
    }

    public void setIdUser(UUID idUser) {
        IdUser = idUser;
    }
}
