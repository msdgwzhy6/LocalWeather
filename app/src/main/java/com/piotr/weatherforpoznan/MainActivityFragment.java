package com.piotr.weatherforpoznan;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.piotr.weatherforpoznan.api.ForecastAdapter;
import com.piotr.weatherforpoznan.api.WeatherService;
import com.piotr.weatherforpoznan.model.Forecast;
import com.piotr.weatherforpoznan.utils.WeatherApplication;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.piotr.weatherforpoznan.utils.WeatherApplication.weatherAPI;

@EFragment(R.layout.fragment_main)
public class MainActivityFragment extends Fragment {

    @StringRes
    String API_ID;

    @ViewById
    ListView listview_forecast;

    @ViewById
    SwipeRefreshLayout swiperefresh;

    ForecastAdapter mForecastAdapter;

    @AfterViews
    public void onCreateFragment() {

        listview_forecast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long forecastItemId = mForecastAdapter.getItem(position).getId();
                Intent intent = new Intent(getContext(), DetailsActivity_.class);
                intent.putExtra("id", forecastItemId);
                startActivity(intent);
            }
        });
        swiperefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        downloadForecastData(weatherAPI);
                    }
                }
        );
        downloadForecastData(weatherAPI);
        downloadCityData(weatherAPI);
    }

    @UiThread
    protected void downloadForecastData(final WeatherService weatherAPI) {
        weatherAPI.getForecast(3088171, "json", "metric", "hour", "f1570c3640caf0e5f96358d802933e40", new Callback<Forecast>() {
            @Override
            public void success(Forecast forecast, Response response) {
                mForecastAdapter = new ForecastAdapter(getActivity(),
                        R.layout.list_item_forecast, forecast.getForecastList());
                listview_forecast.setAdapter(mForecastAdapter);
                swiperefresh.setRefreshing(false);

                //Active Android implementation
                saveForecastItemToDatabase(forecast);

                Log.d("WeatherApplication", "Forecast: " + forecast.getForecastList());
                Log.d("DATABASE", "WeatherApplication: " + WeatherApplication.getObjectsList());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Failure", "error: " + error);
                swiperefresh.setRefreshing(false);
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

    private void saveForecastItemToDatabase(Forecast forecast) {
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
    }

    @UiThread
    protected void downloadCityData(final WeatherService weatherAPI) {
        weatherAPI.getForecast(3088171, "json", "metric", "hour", "f1570c3640caf0e5f96358d802933e40", new Callback<Forecast>() {
            @Override
            public void success(Forecast forecast, Response response) {
                saveCityDataToDatabase(forecast);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Failure", "error: " + error);
                swiperefresh.setRefreshing(false);
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

    private void saveCityDataToDatabase(Forecast forecast) {
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
}