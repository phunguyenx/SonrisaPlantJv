package com.example.sonrisaplantjv.data.repositoryImpl;

import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.common.RetrofitInstance;
import com.example.sonrisaplantjv.data.remote.api.IUserService;
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
import com.example.sonrisaplantjv.domain.model.User.User;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;

import java.util.Map;
import java.util.UUID;

import retrofit2.Call;

public class UserRepositoryImpl implements UserRepository {
    public UserRepositoryImpl() {
    }

    @Override
    public Call<UserDto> GetById(UUID id) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).getById(id);
    }

    @Override
    public Call<ResponseUUID> Update(UpdateUserRequest updateUser) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).update(updateUser.Name, updateUser.PhoneNumber, updateUser.Address, updateUser.Avatar);
    }

    @Override
    public Call<ResponseBoolean> ForgotPassword(ForgotPassword input) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).forgotPassword(input);
    }

    @Override
    public Call<ResponseBoolean> ChangePassword(ChangePassword input) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).changePassword(input);
    }

    @Override
    public Call<ResponseBoolean> ChangePinCode(ChangePinCode input) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).changePin(input);
    }

    @Override
    public Call<ResponseUUID> Register(Register input) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).register(input);
    }

    @Override
    public Call<ResponseBoolean> CreatePin(ValidatePin input) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).createPin(input);
    }

    @Override
    public Call<ResponseBoolean> ValidatePin(ValidatePin input) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).validatePin(input);
    }

    @Override
    public Call<LoginResponse> Login(Authenticate input) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).authenticate(input);
    }

    @Override
    public Call<LoginResponse> RefeshToken(ValidateToken input) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).refreshToken(input);
    }

    @Override
    public Call<ResponseBoolean> RevokeToken(String email) {
        return RetrofitInstance.getRetrofitInstance().create(IUserService.class).revokeToken(email);
    }

}
