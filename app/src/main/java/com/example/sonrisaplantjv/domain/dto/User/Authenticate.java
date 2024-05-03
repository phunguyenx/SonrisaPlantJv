package com.example.sonrisaplantjv.domain.dto.User;

import androidx.databinding.Bindable;

public class Authenticate {
    private String Email;
    private String Password;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}


