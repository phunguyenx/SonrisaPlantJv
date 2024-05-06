package com.example.sonrisaplantjv.presentation.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.databinding.ActivityFillProfileBinding;
import com.example.sonrisaplantjv.databinding.ActivityRegisterBinding;
import com.example.sonrisaplantjv.domain.utils.FileHelper;
import com.example.sonrisaplantjv.domain.utils.RealPathUtil;
import com.example.sonrisaplantjv.domain.utils.RegisterStatus;
import com.example.sonrisaplantjv.domain.utils.UpdateUserStatus;
import com.example.sonrisaplantjv.presentation.viewmodels.FillFrofileViewModel;
import com.example.sonrisaplantjv.presentation.viewmodels.RegisterViewModel;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FillProfileActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 100;
    ActivityFillProfileBinding dataBinding;
    private FillFrofileViewModel fillFrofileViewModel;
    private Uri mUri;

    private final ActivityResultLauncher<Intent> mIntentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == Activity.RESULT_OK){
                        try {
                        Intent data = o.getData();
                        if (data == null) return;
                        Uri uri = data.getData();
                        mUri = uri;
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            dataBinding.imFillAvatar.setImageBitmap(bitmap);
                            if(mUri != null){
//                                String strRealPath = getRealPathFromURI(uri);
                                File file = new File(FileHelper.getRealPathFromURI(FillProfileActivity.this, uri));
                                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                                MultipartBody.Part muPart = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);
                                fillFrofileViewModel.getUpdateUser().Avatar = muPart;
                            }
                        }catch (Exception e){
                            Log.e("upload image fill", e.getMessage());
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        fillFrofileViewModel = new ViewModelProvider(this).get(FillFrofileViewModel.class);

        dataBinding = DataBindingUtil.setContentView(FillProfileActivity.this, R.layout.activity_fill_profile);

        dataBinding.setLifecycleOwner(this);

        dataBinding.setFillProfileViewModel(fillFrofileViewModel);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_fill_profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        init();
        updateUserStatus();
    }

    private void init(){
        dataBinding.toolBarFillProfile.tvToolbar.setText(R.string.fill_your_profile);
        dataBinding.toolBarFillProfile.btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataBinding.btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUploadImage();
            }
        });
    }
    public void clickUploadImage(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }

        if (Build.VERSION.SDK_INT >= 33){
            if(checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else {
                requestPermission(Manifest.permission.READ_MEDIA_IMAGES, REQUEST_CODE_GALLERY);
            }
        }else {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else {
                requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_CODE_GALLERY);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mIntentActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
    }

    private String getRealPathFromURI(Uri contentURI)
    {
        String result = null;
        try {
            Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);

            if (cursor == null)
            { // Source is Dropbox or other similar local file path
                result = contentURI.getPath();
            }
            else
            {
                if(cursor.moveToFirst())
                {
                    int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    result = cursor.getString(idx);
                }
                cursor.close();
            }
        }catch (Exception e){
            Log.e("get real path", e.getMessage());
        }
        return result;
    }
    private void updateUserStatus() {
        fillFrofileViewModel.getUpdateUserStatus().observe(this, status -> {
            switch (status) {
                case UpdateUserStatus.Success:
                    try {
                        Toast.makeText(FillProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences(Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE);
                        boolean isFirstLogin = sharedPreferences.getBoolean(Constant.SP_FIRST_LOGIN, true);
                        if (isFirstLogin){
                            Intent intent = new Intent(FillProfileActivity.this, CreatePinActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        break;
                    }catch (Exception e){
                        Log.e("fill_profile_Activity", e.getMessage());
                    }
                case UpdateUserStatus.Fails:
                    break;
            }

        });
    }
}