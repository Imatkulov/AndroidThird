package com.geektech.androidthird.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.geektech.androidthird.R;
import com.geektech.androidthird.data.model.current_weather.com.example.CurrentWeatrerModel;
import com.geektech.androidthird.data.network.RetrofitBuilder;
import com.geektech.androidthird.data.network.RetrofitService;
import com.geektech.androidthird.ui.base.BaseActivity;
import com.geektech.androidthird.ui.map.MapFragment;
import com.geektech.androidthird.ui.weather.WeatherFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_Framelayout)
    FrameLayout main_Framelayout;
    @BindView(R.id.btn_map)
    Button btn_map;
    Fragment fragment;
    MapFragment mapFragment = new MapFragment();
    WeatherFragment weatherFragment = new WeatherFragment();

    public static void start(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }
    @Override
    protected Integer getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = weatherFragment;
        setFragment();
    }

    private  void setFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_Framelayout, fragment).commit();
    }
    @OnClick(R.id.btn_map)
    public void showMap(){
        fragment = mapFragment;
        setFragment();
    }
}

