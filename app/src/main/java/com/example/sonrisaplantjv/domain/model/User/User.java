package com.example.sonrisaplantjv.domain.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    public UUID Id;
    public String Name;
    public LocalDateTime DateOfBirth;
    public String Address;
    public String PIN;
    public String Avatar;
    public UserStatus Status;
    public String RefeshToken;
    public LocalDateTime RefreshTokenExpiryTime;
    public String UserName;
    public  String Email;
    public String PhoneNumber;
    public String PasswordHash;

    public User() {
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

    public LocalDateTime getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime DateOfBirth) {
        DateOfBirth = DateOfBirth;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public UserStatus getStatus() {
        return Status;
    }

    public void setStatus(UserStatus status) {
        Status = status;
    }

    public String getRefeshToken() {
        return RefeshToken;
    }

    public void setRefeshToken(String refeshToken) {
        RefeshToken = refeshToken;
    }

    public LocalDateTime getRefreshTokenExpiryTime() {
        return RefreshTokenExpiryTime;
    }

    public void setRefreshTokenExpiryTime(LocalDateTime refreshTokenExpiryTime) {
        RefreshTokenExpiryTime = refreshTokenExpiryTime;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
