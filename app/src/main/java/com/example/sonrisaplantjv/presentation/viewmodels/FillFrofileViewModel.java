package com.example.sonrisaplantjv.presentation.viewmodels;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.data.repositoryImpl.UserRepositoryImpl;
import com.example.sonrisaplantjv.domain.dto.User.Register;
import com.example.sonrisaplantjv.domain.dto.User.UpdateUser;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;
import com.example.sonrisaplantjv.domain.usecase.user.CallBackResponse;
import com.example.sonrisaplantjv.domain.usecase.user.RegisterUseCase;
import com.example.sonrisaplantjv.domain.usecase.user.UpdateFrofileUseCase;
import com.example.sonrisaplantjv.domain.utils.RegisterStatus;

public class FillFrofileViewModel extends AndroidViewModel {
    private final UpdateFrofileUseCase updateFrofileUseCase;
    private UpdateUser updateUser = new UpdateUser();
    private Context context;

    private MutableLiveData<Integer> updateUserStatus = new MutableLiveData<>();

    public FillFrofileViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        UserRepository userRepository = new UserRepositoryImpl();
        updateFrofileUseCase = new UpdateFrofileUseCase(userRepository, application);
    }
    public void clickContinue(){
        try {
            updateFrofileUseCase.execute(updateUser, new CallBackResponse() {
                @Override
                public void onSuccess() {
                    updateUserStatus.setValue(RegisterStatus.registerSuccess);
                }
                @Override
                public void onFailure(String errorMessage) {
                    updateUserStatus.setValue(RegisterStatus.registerFails);
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            Log.e("fill profile viewmodel",ex.getMessage());
        }
    }



    public UpdateUser getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(UpdateUser updateUser) {
        this.updateUser = updateUser;
    }

    public MutableLiveData<Integer> getUpdateUserStatus() {
        return updateUserStatus;
    }

    public void setUpdateUserStatus(MutableLiveData<Integer> updateUserStatus) {
        this.updateUserStatus = updateUserStatus;
    }
}
