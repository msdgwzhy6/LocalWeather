package com.piotr.weatherforpoznan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.model.City;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@OptionsMenu(R.menu.menu_main)
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

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

    @OptionsItem(R.id.action_settings)
    void firstMenuItemCalled() {
        Intent others = new Intent(this, SettingsActivity_.class);
        startActivity(others);
    }

    @UiThread
    protected void setWeatherFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivityFragment_())
                    .commit();
        }
    }

    @AfterViews
    public void setMainActivityActionBar() {
        setWeatherFragments(null);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
        getSupportActionBar().setTitle("HELLLLO");
        //action_bar_title.setText(R.string.function_not_available);
        // getSupportActionBar().setSubtitle(getGeoCoordinates());
    }

    protected String getGeoCoordinates() {
        City city = new Select().from(City.class).executeSingle();
        if (city != null) {
            String latitude_value = city.getCoord().getLat().toString();
            latitude = latitude_short+ latitude_value;
            String longitude_value = city.getCoord().getLon().toString();
            longitude = longitude_short+ longitude_value;
            geo_coord = latitude+" "+" || " +" "+longitude;
        }
        return geo_coord;
    }
}