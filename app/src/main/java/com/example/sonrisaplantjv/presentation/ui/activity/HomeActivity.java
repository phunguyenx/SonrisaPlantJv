package com.example.sonrisaplantjv.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.databinding.ActivityHomeBinding;
import com.example.sonrisaplantjv.databinding.ActivityLoginBinding;
import com.example.sonrisaplantjv.presentation.viewmodels.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding dataBinding;
    private HomeViewModel homeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        dataBinding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home);

        dataBinding.setLifecycleOwner(this);
        dataBinding.setHomeViewModel(homeViewModel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dataBinding.toolBar.btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String accessToken = sharedPreferences.getString("access_token", null);
            String refreshToken = sharedPreferences.getString("refresh_token", null);
            if (accessToken != null) {
                // Token đã tồn tại, người dùng đã đăng nhập trước đó
                // Bạn có thể chuyển họ đến màn hình chính hoặc bất kỳ màn hình nào bạn muốn
                // Ví dụ: Chuyển đến MainActivity
                dataBinding.tvAccessToken.setText(accessToken);
                dataBinding.tvRefreshToken.setText(refreshToken);
            } else {
                // Token chưa tồn tại, người dùng chưa đăng nhập trước đó
                // Hiển thị màn hình đăng nhập
                // Ví dụ: Chuyển đến LoginActivity
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish(); // Đóng activity hiện tại
            }
        }catch (Exception e){
            Log.e("Home", e.getMessage());
        }

    }
}