package com.geektech.androidthird.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.geektech.androidthird.R;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context){
        context.startActivity(new Intent(context, MainActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
