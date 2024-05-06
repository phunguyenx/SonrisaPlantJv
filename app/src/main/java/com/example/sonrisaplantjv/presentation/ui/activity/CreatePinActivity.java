package com.example.sonrisaplantjv.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.databinding.ActivityCreatePinBinding;
import com.example.sonrisaplantjv.domain.dto.User.ValidatePin;
import com.example.sonrisaplantjv.domain.utils.CreatePinStatus;
import com.example.sonrisaplantjv.domain.utils.UpdateUserStatus;
import com.example.sonrisaplantjv.presentation.ui.components.ProgressDialog;
import com.example.sonrisaplantjv.presentation.viewmodels.CreatePinViewModel;
import com.example.sonrisaplantjv.presentation.viewmodels.FillFrofileViewModel;

public class CreatePinActivity extends AppCompatActivity {

    ActivityCreatePinBinding dataBinding;
    CreatePinViewModel createPinViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        createPinViewModel = new ViewModelProvider(this).get(CreatePinViewModel.class);

        dataBinding = DataBindingUtil.setContentView(CreatePinActivity.this, R.layout.activity_create_pin);

        dataBinding.setLifecycleOwner(this);

        dataBinding.setCreatePinViewModel(createPinViewModel);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_create_pin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        createPinStatus();
    }
    private void init(){
        dataBinding.toolBarCreatePin.tvToolbar.setText(R.string.create_new_pin);
        dataBinding.toolBarCreatePin.btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dataBinding.pvCreatePin.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        dataBinding.pvCreatePin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 4){
                    Toast.makeText(CreatePinActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                    createPinViewModel.getValidatePin().PIN = s.toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void createPinStatus() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        createPinViewModel.getShowDialogStatus().observe(this, s ->{
            if(s){
                progressDialog.show();
            }else {
                progressDialog.dismiss();
            }
        });
        createPinViewModel.getCreatePinStatus().observe(this, status -> {
            switch (status) {
                case CreatePinStatus.Success:
                    try {
                        Toast.makeText(CreatePinActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreatePinActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    }catch (Exception e){
                        Log.e("create_pin_Activity", e.getMessage());
                    }
                case CreatePinStatus.Fails:
                    break;
            }

        });
    }
}