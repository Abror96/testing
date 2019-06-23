package com.era.a100.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class Constants {

    public static String BASE_URL = "https://a100.technovik.ru:1000/api/";

    public static ProgressDialog initProgressDialog(Context context, String text) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(text);
        return progressDialog;
    }

}
