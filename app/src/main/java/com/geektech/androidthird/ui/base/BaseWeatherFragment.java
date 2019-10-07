package com.geektech.androidthird.ui.base;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.geektech.androidthird.R;
import com.geektech.androidthird.data.model.current_weather.com.example.CurrentWeatrerModel;
import com.geektech.androidthird.data.network.RetrofitBuilder;
import com.geektech.androidthird.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseWeatherFragment extends BaseFragment {

    @BindView(R.id.editWeather)
    EditText editText;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.weather)
    TextView weather;
    @BindView(R.id.wind)
    TextView wind;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.main_imageView)
    ImageView imageView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                            wind.setText(response.body().getWind().getSpeed().toString());
                            weather.setText(response.body().getWeather().get(0).getMain());
                            Glide.with(getContext()).load("http://openweathermap.org/img/wn/" + response.body().
                                    getWeather().get(0).getIcon()+"@2x.png").into(imageView);

                            Toast.makeText(getContext(), response.body().getName(), Toast.LENGTH_LONG).show();
                            Log.e("getCurrentWeather", response.body().getName());
                        } else {
                            Toast.makeText(getContext(), "Error server", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(@Nullable Call<CurrentWeatrerModel> call, @Nullable Throwable t) {
                        if (t != null)
                            Log.e("getCurrentWeather", t.getLocalizedMessage());
                    }
                });
    }

    @OnClick(R.id.btn_weather)
    public void showWeather(){
        getCurrentWeather();
    }
}



