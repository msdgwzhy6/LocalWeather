package com.piotr.weatherforpoznan;

/**
 * Created by piotr on 17.09.15.
 */

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherService {
    //@GET("/data/2.5/forecast")
    //Weather getWeather(@Query("cityId") int id, @Query("mode") String mode, @Query("units") String units, @Query("type") String type);

    @GET("/data/2.5/forecast/city")
    void getForecast(@Query("id") int id, @Query("mode") String mode, @Query("units") String units, @Query("type") String type, Callback<Weather> cb);
    }