package com.example.sonrisaplantjv.domain.dto.User;

import androidx.annotation.Nullable;

import java.io.File;
import java.time.LocalDateTime;

public class UpdateUser {
    @Nullable
    public String Name;
    @Nullable
    public String PhoneNumber;
    @Nullable
    public LocalDateTime DateOfBirth;
    @Nullable
    public String Address;
    @Nullable
    public File Avatar;
}
