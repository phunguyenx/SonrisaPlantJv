package com.example.sonrisaplantjv.presentation.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.data.CurrentContext;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_splash), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences(Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE);
                    String accessToken = sharedPreferences.getString(Constant.SP_ACCESS_TOKEN, null);
                    String refreshToken = sharedPreferences.getString(Constant.SP_REFRESH_TOKEN, null);
                    if (accessToken != null ) {
                        boolean isFirstLogin = sharedPreferences.getBoolean(Constant.SP_FIRST_LOGIN, true);
                        Intent intent;
                        if (isFirstLogin){
                            intent = new Intent(SplashActivity.this, FillProfileActivity.class);
                        }else {
                            intent = new Intent(SplashActivity.this, HomeActivity.class);
                        }
                        startActivity(intent);
                        CurrentContext.AccessToken = accessToken;
                        CurrentContext.RefreshToken = refreshToken;
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }catch (Exception e){
                    Log.e("Splash", e.getMessage());
                }
            }
        }, 3000);
    }
}