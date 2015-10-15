package com.piotr.weatherforpoznan.utils;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.app.Application;
import com.activeandroid.query.Select;
import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piotr.weatherforpoznan.api.WeatherService;
import com.piotr.weatherforpoznan.model.City;
import com.piotr.weatherforpoznan.model.ForecastItem;

import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Piotr on 06.10.2015.
 */

public class WeatherApplication extends Application {

    public static WeatherService weatherAPI;

    public static List<Model> getObjectsList() {
        return new Select().from(ForecastItem.class).execute();
    }

    public static List<Model> getCityList() {
        return new Select().from(City.class).execute();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        ActiveAndroid.initialize(this);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        final String API_ENDPOINT = "http://api.openweathermap.org";
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
        weatherAPI = restAdapter.create(WeatherService.class);
    }


}
