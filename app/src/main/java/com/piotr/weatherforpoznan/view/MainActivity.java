package com.piotr.weatherforpoznan.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.piotr.weatherforpoznan.ui.WeatherNotification;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

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
        setWeatherFragments(null);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<ForecastItem> forecastItems = new Select().from(ForecastItem.class).execute();
        //TODO: Set ACTUAL notification data on every application run
        //TODO: Notification as Service running in the background
        //FIXME: Refreshing notification data
        if (forecastItems.size() != 0) {
            ForecastItem item = new Select().from(ForecastItem.class).where("id = ?",
                    forecastItems.get(1).getId())
                    .executeSingle();

            mWeatherNotification.createWeatherNotification(item);
        }
    }

    private void setWeatherFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mActivity, new MainActivityFragment_())
                    .commit();
        }
    }

}