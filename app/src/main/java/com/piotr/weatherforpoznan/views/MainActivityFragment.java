package com.piotr.weatherforpoznan.views;

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

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    String BASE_URL = "http://api.openweathermap.org";

    ArrayAdapter<String> mForecastAdapter;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Gson gson = new GsonBuilder()
                .setDateFormat("YYYY-MM-DD hh:mm:ss")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();

        WeatherService weatherAPI = restAdapter.create(WeatherService.class);
        weatherAPI.getForecast(3088171, "json", "metric", "hour", new retrofit.Callback<Forecast>() {
            @Override
            public void success(Forecast forecast, Response response) {
                String data = forecast.toString();                                          //Not working properly
                List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));
                Log.d("Forecast", "result: " + forecast);
                mForecastAdapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.list_item_forecast,
                        R.id.list_item_forecast_textview,
                        weekForecast);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        String[] data = {
                "01.00 - Rainy - 18^C - 3.71m/s - 77 % - 1003 hpa",
                "02.00 - Rainy - 18^C - 3.71m/s - 77 % - 1003 hpa",
                "03.00 - Rainy - 18^C - 3.71m/s - 77 % - 1003 hpa",
                "04.00 - Rainy - 19^C - 3.71m/s - 77 % - 1003 hpa",
                "05.00 - Rainy - 19^C - 3.71m/s - 77 % - 1003 hpa",
                "06.00 - Cloudy - 21^C - 2.51m/s - 62 % - 1004 hpa",
                "07.00 - Cloudy - 22^C - 2.51m/s - 62 % - 1004 hpa",
                "08.00 - Cloudy - 22^C - 2.51m/s - 62 % - 1004 hpa",
                "09.00 - Cloudy - 23^C - 2.51m/s - 62 % - 1004 hpa",
                "10.00 - Cloudy - 24^C - 2.51m/s - 62 % - 1004 hpa",
                "11.00 - Sunny  - 24^C - 2.51m/s - 62 % - 1004 hpa",
                "12.00 - Sunny - 25^C - 2.51m/s - 62 % - 1004 hpa",
                "13.00 - Sunny - 25^C - 2.51m/s - 62 % - 1004 hpa",
                "14.00 - Sunny - 26^C - 2.51m/s - 62 % - 1004 hpa",
                "15.00 - Sunny - 26^C - 2.51m/s - 62 % - 1004 hpa",
                "16.00 - Sunny - 25^C - 2.51m/s - 62 % - 1004 hpa",
                "17.00 - Cloudy - 25^C - 2.51m/s - 62 % - 1004 hpa",
                "18.00 - Cloudy - 24^C - 2.51m/s - 62 % - 1004 hpa",
                "19.00 - Cloudy - 23^C - 2.51m/s - 62 % - 1004 hpa",
                "20.00 - Cloudy - 22^C - 2.51m/s - 62 % - 1004 hpa",
                "21.00 - Cloudy - 21^C - 2.51m/s - 62 % - 1004 hpa",
                "22.00 - Cloudy - 20^C - 2.51m/s - 62 % - 1004 hpa",
                "23.00 - Cloudy - 20^C - 2.51m/s - 62 % - 1004 hpa",
                "24.00 - Cloudy - 20^C - 2.51m/s - 62 % - 1004 hpa",
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));

        mForecastAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);
        return rootView;
    }
}

