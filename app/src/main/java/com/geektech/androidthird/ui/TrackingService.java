package com.geektech.androidthird.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.geektech.androidthird.R;
import com.geektech.androidthird.ui.main.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;

import static com.geektech.androidthird.App.ANDROID_CHANNEL_ID;

public class TrackingService extends Service {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private ArrayList<LatLng> lisLocation;
    private NotificationManagerCompat notificationManager;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startLocationService(intent);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private void initFusedLocation(){
        initCallback();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        LocationRequest request = LocationRequest.create();
        fusedLocationProviderClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper());

    }
    private void  initCallback(){
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                lisLocation.add(new LatLng(locationResult.getLastLocation()));
            }
        };
    }

    private void startLocationService(Intent intent) {
        initFusedLocation();
        lisLocation = new ArrayList<>();
        startForeground(1, getNotification());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
    private Notification getNotification(){
        notificationManager = NotificationManagerCompat.from(getApplicationContext());
        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);

        PendingIntent resultPendingIntent =  PendingIntent.getActivity(getApplicationContext(),0,resultIntent,PendingIntent.FLAG_ONE_SHOT);
        resultPendingIntent.describeContents();
        resultPendingIntent.getIntentSender();

        Notification notification = new NotificationCompat.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_message_black_24dp)
                .setAutoCancel(true)
                .setContentTitle("My Notification")
                .setContentText("Ваше местоположение")
                .setPriority(Notification.PRIORITY_HIGH)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentIntent(resultPendingIntent)
                .build();
        notificationManager.notify(1, notification);
        return notification;

    }
}
