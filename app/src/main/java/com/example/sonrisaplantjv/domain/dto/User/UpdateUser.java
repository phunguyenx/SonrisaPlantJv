package com.example.sonrisaplantjv.domain.dto.User;

import androidx.annotation.Nullable;

import java.io.File;
import java.time.LocalDateTime;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

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

    public UpdateUser() {
    }
}
