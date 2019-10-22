package com.geektech.androidthird.ui.splash;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geektech.androidthird.App;
import com.geektech.androidthird.ui.main.MainActivity;
import com.geektech.androidthird.ui.onBoard.OnBoardActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckIsFirstLaunch();
    }

    private void CheckIsFirstLaunch() {
        if (App.getPreferenceHelper().IsFirstLaunch()){
            Log.e("----------", "true");
//            App.getPreferenceHelper().setIsFirstLaunch();
            OnBoardActivity.start(this);
        } else {
            Log.e("----------", "false");
            MainActivity.start(this);
        }finish();
    }
}

