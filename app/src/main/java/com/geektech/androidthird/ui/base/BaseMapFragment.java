package com.geektech.androidthird.ui.base;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.geektech.androidthird.R;
import com.geektech.androidthird.ui.TrackingService;
import com.geektech.androidthird.ui.main.MainActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.utils.BitmapUtils;

import java.util.Objects;

import butterknife.BindView;

import static com.geektech.androidthird.App.ANDROID_CHANNEL_ID;

public abstract class BaseMapFragment extends BaseFragment implements MapboxMap.OnMapClickListener {
    public static final String ID_ICON_AIRPORT = "ID_ICON_AIRPORT";
    @BindView(R.id.mapView)
    MapView mapView;
    SymbolManager symbolManager;
    private MapboxMap map;
    private Symbol symbol;
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.imageButtonStop)
    ImageButton imageButtonStop;
    NotificationManagerCompat notificationManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(requireContext(), getResources().getString(R.string.map_key));
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView.onCreate(savedInstanceState);
        imageButton.setOnClickListener(view1 -> getActivity().startService(new Intent(getContext(), TrackingService.class)));
        imageButtonStop.setOnClickListener(view1 -> getActivity().stopService(new Intent(getContext(), TrackingService.class)));


        mapView.getMapAsync(mapboxMap -> {
            mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {
                map = mapboxMap;
                aadImageToSting(style);

                map.addOnMapClickListener(this);

                symbolManager = new SymbolManager(mapView, mapboxMap, mapboxMap.getStyle(), null);

                symbol = symbolManager.create(createMarkers(new LatLng(12.0, 12.0), ID_ICON_AIRPORT));
            });

            mapboxMap.addMarker(new MarkerOptions().setPosition(new LatLng(42.874733, 74.591904, 14.92)));
            CameraPosition position = new CameraPosition.Builder()
                    .target(new LatLng(42.874733, 74.591904, 14.92)) // Sets the new camera position
                    .zoom(15) // Sets the zoom
                    .bearing(180) // Rotate the camera
                    .tilt(30) // Set the camera tilt
                    .build(); // Creates a CameraPosition from the builder
            mapboxMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(position), 5000);
//
        });
    }
    private void aadImageToSting(Style style){
        Objects.requireNonNull(map.getStyle()).addImage(ID_ICON_AIRPORT,
                Objects.requireNonNull(BitmapUtils.getBitmapFromDrawable(getResources().getDrawable(R.drawable.girl))),
                true);    }

    private SymbolOptions createMarkers(LatLng latLng, String image){
        return new  SymbolOptions()
                .withLatLng(latLng)
                .withIconImage(image)
                .withIconSize(1.3F)
                .withSymbolSortKey(10.0f)
                .withDraggable(true);
    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onMapClick(@NonNull LatLng point) {
        symbolManager.create(createMarkers(point, ID_ICON_AIRPORT));
        return false;
    }
    private void getNotification(){
        notificationManager = NotificationManagerCompat.from(getContext());
        Intent resultIntent = new Intent(getContext(), MainActivity.class);

        PendingIntent resultPendingIntent =  PendingIntent.getActivity(getContext(),0,resultIntent,PendingIntent.FLAG_ONE_SHOT);
        resultPendingIntent.describeContents();
        resultPendingIntent.getIntentSender();

        Notification notification = new NotificationCompat.Builder(getActivity(), ANDROID_CHANNEL_ID)
                .setSmallIcon(R.drawable.mapbox_compass_icon)
                .setAutoCancel(true)
                .setContentTitle("My Notification")
                .setContentText("Ваше местоположение")
                .setPriority(Notification.PRIORITY_HIGH)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentIntent(resultPendingIntent)
                .build();
                notificationManager.notify(1, notification);
            }
}
