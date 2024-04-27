package com.example.sonrisaplantjv.data.remote.api;

import com.example.sonrisaplantjv.data.remote.dto.User.Authenticate;
import com.example.sonrisaplantjv.data.remote.dto.User.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("authenticate")
    Call<LoginResponse> authenticate(@Body Authenticate authenticate);
}
