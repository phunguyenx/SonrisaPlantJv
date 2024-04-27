package com.example.sonrisaplantjv.common;

import android.content.Context;
import android.widget.Toast;

import com.example.sonrisaplantjv.presentation.ui.activity.LoginActivity;

import org.json.JSONObject;

import retrofit2.Response;

public class Constant {
    public static void checkResponse(Response response, Context context){
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
