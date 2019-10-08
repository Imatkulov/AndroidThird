package com.geektech.androidthird.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geektech.androidthird.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import butterknife.BindView;

public abstract class BaseMapFragment extends BaseFragment{

    @BindView(R.id.mapView)
    MapView mapView;
    MapboxMap map;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getContext(), getResources().getString(R.string.map_key));
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView.onCreate(savedInstanceState);
      //  mapView.getMapAsync(this);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                map = mapboxMap;
                mapboxMap.setStyle(Style.MAPBOX_STREETS);
                //mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> mapboxMap.animateCamera(new CameraUpdateFactory().newLatLng(new LatLng(42.874476, 74.590739)), 3000));
//        camera position bishkek
                mapboxMap.addMarker(new MarkerOptions().setPosition(new LatLng(42.874733, 74.591904,14.92)).setTitle("Bishkek"));
                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(42.874733, 74.591904,14.92)) // Sets the new camera position
                        .zoom(15) // Sets the zoom
                        .bearing(180) // Rotate the camera
                        .tilt(30) // Set the camera tilt
                        .build(); // Creates a CameraPosition from the builder
                mapboxMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(position), 5000);

            }
        });
    }



//    @Override
//    public void onMapReady(@NonNull MapboxMap mapboxMap) {
//        this.mapboxMap = mapboxMap;
//        mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> mapboxMap.animateCamera(new CameraUpdateFactory().newLatLng(new LatLng(42.874476, 74.590739)), 3000));
////        camera position bishkek
//            mapboxMap.addMarker(new MarkerOptions().setPosition(new LatLng(42.8747057, 74.6101724,14.92)).setTitle("Bishkek"));
//            CameraPosition position = new CameraPosition.Builder()
//                    .target(new LatLng(42.8747057, 74.6101724,14.92)) // Sets the new camera position
//                    .zoom(10) // Sets the zoom
//                    .bearing(180) // Rotate the camera
//                    .tilt(30) // Set the camera tilt
//                    .build(); // Creates a CameraPosition from the builder
//           mapboxMap.animateCamera(CameraUpdateFactory
//                   .newCameraPosition(position), 4000);
//    }
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
}






