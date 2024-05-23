package com.example.sonrisaplantjv.presentation.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.data.repositoryImpl.PlantRepositoryImpl;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantDto;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantRequestParameters;
import com.example.sonrisaplantjv.domain.usecase.CallBackResponse;
import com.example.sonrisaplantjv.domain.usecase.Plant.AddToWishListUseCase;
import com.example.sonrisaplantjv.domain.usecase.Plant.GetAllPlantPagingUseCase;
import com.example.sonrisaplantjv.domain.usecase.Plant.RemoveFromWishListUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlantHomeViewModel extends AndroidViewModel {
    private LiveData<Integer> PageSize;
    private LiveData<Integer> PageNumber;
    private final GetAllPlantPagingUseCase useCase;
    private LiveData<List<PlantDto>> plants;

    private final AddToWishListUseCase addToWishListUseCase;
    private MutableLiveData<Boolean> addToWishListResult = new MutableLiveData<>();
    private final RemoveFromWishListUseCase removeFromWishListUseCase;
    private MutableLiveData<Boolean> removeFromWishListResult = new MutableLiveData<>();
    public PlantHomeViewModel(@NonNull Application application) {
        super(application);
        useCase = new GetAllPlantPagingUseCase(new PlantRepositoryImpl(), application);
        addToWishListUseCase = new AddToWishListUseCase(new PlantRepositoryImpl(), application);
        removeFromWishListUseCase = new RemoveFromWishListUseCase(new PlantRepositoryImpl(), application);
        plants = new MutableLiveData<>(new ArrayList<PlantDto>());
        PageSize = new MutableLiveData<>(10);
        PageNumber = new MutableLiveData<>(0);

    }
    public LiveData<List<PlantDto>> getPlantPaging(PlantRequestParameters request) {
        return useCase.execute(request);
    }
    public void addToWishList(UUID request){
        addToWishListUseCase.execute(request, new CallBackResponse() {
            @Override
            public void onSuccess() {
                addToWishListResult.setValue(true);
                Toast.makeText(getApplication().getApplicationContext(), "Add to wishlist success", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String errorMessage) {
                addToWishListResult.setValue(false);
                Toast.makeText(getApplication().getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void removeFromWishList(UUID request){
        removeFromWishListUseCase.execute(request, new CallBackResponse() {
            @Override
            public void onSuccess() {
                removeFromWishListResult.setValue(true);
                Toast.makeText(getApplication().getApplicationContext(), "Remove from wishlist success", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String errorMessage) {
                removeFromWishListResult.setValue(false);
                Toast.makeText(getApplication().getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public LiveData<List<PlantDto>> getPlants() {
        return plants;
    }

    public void setPlants(LiveData<List<PlantDto>> plants) {
        this.plants = plants;
    }

    public LiveData<Integer> getPageSize() {
        return PageSize;
    }

    public void setPageSize(LiveData<Integer> pageSize) {
        PageSize = pageSize;
    }

    public LiveData<Integer> getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(LiveData<Integer> pageNumber) {
        PageNumber = pageNumber;
    }

    public MutableLiveData<Boolean> getAddToWishListResult() {
        return addToWishListResult;
    }

    public void setAddToWishListResult(MutableLiveData<Boolean> addToWishListResult) {
        this.addToWishListResult = addToWishListResult;
    }

    public MutableLiveData<Boolean> getRemoveFromWishListResult() {
        return removeFromWishListResult;
    }

    public void setRemoveFromWishListResult(MutableLiveData<Boolean> removeFromWishListResult) {
        this.removeFromWishListResult = removeFromWishListResult;
    }
}
