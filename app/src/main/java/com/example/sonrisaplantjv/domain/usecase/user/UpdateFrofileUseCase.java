package com.example.sonrisaplantjv.domain.usecase.user;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.domain.dto.ResponseUUID;
import com.example.sonrisaplantjv.domain.dto.User.Register;
import com.example.sonrisaplantjv.domain.dto.User.UpdateUser;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateFrofileUseCase {
    private final UserRepository userRepository;
    private final Context context;
    private final Application application;
    private MutableLiveData<ResponseUUID> updateUserReponse = new MutableLiveData<>();
    public UpdateFrofileUseCase(UserRepository userRepository, Application application) {
        this.userRepository = userRepository;
        this.context = application.getApplicationContext();
        this.application = application;
    }
    public void execute(UpdateUser input, CallBackResponse callback){

        if (input == null){
            callback.onFailure("Invalid input");
            return;
        }

        Call<ResponseUUID> call= userRepository.Update(input);
        call.enqueue(new Callback<ResponseUUID>() {
            @Override
            public void onResponse(Call<ResponseUUID> call, Response<ResponseUUID> response) {
                try{
                    if (response.body() == null){
                        Constant.showMessageError(response, context);
                        return;
                    }
                    updateUserReponse.setValue(response.body());
                    if (!updateUserReponse.getValue().isSuccess()){
                        callback.onFailure(updateUserReponse.getValue().getMessage());
                        return;
                    }
                    callback.onSuccess();
                }catch (Exception e){
                    Log.e("update_profile_use_case"," - > Error    "+ e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseUUID> call, Throwable t) {
                Log.d("update_profile_Call"," - > Error    "+ t.getMessage());
            }
        });
    }
}
