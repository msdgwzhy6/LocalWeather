package com.piotr.weatherforpoznan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piotr.weatherforpoznan.api.ForecastAdapter;
import com.piotr.weatherforpoznan.api.WeatherService;
import com.piotr.weatherforpoznan.model.Forecast;
import com.piotr.weatherforpoznan.model.ForecastItem;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class MainActivityFragment extends Fragment {

    protected ForecastItem forecastItem;
    ForecastAdapter mForecastAdapter;
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
                .setConverter(new GsonConverter(gson))
                .build();

        final WeatherService weatherAPI = restAdapter.create(WeatherService.class);
        weatherAPI.getForecast(3088171, "json", "metric", "hour", new Callback<Forecast>() {
            @Override
            public void success(Forecast forecast, Response response) {
                mForecastAdapter = new ForecastAdapter(getActivity(),
                        R.layout.list_item_forecast, forecast.getForecastList());
                listView.setAdapter(mForecastAdapter);

                int i = 0;
                // ActiveAndroid.beginTransaction();


                ActiveAndroid.beginTransaction();
                try {
                    while (i < forecast.getForecastList().size()) {
                        forecast.getForecastList().get(i).save();
                        i++;
                    }
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }

                //    Log.d("DATABASE", new Database().getRandom(forecastItem).toString());
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

