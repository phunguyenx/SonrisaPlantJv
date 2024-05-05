package com.example.sonrisaplantjv.domain.usecase.user;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.domain.dto.User.Authenticate;
import com.example.sonrisaplantjv.domain.dto.User.LoginResponse;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUseCase {
    private final UserRepository userRepository;
    private final Context context;
    private final Application application;

    private MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();

    public LoginUseCase(UserRepository userRepository, Context context, Application application) {
        this.userRepository = userRepository;
        this.context = context;
        this.application = application;
    }
    public void execute(Authenticate input, CallBackResponse callback){
        if (input == null){
            callback.onFailure("Invalid input");
            return;
        }
        Call<LoginResponse> call= userRepository.Login(input);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try{
                    if (response.body() == null){
                        Constant.showMessageError(response, context);
                        return;
                    }
                    loginResponse.setValue(response.body());
                    if (!loginResponse.getValue().isSuccess()){
                        callback.onFailure(loginResponse.getValue().getMessage());
                        return;
                    }
                    SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE);
                    String accessToken = "Bearer " + loginResponse.getValue().getData().accessToken;
                    String refreshToken = loginResponse.getValue().getData().refreshToken;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constant.SP_ACCESS_TOKEN, accessToken);
                    editor.putString(Constant.SP_REFRESH_TOKEN, refreshToken);
                    editor.apply();
                    callback.onSuccess();
                }catch (Exception e){
                    Log.e("Login_use_case"," - > Error    "+ e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("Login_Call"," - > Error    "+ t.getMessage());
            }
        });
    }
}
