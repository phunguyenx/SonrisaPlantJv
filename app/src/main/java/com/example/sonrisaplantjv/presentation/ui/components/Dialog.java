package com.example.sonrisaplantjv.presentation.ui.components;

import android.app.ProgressDialog;
import android.content.Context;

public class Dialog {
    static ProgressDialog progressDialog;
    public static void showProgressDialog(Context context, String msg){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }
    public static void dismissProgressDialog(){
        progressDialog.dismiss();
    }
}
