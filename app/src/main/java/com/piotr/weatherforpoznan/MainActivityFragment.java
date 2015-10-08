package com.piotr.weatherforpoznan;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class MainActivityFragment extends Fragment {


    SwipeRefreshLayout mySwipeRefreshLayout;
    ForecastAdapter mForecastAdapter;
    WeatherService weatherAPI;
    private ListView listView;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        mySwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        downloadForecastData(weatherAPI);
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                    }
                }
        );

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setConverter(new GsonConverter(gson))
                .build();
        weatherAPI = restAdapter.create(WeatherService.class);
        downloadForecastData(weatherAPI);

        return rootView;
    }

    private void downloadForecastData(final WeatherService weatherAPI) {
        weatherAPI.getForecast(3088171, "json", "metric", "hour", new Callback<Forecast>() {
            @Override
            public void success(Forecast forecast, Response response) {
                mForecastAdapter = new ForecastAdapter(getActivity(),
                        R.layout.list_item_forecast, forecast.getForecastList());
                listView.setAdapter(mForecastAdapter);
                mySwipeRefreshLayout.setRefreshing(false);

                //Active Android implementation
                int i = 0;
                ActiveAndroid.beginTransaction();
                try {
                    while (i < forecast.getForecastList().size()) {

                        forecast.getForecastList().get(i).getMain().save();
                        for (int k = 0; k < forecast.getForecastList().get(k).getWeather().size(); k++) {
                            forecast.getForecastList().get(i).getWeather().get(k).save();
                        }
                        forecast.getForecastList().get(i).getWind().save();
                        forecast.getForecastList().get(i).save();
                        //TODO: Make forecast.getForecastList().get(i).save(saveWind, saveMain, saveWeather);
                        i++;
                    }
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }
                Log.d("Database", "Forecast: " + forecast.getForecastList());
                Log.d("DATABASE", "Database: " + Database.getObjectsList());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Failure", "error: " + error);
                mySwipeRefreshLayout.setRefreshing(false);
                Snackbar.make(getView(), "Error caused when trying to download forecast data!", Snackbar.LENGTH_LONG)
                        .setAction("Try again", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                downloadForecastData(weatherAPI);
                            }
                        }).show();
            }
        });
    }
}
