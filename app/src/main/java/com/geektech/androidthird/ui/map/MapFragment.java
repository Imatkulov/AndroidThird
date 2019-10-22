package com.geektech.androidthird.ui.map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.geektech.androidthird.R;
import com.geektech.androidthird.ui.TrackingService;
import com.geektech.androidthird.ui.base.BaseFragment;
import com.geektech.androidthird.ui.base.BaseMapFragment;


public class MapFragment  extends BaseMapFragment {



    @Override
    protected Integer getResId() { return R.layout.fragment_map;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().stopService(new Intent(getContext(), TrackingService.class));
    }

    private boolean checkPermissions() {
        return  PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
    }
    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 12) {
            if (grantResults.length <= 0) {
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               getActivity().startService(new Intent(getContext(), TrackingService.class));
            }
        }
    }
}
