package com.example.sonrisaplantjv.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.sonrisaplantjv.presentation.ui.activity.LoginActivity;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;

public class Constant {
    public static final String SHARED_PREFERENCES  = "SP_1";
    public static final String SP_EMAIl  = "EMAIL";
    public static final String SP_PASSWORD  = "PASSWORD";
    public static final String SP_ACCESS_TOKEN  = "ACCESS TOKEN";
    public static final String SP_REFRESH_TOKEN  = "REFRESH TOKEN";
    public static final String SP_FIRST_LOGIN  = "FIRST LOGIN";
    public static final String SP_IS_LOG_OUT  = "LOG OUT";
    public static final String SP_IS_REMEMBER  = "REMEMBER ME";
    public static final String MSG_FAIL = "Fail";
    public static void showMessageError(Response response, Context context){
        if (!response.isSuccessful() && response.errorBody() != null) {
            if(response.code() == 401){
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Constant.SP_ACCESS_TOKEN);
                editor.remove(Constant.SP_REFRESH_TOKEN);
                editor.apply();
                Toast.makeText(context, "Login session has expired", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(context, jObjError.getString("Message"), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("Call api fail", e.getMessage());
                Toast.makeText(context, MSG_FAIL, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
