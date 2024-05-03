package com.example.sonrisaplantjv.domain.usecase.user;

public interface CallBackResponse {
    void onSuccess();
    void onFailure(String errorMessage);
}
