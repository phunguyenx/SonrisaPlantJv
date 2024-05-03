package com.example.sonrisaplantjv.presentation.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.data.repositoryImpl.UserRepositoryImpl;
import com.example.sonrisaplantjv.domain.dto.User.Register;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;
import com.example.sonrisaplantjv.domain.usecase.user.RegisterUseCase;
import com.example.sonrisaplantjv.domain.usecase.user.CallBackResponse;
import com.example.sonrisaplantjv.domain.utils.RegisterStatus;
import com.example.sonrisaplantjv.domain.utils.ValidatorUtils;

public class RegisterViewModel extends AndroidViewModel {
    private final RegisterUseCase registerUseCase;
    private Register register = new Register();
    private Context context;
    private MutableLiveData<Boolean> isRememberMe = new MutableLiveData<>(false);

    private MutableLiveData<Integer> registerStatus = new MutableLiveData<>();
    private MutableLiveData<Boolean> isPasswordVisible = new MutableLiveData<>(false);

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        UserRepository userRepository = new UserRepositoryImpl();
        registerUseCase = new RegisterUseCase(userRepository, context, application);
    }

    public void clickRegister(){
        try {
            if (validateData()) {
                registerUseCase.execute(register, new CallBackResponse() {
                    @Override
                    public void onSuccess() {
                        registerStatus.setValue(RegisterStatus.registerSuccess);

                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        registerStatus.setValue(RegisterStatus.registerFails);
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (Exception ex) {
            Log.e("register viewmodel",ex.getMessage());
        }
    }

    private boolean validateData() {
        try {
            register.ConfirmPassword = register.Password;
            if (ValidatorUtils.emptyValue(register.Email)) {
                registerStatus.setValue(RegisterStatus.emptyEmail);
                return false;
            } else if (ValidatorUtils.emptyValue(register.Password)) {
                registerStatus.setValue(RegisterStatus.emptyPassWord);
                return false;
            }else if (ValidatorUtils.emptyValue(register.ConfirmPassword)) {
                registerStatus.setValue(RegisterStatus.emptyPassWord);
                return false;
            }else if (!ValidatorUtils.isMatch(register.Password, register.ConfirmPassword)) {
                registerStatus.setValue(RegisterStatus.isMatch);
                return false;
            }
        } catch (Exception ex) {
            Log.e("Validate register viewmodel",ex.getMessage());
            return false;
        }
        return true;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
    public MutableLiveData<Integer> getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(MutableLiveData<Integer> registerStatus) {
        this.registerStatus = registerStatus;
    }

    public MutableLiveData<Boolean> getIsPasswordVisible() {
        return isPasswordVisible;
    }

    public void setIsPasswordVisible(MutableLiveData<Boolean> isPasswordVisible) {
        this.isPasswordVisible = isPasswordVisible;
    }

    public MutableLiveData<Boolean> getIsRememberMe() {
        return isRememberMe;
    }

    public void setIsRememberMe(MutableLiveData<Boolean> isRememberMe) {
        this.isRememberMe = isRememberMe;
    }
}
