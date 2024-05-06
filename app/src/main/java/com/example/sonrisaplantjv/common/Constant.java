package com.example.sonrisaplantjv.common;

import android.content.Context;
import android.widget.Toast;

import com.example.sonrisaplantjv.presentation.ui.activity.LoginActivity;

import org.json.JSONObject;

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
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(context, jObjError.getString("Message"), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
