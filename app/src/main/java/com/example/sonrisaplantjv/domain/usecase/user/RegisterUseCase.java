package com.example.sonrisaplantjv.domain.usecase.user;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.domain.dto.ResponseUUID;
import com.example.sonrisaplantjv.domain.dto.User.Register;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;
import com.example.sonrisaplantjv.domain.usecase.CallBackResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUseCase {
    private final UserRepository userRepository;
    private final Context context;
    private final Application application;

    private MutableLiveData<ResponseUUID> registerReponse = new MutableLiveData<>();

    public RegisterUseCase(UserRepository userRepository, Context context, Application application) {
        this.userRepository = userRepository;
        this.context = context;
        this.application = application;
    }
    public void execute(Register input, CallBackResponse callback){

        if (input == null){
            callback.onFailure("Invalid input");
            return;
        }

        Call<ResponseUUID> call= userRepository.Register(input);
        call.enqueue(new Callback<ResponseUUID>() {
            @Override
            public void onResponse(Call<ResponseUUID> call, Response<ResponseUUID> response) {
                try{
                    if (response.body() == null){
                        Constant.showMessageError(response, context);
                        return;
                    }
                    registerReponse.setValue(response.body());
                    if (!registerReponse.getValue().isSuccess()){
                        callback.onFailure(registerReponse.getValue().getMessage());
                        return;
                    }
                    callback.onSuccess();
                }catch (Exception e){
                    Log.e("Register_use_case"," - > Error    "+ e.getMessage());
                    callback.onFailure(Constant.MSG_FAIL);
                }
            }

            @Override
            public void onFailure(Call<ResponseUUID> call, Throwable t) {
                Log.d("Register_Call"," - > Error    "+ t.getMessage());
                callback.onFailure(Constant.MSG_FAIL);
            }
        });
    }
}
