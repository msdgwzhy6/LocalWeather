package com.piotr.weatherforpoznan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piotr.weatherforpoznan.api.ForecastAdapter;
import com.piotr.weatherforpoznan.api.WeatherService;
import com.piotr.weatherforpoznan.model.Forecast;

import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ForecastAdapter mForecastAdapter;
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
        String lang = "en";
        String language = Locale.getDefault().getDisplayLanguage().toString();
        if (language == "pl") lang = "pl";
        weatherAPI.getForecast(3088171, "json", "metric", "hour", lang, new Callback<Forecast>() {
            @Override
            public void success(Forecast forecast, Response response) {
                mForecastAdapter = new ForecastAdapter(getActivity(),
                        R.layout.list_item_forecast, forecast.getForecastList());
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

