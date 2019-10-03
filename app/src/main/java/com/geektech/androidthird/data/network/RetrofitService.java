package com.geektech.androidthird.data.network;

import com.geektech.androidthird.data.model.current_weather.com.example.CurrentWeatrerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

        @GET("weather")
        Call<CurrentWeatrerModel> getWeatherByCity(@Query("q") String city, @Query("units") String unit, @Query("APPID") String appid);

}

