package com.piotr.weatherforpoznan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piotr.weatherforpoznan.api.ForecastAdapter;
import com.piotr.weatherforpoznan.api.WeatherService;
import com.piotr.weatherforpoznan.model.Forecast;
import com.piotr.weatherforpoznan.utils.WeatherApplication;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class MainActivityFragment extends Fragment {

    String API_ID = "f1570c3640caf0e5f96358d802933e40";

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long forecastItemId = mForecastAdapter.getItem(position).getId();
                Intent intent = new Intent(getContext(), DetailsActivity_.class);
                intent.putExtra("id", forecastItemId);
                startActivity(intent);

            }
        });
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

        final String API_ENDPOINT = "http://api.openweathermap.org";
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
        weatherAPI = restAdapter.create(WeatherService.class);
        downloadCityData(weatherAPI);
        downloadForecastData(weatherAPI);

        return rootView;
    }

    protected void downloadForecastData(final WeatherService weatherAPI) {
        weatherAPI.getForecast(3088171, "json", "metric", "hour", API_ID, new Callback<Forecast>() {
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

                        forecast.getForecastList().get(i).saveItemToDatabase();
                        i++;
                    }
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }

                Log.d("WeatherApplication", "Forecast: " + forecast.getForecastList());
                Log.d("DATABASE", "WeatherApplication: " + WeatherApplication.getObjectsList());
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

    protected void downloadCityData(final WeatherService weatherAPI) {
        weatherAPI.getForecast(3088171, "json", "metric", "hour", API_ID, new Callback<Forecast>() {
            @Override
            public void success(Forecast forecast, Response response) {
                ActiveAndroid.beginTransaction();
                try {
                    forecast.getCity().getCoord().save();
                    forecast.getCity().save();
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }
                Log.d("DATABASE", "WeatherApplication: " + WeatherApplication.getCityList());

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