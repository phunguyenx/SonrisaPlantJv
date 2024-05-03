package com.example.sonrisaplantjv.presentation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class HomeViewModel extends AndroidViewModel {
    public String Accesstoken;
    public String Refreshtoken;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }
}
