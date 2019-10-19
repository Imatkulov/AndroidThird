package com.geektech.androidthird;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

import com.geektech.androidthird.data.sharedPreference.SharedPreferenceHelper;



public class App extends Application {
    public static boolean getPreferenceHelper;
    private static SharedPreferenceHelper preferenceHelper;
    public static final String ANDROID_CHANNEL_ID = "channel_id";
    public static final String NAME = "channel_name";


    @Override
    public void onCreate() {
        super.onCreate();
        preferenceHelper = new SharedPreferenceHelper(this);
        createNotificationCnannel();
    }

    private void createNotificationCnannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            NotificationChannel channel = new NotificationChannel(ANDROID_CHANNEL_ID, NAME, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static SharedPreferenceHelper getPreferenceHelper() {
        return preferenceHelper;
    }
}
