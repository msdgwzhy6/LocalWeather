package com.piotr.localweather.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.piotr.localweather.R;
import com.piotr.localweather.api.model.WeatherData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.IntegerRes;
import org.androidannotations.annotations.res.StringRes;

@SuppressLint("Registered")
@EActivity(R.layout.activity_details)
public class DetailsActivity extends BasicActivity {

    private final String TAG = DetailsActivity.class.getSimpleName();
    @ViewById
    public FloatingActionButton fab;
    @ViewById
    public TextView dDay;
    @ViewById
    public TextView dDate;
    @ViewById
    public TextView dCity;
    @ViewById
    public TextView dHighTemp;
    @ViewById
    public TextView dLowTemp;
    @ViewById
    public TextView dDescription;
    @ViewById
    public TextView dHumidityVal;
    @ViewById
    public TextView dPressureVal;
    @ViewById
    public TextView dWindVal;
    @ViewById
    public ImageView dIcon;
    @ViewById
    Toolbar toolbar;
    @StringRes
    String day;
    @StringRes
    String date;
    @StringRes
    String city_name;
    @StringRes
    String high_temp;
    @StringRes
    String low_temp;
    @StringRes
    String humidity_val;
    @StringRes
    String pressure_val;
    @StringRes
    String wind_val;
    @StringRes
    String description;
    @IntegerRes
    int icon;
    @Extra
    long id;

    private WeatherData item;
    private com.piotr.localweather.api.model.City city;

    @Click(R.id.fab)
    public void onClick(View view) {
        String shareMessage = String.format("%s, %s: %s %s",
                dDay.getText(),
                dDate.getText(),
                dDescription.getText(),
                dLowTemp.getText());
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(
                R.string.share_message_subject, item.getCity()));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string
                .share_message_choose)));
    }

    @AfterViews
    protected void initialize() {
        setDetailsActivityActionBar();
        // FIXME: 13.03.17 Re-implement previous functionality
    }


    private void setDetailsActivityActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}