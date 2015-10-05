package com.piotr.weatherforpoznan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piotr.weatherforpoznan.api.WeatherService;
import com.piotr.weatherforpoznan.model.Forecast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ArrayAdapter<String> mForecastAdapter;
    int i = 0;
    String temperature, weatherDescription;
    private ListView listView;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setConverter(new GsonConverter(gson))
                .build();

        final WeatherService weatherAPI = restAdapter.create(WeatherService.class);
        weatherAPI.getForecast(3088171, "json", "metric", "hour", new Callback<Forecast>() {
            @Override
            public void success(Forecast forecast, Response response) {
                Log.d("SUCCESS", "Success result: " + forecast);

                List<String> weekForecast = new ArrayList<>(Arrays.asList(forecast.getForecastItem().get(0).getMain().getTemp().toString()));

                mForecastAdapter = new ArrayAdapter<>(getActivity(),
                        R.layout.list_item_forecast,
                        R.id.list_item_forecast,
                        weekForecast);
                listView.setAdapter(mForecastAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Failure", "error: " + error);
            }
        });

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        return rootView;
    }
}

