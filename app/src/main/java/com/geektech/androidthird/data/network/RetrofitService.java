package com.geektech.androidthird.data.network;

import com.geektech.androidthird.data.model.current_weather.com.example.CurrentWeatrerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.geektech.androidthird.data.Constant.CURRENT_WEATHER;

public interface RetrofitService {

        @GET(CURRENT_WEATHER)
        Call<CurrentWeatrerModel> getWeatherByCity(@Query("q") String city, @Query("units") String unit, @Query("APPID") String appid);

        @GET("locations/v1/adminareas/")
        Call<CurrentWeatrerModel> getWeatherByCity(@Path("id") String id );



}

