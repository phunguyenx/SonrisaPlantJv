package com.example.sonrisaplantjv.presentation.viewmodels;


import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sonrisaplantjv.data.repositoryImpl.UserRepositoryImpl;
import com.example.sonrisaplantjv.domain.dto.User.Authenticate;
import com.example.sonrisaplantjv.domain.repository.User.UserRepository;
import com.example.sonrisaplantjv.domain.usecase.user.CallBackResponse;
import com.example.sonrisaplantjv.domain.usecase.user.LoginUseCase;
import com.example.sonrisaplantjv.domain.utils.LoginStatus;
import com.example.sonrisaplantjv.domain.utils.ValidatorUtils;

public class LoginViewModel extends AndroidViewModel {
    private final LoginUseCase loginUseCase;
    private Authenticate authenticate = new Authenticate();
    private boolean rememberMe = false;
    private Context context;
    private MutableLiveData<Integer> loginStatus = new MutableLiveData<>(LoginStatus.loginFails);



    private MutableLiveData<Boolean> isPasswordVisible = new MutableLiveData<>(false);
    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        UserRepository userRepository = new UserRepositoryImpl();
        loginUseCase = new LoginUseCase(userRepository, context, application);
    }
    public void clickLogin(){
        try {
            if (validateData()) {
                loginUseCase.execute(authenticate, new CallBackResponse() {
                    @Override
                    public void onSuccess() {
                        loginStatus.setValue(LoginStatus.loginSuccess);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                        loginStatus.setValue(LoginStatus.loginFails);
                    }
                });
            }
        } catch (Exception ex) {
            Log.e("Login viewmodel",ex.getMessage());
        }
    }
    private boolean validateData() {
        try {
            if (ValidatorUtils.emptyValue(authenticate.getEmail())) {
                loginStatus.setValue(LoginStatus.emptyEmail);
                return false;
            } else if (ValidatorUtils.emptyValue(authenticate.getPassword())) {
                loginStatus.setValue(LoginStatus.emptyPassWord);
                return false;
            }else if (!ValidatorUtils.isEmail(authenticate.getEmail())) {
                loginStatus.setValue(LoginStatus.isEmail);
                return false;
            }
        } catch (Exception ex) {
            Log.e("Validate login viewmodel",ex.getMessage());
            return false;
        }
        return true;
    }
    public Authenticate getAuthenticate() {
        return authenticate;
    }

    public MutableLiveData<Integer> getLoginStatus() {
        return loginStatus;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
