package com.example.sonrisaplantjv.presentation.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.data.remote.api.RetrofitInstance;
import com.example.sonrisaplantjv.data.remote.api.UserApi;
import com.example.sonrisaplantjv.data.remote.dto.User.Authenticate;
import com.example.sonrisaplantjv.data.remote.dto.User.LoginResponse;
import com.example.sonrisaplantjv.databinding.ActivityLoginBinding;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.toolBar.btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Authenticate authenticate = new Authenticate();
                authenticate.Email = binding.edtEmailLogin.getText().toString();
                authenticate.Password = binding.edtPasswordLogin.getText().toString();

                UserApi apiService = RetrofitInstance.getApiService();
                Call<LoginResponse> call = apiService.authenticate(authenticate);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        if(loginResponse != null){
                            Toast.makeText(LoginActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                        }else {
                            Constant.checkResponse(response, LoginActivity.this);
                        }


                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d("Login"," - > Error    "+ t.getMessage());
                    }
                });
            }
        });
        binding.edtPasswordLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.remove_red_eye_24, 0);
        binding.edtPasswordLogin.setOnTouchListener(new View.OnTouchListener() {
            final int DRAWABLE_RIGHT = 2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float x = event.getRawX();
                    float y = (binding.edtPasswordLogin.getRight() - binding.edtPasswordLogin.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width());
                    if (event.getRawX() >= (binding.edtPasswordLogin.getRight() - 30 - binding.edtPasswordLogin.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Xử lý sự kiện khi người dùng nhấn vào biểu tượng
                        togglePasswordVisibility();
                        return true;
                    }

                }
                return false;
            }
        });
    }
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Ẩn mật khẩu
            binding.edtPasswordLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            binding.edtPasswordLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.remove_red_eye_24, 0);
        } else {
            // Hiển thị mật khẩu
            binding.edtPasswordLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            binding.edtPasswordLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidden_24, 0);
        }
        isPasswordVisible = !isPasswordVisible;
    }
}