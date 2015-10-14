package com.piotr.weatherforpoznan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.model.City;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.res.StringRes;

@OptionsMenu(R.menu.menu_main)
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @StringRes
    String geo_coord;

    @StringRes
    String latitude;

    @StringRes
    String longitude;

    @OptionsItem(R.id.action_settings)
    void firstMenuItemCalled() {
        Intent others = new Intent(this, SettingsActivity_.class);
        startActivity(others);
    }

    @UiThread
    protected void setWeatherFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivityFragment())
                    .commit();
        }
    }

    @AfterViews
    public void setMainActivityActionBar() {
        setWeatherFragments(null);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(getGeoCoordinates());
    }

    protected String getGeoCoordinates() {
        City city = new Select().from(City.class).executeSingle();
        if (city != null) {
            latitude = "Lat: " + city.getCoord().getLat().toString();
            longitude = "Long: " + city.getCoord().getLon().toString();
            geo_coord = latitude + "\t|\t" + longitude;
        }
        return geo_coord;
    }
}