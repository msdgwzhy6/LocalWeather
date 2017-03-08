package com.piotr.localweather.service;

/**
 * @author piotr on 17.09.15.
 */

import com.piotr.localweather.model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("/data/2.5/forecast")
    Call<Forecast> getForecast(@Query("q") String q,
                               @Query("mode") String mode,
                               @Query("units") String units,
                               @Query("type") String type,
                               @Query("lang") String lang,
                               @Query("APPID") String appiId);
}