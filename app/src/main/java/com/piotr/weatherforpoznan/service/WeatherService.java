package com.piotr.weatherforpoznan.service;

/**
 * @author piotr on 17.09.15.
 */

import com.piotr.weatherforpoznan.model.Forecast;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherService {
    @GET("/data/2.5/forecast/city")
    void getForecast(@Query("q") String q, @Query("mode") String mode, @Query("units") String
            units, @Query("type") String type, @Query("lang") String lang, @Query("APPID") String apiId, Callback<Forecast> cb);
}