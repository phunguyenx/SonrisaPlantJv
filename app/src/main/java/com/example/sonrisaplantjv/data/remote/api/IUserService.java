package com.example.sonrisaplantjv.data.remote.api;

import androidx.annotation.Nullable;

import com.example.sonrisaplantjv.domain.dto.ResponseBoolean;
import com.example.sonrisaplantjv.domain.dto.ResponseUUID;
import com.example.sonrisaplantjv.domain.dto.User.Authenticate;
import com.example.sonrisaplantjv.domain.dto.User.ChangePassword;
import com.example.sonrisaplantjv.domain.dto.User.ChangePinCode;
import com.example.sonrisaplantjv.domain.dto.User.ForgotPassword;
import com.example.sonrisaplantjv.domain.dto.User.LoginResponse;
import com.example.sonrisaplantjv.domain.dto.User.Register;
import com.example.sonrisaplantjv.domain.dto.User.UpdateUser;
import com.example.sonrisaplantjv.domain.dto.User.UpdateUserRequest;
import com.example.sonrisaplantjv.domain.dto.User.UserDto;
import com.example.sonrisaplantjv.domain.dto.User.ValidatePin;
import com.example.sonrisaplantjv.domain.dto.User.ValidateToken;

import java.util.UUID;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IUserService {
    @POST("user/authenticate")
    Call<LoginResponse> authenticate(@Body Authenticate authenticate);
    @POST("user/create")
    Call<ResponseUUID> register(@Body Register register);
    @GET("user/get-by-id")
    Call<UserDto> getById(UUID id);
    @Multipart
    @POST("user/update")
    Call<ResponseUUID> update(@Part("name") @Nullable RequestBody name,
                              @Part("phone") @Nullable RequestBody phone,
                              @Part("address") @Nullable RequestBody address,
                              @Part @Nullable MultipartBody.Part avatar);
    @POST("user/forgot-password")
    Call<ResponseBoolean> forgotPassword(@Body ForgotPassword forgotPassword);
    @POST("user/change-password")
    Call<ResponseBoolean> changePassword(@Body ChangePassword changePassword);
    @POST("user/change-pin")
    Call<ResponseBoolean> changePin(@Body ChangePinCode changePinCode);
    @POST("user/create-pin")
    Call<ResponseBoolean> createPin(@Body ValidatePin validatePin);
    @POST("user/validate-pin")
    Call<ResponseBoolean> validatePin(@Body ValidatePin validatePin);
    @POST("user/refresh-token")
    Call<LoginResponse> refreshToken(@Body ValidateToken validateToken);
    @POST("user/revoke-token")
    Call<ResponseBoolean> revokeToken(@Body String email);
}
