package com.geektech.androidthird.ui.base;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.geektech.androidthird.R;
import com.geektech.androidthird.ui.main.MainActivity;
import com.geektech.androidthird.utils.ToastUtils;

import butterknife.ButterKnife;
import kotlin.Suppress;

public abstract class BaseActivity extends AppCompatActivity {

    ImageButton imageButton;
    public static final String ANDROID_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID";

    protected abstract Integer getResId();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        imageButton = findViewById(R.id.imageButton);
        ButterKnife.bind(this);
    }
    public void createNotification(){
       
    }


    public void OnClick(View view){
        createNotification();
    }
}
