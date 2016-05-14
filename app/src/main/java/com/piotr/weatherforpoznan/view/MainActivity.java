package com.piotr.weatherforpoznan.view;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.piotr.weatherforpoznan.ui.WeatherNotification;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import de.greenrobot.event.EventBus;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final WeatherNotification mWeatherNotification = new WeatherNotification(this);

    @ViewById(R.id.action_bar_title)
    TextView action_bar_title;

    @StringRes
    String geo_coord;

    @StringRes
    String latitude;

    @StringRes
    String longitude;

    @StringRes
    String latitude_short;

    @StringRes
    String longitude_short;

    @AfterViews
    public void initialize() {
        EventBus.getDefault().register(this);
        setWeatherFragments();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setWeatherFragments() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mActivity, new MainActivityFragment_())
                    .commit();
    }

    public void onEvent(ForecastItem item) {
        Log.d(TAG, "onEvent() called with: " + "item = [" + item + "]");
        mWeatherNotification.createWeatherNotification(item);
        //TODO: Set ACTUAL notification data on every application run
        //TODO: Notification as Service running in the background
        //FIXME: Refreshing notification data
    }
}