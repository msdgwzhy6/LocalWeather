package com.piotr.weatherforpoznan;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

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

    @UiThread
    protected void setWeatherFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mActivity, new MainActivityFragment_())
                    .commit();
        }
    }

    @AfterViews
    public void setMainActivityActionBar() {
        setWeatherFragments(null);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
    }
}