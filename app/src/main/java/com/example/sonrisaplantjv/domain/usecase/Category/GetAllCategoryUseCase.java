package com.example.sonrisaplantjv.domain.usecase.Category;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.domain.dto.Category.CategoryDto;
import com.example.sonrisaplantjv.domain.dto.Category.ListOfCategory;
import com.example.sonrisaplantjv.domain.dto.Plant.ListOfPlant;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantDto;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantRequestParameters;
import com.example.sonrisaplantjv.domain.repository.category.CategoryRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllCategoryUseCase {
    private final CategoryRepository categoryRepository;
    private final Application application;
    private List<CategoryDto> categoryDtos;
    public MutableLiveData<List<CategoryDto>> listCategory ;

    public GetAllCategoryUseCase(CategoryRepository categoryRepository, Application application) {
        this.categoryRepository = categoryRepository;
        this.application = application;
    }

    public MutableLiveData<List<CategoryDto>> execute() {
        listCategory = new MutableLiveData<>();
        Call<ListOfCategory> call = categoryRepository.getAll();
        call.enqueue(new Callback<ListOfCategory>() {
            @Override
            public void onResponse(Call<ListOfCategory> call, Response<ListOfCategory> response) {
                try{
                    if (response.body() == null){
                        Constant.showMessageError(response, application.getApplicationContext());
                        return;
                    }
                    ListOfCategory list = response.body();
                    if (!list.isSuccess()){
//                        callback.onFailure(list.getMessage());
                    }else {
                        listCategory.setValue(list.getData());
//                        callback.onSuccess();
                    }
                }catch (Exception e){
                    Log.e("get all category paging use case"," - > Error    "+ e.getMessage());
//                    callback.onFailure(Constant.MSG_FAIL);
                }
            }

            @Override
            public void onFailure(Call<ListOfCategory> call, Throwable t) {
                Log.d("get-category_call"," - > Error    "+ t.getMessage());
            }
        });
        return listCategory;
    }
}
