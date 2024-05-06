package com.example.sonrisaplantjv.domain.usecase.user;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.domain.dto.ResponseBoolean;
import com.example.sonrisaplantjv.domain.dto.User.LoginResponse;
import com.example.sonrisaplantjv.domain.dto.User.ValidatePin;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePinUseCase {
    private final UserRepository userRepository;
    private final Context context;
    private final Application application;

    private MutableLiveData<ResponseBoolean> booleanResponse = new MutableLiveData<>();

    public CreatePinUseCase(UserRepository userRepository, Application application) {
        this.userRepository = userRepository;
        this.context = application.getApplicationContext();
        this.application = application;
    }
    public void execute(ValidatePin input, CallBackResponse callback){
        if (input == null){
            callback.onFailure("Invalid input");
            return;
        }
        Call<ResponseBoolean> call= userRepository.CreatePin(input);
        call.enqueue(new Callback<ResponseBoolean>() {
            @Override
            public void onResponse(Call<ResponseBoolean> call, Response<ResponseBoolean> response) {
                try{
                    if (response.body() == null){
                        Constant.showMessageError(response, context);
                        return;
                    }
                    booleanResponse.setValue(response.body());
                    if (!booleanResponse.getValue().isSuccess()){
                        callback.onFailure(booleanResponse.getValue().getMessage());
                        return;
                    }
                    callback.onSuccess();
                }catch (Exception e){
                    Log.e("create_pin_use_case"," - > Error    "+ e.getMessage());
                    callback.onFailure(Constant.MSG_FAIL);
                }
            }

            @Override
            public void onFailure(Call<ResponseBoolean> call, Throwable t) {
                Log.d("create_pin_Call"," - > Error    "+ t.getMessage());
                callback.onFailure(Constant.MSG_FAIL);
            }
        });

    }
}
