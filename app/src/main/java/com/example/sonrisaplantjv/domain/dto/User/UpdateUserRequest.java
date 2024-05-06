package com.example.sonrisaplantjv.domain.dto.User;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public class UpdateUserRequest {
    @Nullable
    public RequestBody Name;
    @Nullable
    public RequestBody PhoneNumber;
//    @Nullable
//    public LocalDateTime DateOfBirth;
    @Nullable
    public RequestBody Address;
    @Nullable
    public MultipartBody.Part Avatar;
}
