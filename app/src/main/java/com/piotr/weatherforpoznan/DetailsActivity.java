package com.piotr.weatherforpoznan;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.crashlytics.android.Crashlytics;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.piotr.weatherforpoznan.utils.Utility;

import io.fabric.sdk.android.Fabric;

public class DetailsActivity extends AppCompatActivity {

    TextView detailDay;
    TextView detailDate;
    TextView highTemperature;
    TextView lowTemperature;
    ImageView icon;
    TextView forecast;
    TextView humidity;
    TextView pressure;
    TextView wind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setDetailsActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        long itemId = (long) b.get("id");

        this.detailDay = (TextView) findViewById(R.id.detail_day_textview);
        this.detailDate = (TextView) findViewById(R.id.detail_date_textview);
        this.highTemperature = (TextView) findViewById(R.id.detail_high_textview);
        this.lowTemperature = (TextView) findViewById(R.id.detail_low_textview);
        this.icon = (ImageView) findViewById(R.id.detail_icon);
        this.forecast = (TextView) findViewById(R.id.detail_forecast_textview);
        this.humidity = (TextView) findViewById(R.id.detail_humidity_textview);
        this.pressure = (TextView) findViewById(R.id.detail_pressure_textview);
        this.wind = (TextView) findViewById(R.id.detail_wind_textview);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "O", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ForecastItem item = new Select().from(ForecastItem.class).where("Id = ?", itemId).executeSingle();
        detailDay.setText(Utility.getDayName(getApplicationContext(), item.getDt_txt().getTime()));
        detailDate.setText("" + item.getDt_txt());
        highTemperature.setText("" + Math.round(item.getMain().getTempMax()) + " °C");
        lowTemperature.setText("" + Math.round(item.getMain().getTempMin()) + " °C");
        humidity.setText("Humidity: " + Math.round(item.getMain().getHumidity()) + " %");
        pressure.setText("Pressure: " + Math.round(item.getMain().getPressure()) + " hPa");
        wind.setText("Wind: " + Math.round(item.getWind().getSpeed()) + " km/h \t" + Utility.getFormattedWind(item.getWind().getDeg()));

        //TODO: Fix problems with loading icon and description from database
        //FIXME: Change format of date and metrics
    }

    private void setDetailsActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Details");
    }
}
