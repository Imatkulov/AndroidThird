package com.geektech.androidthird;

import android.app.Application;

import com.geektech.androidthird.data.SharedPreferenceHelper;

public class App extends Application {
    public static boolean getPreferenceHelper;
    private static SharedPreferenceHelper preferenceHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        preferenceHelper = new SharedPreferenceHelper(this);
    }

    public static SharedPreferenceHelper getPreferenceHelper() {
        return preferenceHelper;
    }
}
