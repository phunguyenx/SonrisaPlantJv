package com.example.sonrisaplantjv.presentation.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.data.repositoryImpl.UserRepositoryImpl;
import com.example.sonrisaplantjv.domain.dto.User.UpdateUser;
import com.example.sonrisaplantjv.domain.dto.User.UpdateUserRequest;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;
import com.example.sonrisaplantjv.domain.usecase.CallBackResponse;
import com.example.sonrisaplantjv.domain.usecase.user.UpdateFrofileUseCase;
import com.example.sonrisaplantjv.domain.utils.UpdateUserStatus;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FillFrofileViewModel extends AndroidViewModel {
    private final UpdateFrofileUseCase updateFrofileUseCase;
    private UpdateUser updateUser = new UpdateUser();
    private UpdateUserRequest updateUserRequest = new UpdateUserRequest();
    private Context context;
    private MutableLiveData<Boolean> showDialogStatus = new MutableLiveData<>();

    private MutableLiveData<Integer> updateUserStatus = new MutableLiveData<>();

    public FillFrofileViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        UserRepository userRepository = new UserRepositoryImpl();
        updateFrofileUseCase = new UpdateFrofileUseCase(userRepository, application);
    }
    public void clickContinue(){
        try {
            showDialogStatus.setValue(true);
            updateUserRequest.Name = RequestBody.create(MediaType.parse("multipart/form-data"), updateUser.Name == null? "": updateUser.Name);
            updateUserRequest.PhoneNumber = RequestBody.create(MediaType.parse("multipart/form-data"), updateUser.PhoneNumber == null? "": updateUser.PhoneNumber);
            updateUserRequest.Address = RequestBody.create(MediaType.parse("multipart/form-data"), updateUser.Address == null? "": updateUser.Address);
            updateUserRequest.Avatar = updateUser.Avatar == null? null: updateUser.Avatar;
            updateFrofileUseCase.execute(updateUserRequest, new CallBackResponse() {
                @Override
                public void onSuccess() {
                    updateUserStatus.setValue(UpdateUserStatus.Success);
                    showDialogStatus.setValue(false);
                }
                @Override
                public void onFailure(String errorMessage) {
                    showDialogStatus.setValue(false);
                    updateUserStatus.setValue(UpdateUserStatus.Fails);
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            showDialogStatus.setValue(false);
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

    public MutableLiveData<Boolean> getShowDialogStatus() {
        return showDialogStatus;
    }

    public void setShowDialogStatus(MutableLiveData<Boolean> showDialogStatus) {
        this.showDialogStatus = showDialogStatus;
    }
}
