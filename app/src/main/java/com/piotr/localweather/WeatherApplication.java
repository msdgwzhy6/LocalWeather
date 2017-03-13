package com.piotr.localweather;

import android.app.Application;
import android.os.AsyncTask;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piotr.localweather.service.WeatherService;

import java.lang.reflect.Modifier;

import io.fabric.sdk.android.Fabric;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author piotr on 06.10.2015.
 */

public class WeatherApplication extends Application {

    public static WeatherService weatherAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        final String API_ENDPOINT = "http://api.openweathermap.org";
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .callbackExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        weatherAPI = restAdapter.create(WeatherService.class);
    }
}
