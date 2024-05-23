package com.example.sonrisaplantjv.domain.usecase.Plant;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.domain.dto.Plant.ListOfPlant;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantDto;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantRequestParameters;
import com.example.sonrisaplantjv.domain.model.Plant.Plant;
import com.example.sonrisaplantjv.domain.repository.Plant.PlantRepository;
import com.example.sonrisaplantjv.domain.usecase.CallBackResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllPlantPagingUseCase {
    private final PlantRepository plantRepository;
    private final Application application;
    private List<PlantDto> plants;
    public MutableLiveData<List<PlantDto>> listPlant;
    public GetAllPlantPagingUseCase(PlantRepository plantRepository, Application application) {
        this.plantRepository = plantRepository;
        this.application = application;
    }
    public MutableLiveData<List<PlantDto>> execute(PlantRequestParameters request){
        listPlant = new MutableLiveData<>();
        Call<ListOfPlant> call = plantRepository.getAllPaging(request);
        call.enqueue(new Callback<ListOfPlant>() {
            @Override
            public void onResponse(Call<ListOfPlant> call, Response<ListOfPlant> response) {
                try{
                    if (response.body() == null){
                        Constant.showMessageError(response, application.getApplicationContext());
                        return;
                    }
                    ListOfPlant list = response.body();
                    if (!list.isSuccess()){

//                        callback.onFailure(list.getMessage());
                    }else {
                        listPlant.setValue(list.getData());
//                        callback.onSuccess();
                    }
                }catch (Exception e){
                    Log.e("get all plant paging use case"," - > Error    "+ e.getMessage());
//                    callback.onFailure(Constant.MSG_FAIL);
                }
            }

            @Override
            public void onFailure(Call<ListOfPlant> call, Throwable t) {
                Log.d("get-plant_Call"," - > Error    "+ t.getMessage());
//                callback.onFailure(Constant.MSG_FAIL);
            }
        });
        return listPlant;
    }
}
