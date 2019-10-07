package com.geektech.androidthird.data.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceHelper {

    private final static String PREFERENCE = "MyPref";
    private final static String IS_FIRST_LAUNCH = "isFirstLaunch";

    private SharedPreferences preferences;

    public SharedPreferenceHelper(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE, MODE_PRIVATE);
    }


    public void setIsFirstLaunch(){
        preferences.edit().putBoolean(IS_FIRST_LAUNCH, false).apply();
    }
    public boolean IsFirstLaunch(){

        return preferences.getBoolean(IS_FIRST_LAUNCH, true);

    }
}
