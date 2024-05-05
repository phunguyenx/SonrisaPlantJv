package com.example.sonrisaplantjv.data.remote.api;

import com.example.sonrisaplantjv.domain.dto.ResponseBoolean;
import com.example.sonrisaplantjv.domain.dto.ResponseUUID;
import com.example.sonrisaplantjv.domain.dto.User.Authenticate;
import com.example.sonrisaplantjv.domain.dto.User.ChangePassword;
import com.example.sonrisaplantjv.domain.dto.User.ChangePinCode;
import com.example.sonrisaplantjv.domain.dto.User.ForgotPassword;
import com.example.sonrisaplantjv.domain.dto.User.LoginResponse;
import com.example.sonrisaplantjv.domain.dto.User.Register;
import com.example.sonrisaplantjv.domain.dto.User.UpdateUser;
import com.example.sonrisaplantjv.domain.dto.User.UserDto;
import com.example.sonrisaplantjv.domain.dto.User.ValidatePin;
import com.example.sonrisaplantjv.domain.dto.User.ValidateToken;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface IUserService {
    @POST("user/authenticate")
    Call<LoginResponse> authenticate(@Body Authenticate authenticate);
    @POST("user/create")
    Call<ResponseUUID> register(@Body Register register);
    @GET("user/get-by-id")
    Call<UserDto> getById(UUID id);
    @Multipart
    @POST("user/update")
    Call<ResponseUUID> update(@Body UpdateUser updateUser);
    @POST("user/forgot-password")
    Call<ResponseBoolean> forgotPassword(ForgotPassword forgotPassword);
    @POST("user/change-password")
    Call<ResponseBoolean> changePassword(ChangePassword changePassword);
    @POST("user/change-pin")
    Call<ResponseBoolean> changePin(ChangePinCode changePinCode);
    @POST("user/create-pin")
    Call<ResponseBoolean> createPin(ValidatePin validatePin);
    @POST("user/validate-pin")
    Call<ResponseBoolean> validatePin(ValidatePin validatePin);
    @POST("user/refresh-token")
    Call<LoginResponse> refreshToken(ValidateToken validateToken);
    @POST("user/revoke-token")
    Call<ResponseBoolean> revokeToken(String email);
}
