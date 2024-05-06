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
import com.example.sonrisaplantjv.domain.dto.User.ValidatePin;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;
import com.example.sonrisaplantjv.domain.usecase.user.CallBackResponse;
import com.example.sonrisaplantjv.domain.usecase.user.CreatePinUseCase;
import com.example.sonrisaplantjv.domain.usecase.user.UpdateFrofileUseCase;
import com.example.sonrisaplantjv.domain.utils.CreatePinStatus;
import com.example.sonrisaplantjv.domain.utils.UpdateUserStatus;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CreatePinViewModel extends AndroidViewModel {
    private final CreatePinUseCase createPinUseCase;
    private ValidatePin validatePin = new ValidatePin();
    private Context context;
    private MutableLiveData<Integer> createPinStatus = new MutableLiveData<>();
    private MutableLiveData<Boolean> showDialogStatus = new MutableLiveData<>();

    public CreatePinViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        UserRepository userRepository = new UserRepositoryImpl();
        createPinUseCase = new CreatePinUseCase(userRepository, application);
    }
    public void clickContinue(){
        try {
            if (validatePin.PIN == null){
                Toast.makeText(context, "Input to continue", Toast.LENGTH_SHORT).show();
                return;
            }
            if(validatePin.PIN.length() != 4){
                Toast.makeText(context, "Lack Of Input", Toast.LENGTH_SHORT).show();
                return;
            }
            showDialogStatus.setValue(true);

            createPinUseCase.execute(validatePin, new CallBackResponse() {
                @Override
                public void onSuccess() {
                    createPinStatus.setValue(CreatePinStatus.Success);
                    showDialogStatus.setValue(false);

                }
                @Override
                public void onFailure(String errorMessage) {
                    showDialogStatus.setValue(false);
                    createPinStatus.setValue(CreatePinStatus.Fails);
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception ex) {
            showDialogStatus.setValue(false);
            Log.e("create pin viewmodel",ex.getMessage());
        }
    }

    public ValidatePin getValidatePin() {
        return validatePin;
    }

    public void setValidatePin(ValidatePin validatePin) {
        this.validatePin = validatePin;
    }

    public MutableLiveData<Integer> getCreatePinStatus() {
        return createPinStatus;
    }

    public void setCreatePinStatus(MutableLiveData<Integer> createPinStatus) {
        this.createPinStatus = createPinStatus;
    }

    public MutableLiveData<Boolean> getShowDialogStatus() {
        return showDialogStatus;
    }

    public void setShowDialogStatus(MutableLiveData<Boolean> showDialogStatus) {
        this.showDialogStatus = showDialogStatus;
    }
}
