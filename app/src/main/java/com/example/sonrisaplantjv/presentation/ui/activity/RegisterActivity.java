package com.example.sonrisaplantjv.presentation.ui.activity;

import android.content.Intent;
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
import com.example.sonrisaplantjv.databinding.ActivityRegisterBinding;
import com.example.sonrisaplantjv.domain.utils.RegisterStatus;
import com.example.sonrisaplantjv.presentation.viewmodels.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding dataBinding;
    private RegisterViewModel registerViewModel;

    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        dataBinding = DataBindingUtil.setContentView(RegisterActivity.this, R.layout.activity_register);

        dataBinding.setLifecycleOwner(this);

        dataBinding.setRegisterViewModel(registerViewModel);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        registerStatus();

    }
    private void init(){
        dataBinding.toolBarRegister.tvToolbar.setText("");
        dataBinding.toolBarRegister.btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataBinding.tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        dataBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dataBinding.edtPasswordRegister.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.remove_red_eye_24, 0);
        dataBinding.edtPasswordRegister.setOnTouchListener(new View.OnTouchListener() {
            final int DRAWABLE_RIGHT = 2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float x = event.getRawX();
                    float y = (dataBinding.edtPasswordRegister.getRight() - dataBinding.edtPasswordRegister.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width());
                    if (event.getRawX() >= (dataBinding.edtPasswordRegister.getRight() - 30 - dataBinding.edtPasswordRegister.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
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
            dataBinding.edtPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            dataBinding.edtPasswordRegister.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.remove_red_eye_24, 0);
        } else {
            // Hiển thị mật khẩu
            dataBinding.edtPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            dataBinding.edtPasswordRegister.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidden_24, 0);
        }
        isPasswordVisible = !isPasswordVisible;
    }

    private void registerStatus() {
        registerViewModel.getRegisterStatus().observe(this, status -> {
            switch (status) {
                case RegisterStatus.registerSuccess:
                    try {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.putExtra("is_signup_success", true);
                        if(dataBinding.cbRememberMeRegister.isChecked()){
                            intent.putExtra("email_register", registerViewModel.getRegister().Email);
                            intent.putExtra("password_register", registerViewModel.getRegister().Password);
                        }
                        startActivity(intent);
                        break;
                    }catch (Exception e){
                        Log.e("Register_Activity", e.getMessage());
                    }

                case RegisterStatus.emptyEmail:
                    //thong bao Toast message hoac hien thi loi Email trong
                    dataBinding.edtEmailRegister.setError(getString(R.string.emptyemail));
                    dataBinding.edtEmailRegister.requestFocus();
                    break;
                case RegisterStatus.emptyPassWord:
                    //tuong tu cho password
                    dataBinding.edtPasswordRegister.setError(getString(R.string.emptypassword));
                    dataBinding.edtPasswordRegister.requestFocus();
                    break;
                case RegisterStatus.isEmail:
                    dataBinding.edtEmailRegister.setError(getString(R.string.emailformat));
                    dataBinding.edtPasswordRegister.requestFocus();
                    break;
                case RegisterStatus.registerFails:
                    break;
            }

        });
    }
}