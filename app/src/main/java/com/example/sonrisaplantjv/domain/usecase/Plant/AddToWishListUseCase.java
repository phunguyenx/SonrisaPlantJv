package com.example.sonrisaplantjv.domain.usecase.Plant;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.domain.dto.ResponseUUID;
import com.example.sonrisaplantjv.domain.repository.Plant.PlantRepository;
import com.example.sonrisaplantjv.domain.usecase.CallBackResponse;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToWishListUseCase {
    private final PlantRepository plantRepository;
    private final Application application;
    private MutableLiveData<ResponseUUID> responseResult = new MutableLiveData<>();

    public AddToWishListUseCase(PlantRepository plantRepository, Application application) {
        this.plantRepository = plantRepository;
        this.application = application;
    }
    public void execute(UUID request, CallBackResponse callback){
        Call<ResponseUUID> call = plantRepository.addToWishList(request);
        call.enqueue(new Callback<ResponseUUID>() {
            @Override
            public void onResponse(Call<ResponseUUID> call, Response<ResponseUUID> response) {
                try{
                    if (response.body() == null){
                        Constant.showMessageError(response, application.getApplicationContext());
                        return;
                    }
                    responseResult.setValue(response.body());
                    if (!responseResult.getValue().isSuccess()){
                        callback.onFailure(responseResult.getValue().getMessage());
                        return;
                    }
                    callback.onSuccess();
                }catch (Exception e){
                    Log.e("add_wishlist_use_case"," - > Error    "+ e.getMessage());
                    callback.onFailure(Constant.MSG_FAIL);
                }
            }

            @Override
            public void onFailure(Call<ResponseUUID> call, Throwable t) {
                Log.d("add_wishlist_Call"," - > Error    "+ t.getMessage());
                callback.onFailure(Constant.MSG_FAIL);
            }
        });
    }
}
