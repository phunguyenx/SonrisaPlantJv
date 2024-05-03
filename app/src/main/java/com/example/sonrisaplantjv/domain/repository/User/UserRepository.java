package com.example.sonrisaplantjv.domain.repository.User;

import android.view.Window;

import androidx.lifecycle.MutableLiveData;

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
import com.example.sonrisaplantjv.domain.model.User.User;

import java.util.UUID;

import javax.security.auth.callback.Callback;

import retrofit2.Call;

public interface UserRepository {
    public Call<UserDto> GetById(UUID id);
    public Call<ResponseUUID> Update(UpdateUser updateUser);
    public Call<ResponseBoolean> ForgotPassword(ForgotPassword input);
    public Call<ResponseBoolean> ChangePassword(ChangePassword input);
    public Call<ResponseBoolean> ChangePinCode(ChangePinCode input);
    public Call<ResponseUUID> Register(Register input);
    public Call<ResponseBoolean> CreatePin(ValidatePin input);
    public Call<ResponseBoolean> ValidatePin(ValidatePin input);
    public Call<LoginResponse> Login(Authenticate input);
    public Call<LoginResponse> RefeshToken(ValidateToken input);
    public Call<ResponseBoolean> RevokeToken(String email);
}
