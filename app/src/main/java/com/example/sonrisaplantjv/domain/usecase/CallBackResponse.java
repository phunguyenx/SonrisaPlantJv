package com.example.sonrisaplantjv.domain.usecase;

public interface CallBackResponse {
    void onSuccess();
    void onFailure(String errorMessage);
}
