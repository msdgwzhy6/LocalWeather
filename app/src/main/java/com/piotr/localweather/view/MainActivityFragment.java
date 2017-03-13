package com.piotr.localweather.view;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.piotr.localweather.R;
import com.piotr.localweather.adapter.WeatherDataAdapter;
import com.piotr.localweather.api.WeatherService;
import com.piotr.localweather.api.model.WeatherData;
import com.piotr.localweather.utils.ConnectionUtils;
import com.piotr.localweather.utils.LocationUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.piotr.localweather.WeatherApplication.weatherAPI;

@EFragment(R.layout.fragment_main)
public class MainActivityFragment extends Fragment {

    private final String format = "json";
    private final String units = "metric";
    private final String type = "hour";
    // FIXME: 13.03.17 Extract hardcoded values to xml
    @StringRes
    String API_ID;
    @StringRes
    String lang;
    @ViewById(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @ViewById
    SwipeRefreshLayout swipeRefresh;
    private String locationQuery;
    private WeatherDataAdapter mWeatherDataAdapter;

    @AfterViews
    public void onCreateMainActivityFragment() {
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        downloadForecastData(weatherAPI);
                    }
                }
        );
        if (ConnectionUtils.haveNetworkConnection(getContext())) {
            downloadForecastData(weatherAPI);
        } else {
            Snackbar.make(getView(), R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
        }
        initRecyclerView();
    }

    private void downloadForecastData(final WeatherService weatherAPI) {
        locationQuery = LocationUtils.getPreferredLocation(getContext());

        Call<WeatherData> call = weatherAPI.getForecast(locationQuery, format, units, type, lang, API_ID);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefresh.setRefreshing(false);
                            mWeatherDataAdapter =
                                    new WeatherDataAdapter(response.body(), getActivity());
                            mRecyclerView.setAdapter(mWeatherDataAdapter);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.d("Failure", "error: " + t.getLocalizedMessage());
                swipeRefresh.setRefreshing(false);
                showErrorSnackbar(weatherAPI);
            }
        });
    }

    private void showErrorSnackbar(final WeatherService weatherAPI) {
        if (getView() != null) {
            Snackbar.make(getView(), R.string.error_download_data, Snackbar.LENGTH_LONG)
                    .setAction(R.string.error_again_message, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            downloadForecastData(weatherAPI);
                        }
                    }).show();
        }
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }
}