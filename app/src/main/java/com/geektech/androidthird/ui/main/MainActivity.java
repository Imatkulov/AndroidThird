package com.geektech.androidthird.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.geektech.androidthird.R;
import com.geektech.androidthird.data.model.current_weather.com.example.CurrentWeatrerModel;
import com.geektech.androidthird.data.network.RetrofitBuilder;
import com.geektech.androidthird.data.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView temperature;
    TextView humidity;
    EditText editText;
    Button button;
    ImageView imageView;
//    LottieAnimationView lottieAnimationView;

    public static void start(Context context){
        context.startActivity(new Intent(context, MainActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editWeather);
        temperature = findViewById(R.id.temperature);
        button = findViewById(R.id.btn_weather);
        imageView = findViewById(R.id.main_imageView);
        humidity = findViewById(R.id.humidity);
//        lottieAnimationView = findViewById(R.id.main_Lottie);
        getCurrentWeather();
    }

    private void getCurrentWeather() {
        String city = editText.getText().toString().trim();

        RetrofitBuilder.getService().getWeatherByCity(city,"metric", getResources().getString(R.string.api_key))
                .enqueue(new Callback<CurrentWeatrerModel>() {
                    @Override
                    public void onResponse(@Nullable Call<CurrentWeatrerModel> call, @Nullable Response<CurrentWeatrerModel> response) {
                        if (response != null && response.isSuccessful() && response.body() != null) {
                            temperature.setText(response.body().getMain().getTemp().toString());
                            humidity.setText(response.body().getMain().getHumidity().toString());
                            Glide.with(MainActivity.this).load("http://openweathermap.org/img/wn/" + response.body().getWeather().get(0).getIcon()+"@2x.png").into(imageView);

                            Toast.makeText(getApplicationContext(), response.body().getName(), Toast.LENGTH_LONG).show();
                            Log.e("getCurrentWeather", response.body().getName());
                        } else {
                            Toast.makeText(getApplicationContext(), "Error server", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(@Nullable Call<CurrentWeatrerModel> call, @Nullable Throwable t) {
                        if (t != null)
                            Log.e("getCurrentWeather", t.getLocalizedMessage());
                    }
                });
    }

    public void onShowWeather(View view) {
        getCurrentWeather();
    }
}

