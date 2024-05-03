package com.example.sonrisaplantjv.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.databinding.ActivityLoginBinding;
import com.example.sonrisaplantjv.domain.utils.LoginStatus;
import com.example.sonrisaplantjv.presentation.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding dataBinding;
    private LoginViewModel loginViewModel;

    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        dataBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        dataBinding.setLifecycleOwner(this);

        dataBinding.setLoginViewModel(loginViewModel);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        loginstatus();
    }

    private void init(){
        dataBinding.toolBar.tvToolbar.setText("");

        Intent intent = getIntent();
        if (intent.getBooleanExtra("is_signup_success", false)){
            Toast.makeText(this, "Sign up success", Toast.LENGTH_SHORT).show();
        }
        if(intent.getStringExtra("email_register") != null && intent.getStringExtra("password_register")!= null){
            dataBinding.edtEmailLogin.setBackground(getDrawable(R.drawable.round_corner_color1));
            loginViewModel.getAuthenticate().setEmail(intent.getStringExtra("email_register"));

            dataBinding.edtPasswordLogin.setBackground(getDrawable(R.drawable.round_corner_color1));
            loginViewModel.getAuthenticate().setPassword(intent.getStringExtra("password_register"));
        }
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isLogout = sharedPreferences.getBoolean("isLogout", false);
        String email = sharedPreferences.getString("email", null);
        String password = sharedPreferences.getString("password", null);
        if (email != null && password != null && isLogout){
            dataBinding.edtEmailLogin.setBackground(getDrawable(R.drawable.round_corner_color1));
            loginViewModel.getAuthenticate().setEmail(intent.getStringExtra("email_register"));

            dataBinding.edtPasswordLogin.setBackground(getDrawable(R.drawable.round_corner_color1));
            loginViewModel.getAuthenticate().setPassword(intent.getStringExtra("password_register"));
        }
        dataBinding.toolBar.btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dataBinding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        dataBinding.edtPasswordLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.remove_red_eye_24, 0);
        dataBinding.edtPasswordLogin.setOnTouchListener(new View.OnTouchListener() {
            final int DRAWABLE_RIGHT = 2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float x = event.getRawX();
                    float y = (dataBinding.edtPasswordLogin.getRight() - dataBinding.edtPasswordLogin.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width());
                    if (event.getRawX() >= (dataBinding.edtPasswordLogin.getRight() - 30 - dataBinding.edtPasswordLogin.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
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
            dataBinding.edtPasswordLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            dataBinding.edtPasswordLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.remove_red_eye_24, 0);
        } else {
            // Hiển thị mật khẩu
            dataBinding.edtPasswordLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            dataBinding.edtPasswordLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidden_24, 0);
        }
        isPasswordVisible = !isPasswordVisible;
    }
    private void loginstatus() {
        loginViewModel.getLoginStatus().observe(this, status -> {
            switch (status) {
                case LoginStatus.loginSuccess:
                    try {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        String email = loginViewModel.getAuthenticate().getEmail();
                        String password = loginViewModel.getAuthenticate().getPassword();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", email);
                        editor.putString("password", password);
                        editor.apply();
                        startActivity(intent);
                        break;
                    }catch (Exception e){
                        Log.e("LoginActivity", e.getMessage());
                    }

                case LoginStatus.emptyEmail:
                    //thong bao Toast message hoac hien thi loi Email trong
                    dataBinding.edtEmailLogin.setError(getString(R.string.emptyemail));
                    dataBinding.edtEmailLogin.requestFocus();
                    break;
                case LoginStatus.emptyPassWord:
                    //tuong tu cho password
                    dataBinding.edtPasswordLogin.setError(getString(R.string.emptypassword));
                    dataBinding.edtPasswordLogin.requestFocus();
                    break;
                case LoginStatus.isEmail:
                    dataBinding.edtEmailLogin.setError(getString(R.string.emailformat));
                    dataBinding.edtPasswordLogin.requestFocus();
                    break;
                case LoginStatus.loginFails:

                    break;
            }

        });
    }
}