package com.example.sonrisaplantjv.presentation.ui.components;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.example.sonrisaplantjv.R;

public class ProgressDialog {

    public AlertDialog alertDialog;

    public ProgressDialog(Context context) {
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setView(R.layout.progress_layout);
        builder.setCancelable(false);
        alertDialog = builder.create();
    }

    public void show(){
         alertDialog.show();
    }
    public void dismiss(){
        alertDialog.dismiss();
    }
}
