package com.geektech.androidthird.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {


    public static void showShortToast(String message, Context context ) {
        showToast(message, context, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(String message, Context context) {
        showToast(message, context, Toast.LENGTH_LONG);
    }

    private static void showToast(String message, Context context, int type) {
        Toast.makeText(context,message, type).show();
    }
}
