package com.piotr.weatherforpoznan;

/**
 * Created by piotr on 17.09.15.
 */

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiInterface {
    @GET("/data/2.5/weather")  //
    void getWeather(@Query("q") String city, Callback<ResponseData> callback);
}

