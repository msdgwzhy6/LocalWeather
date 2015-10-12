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

import static com.piotr.weatherforpoznan.utils.Utility.capitalizeString;
import static com.piotr.weatherforpoznan.utils.Utility.getDayName;

public class DetailsActivity extends AppCompatActivity {

    TextView detailDay;
    TextView detailDate;
    TextView highTemperature;
    TextView lowTemperature;
    TextView forecast;
    TextView humidity;
    TextView pressure;
    TextView wind;
    ImageView icon;

    String dayName;
    String date;
    String highTemp;
    String lowTemp;
    String humidityValue;
    String pressureValue;
    String windSpeed;
    String windDeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setDetailsActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        long itemId = (long) b.get("id");

        detailDay = (TextView) findViewById(R.id.detail_day_textview);
        detailDate = (TextView) findViewById(R.id.detail_date_textview);
        highTemperature = (TextView) findViewById(R.id.detail_high_textview);
        lowTemperature = (TextView) findViewById(R.id.detail_low_textview);
        icon = (ImageView) findViewById(R.id.detail_icon);
        forecast = (TextView) findViewById(R.id.detail_forecast_textview);
        humidity = (TextView) findViewById(R.id.detail_humidity_textview);
        pressure = (TextView) findViewById(R.id.detail_pressure_textview);
        this.wind = (TextView) findViewById(R.id.detail_wind_textview);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.function_not_available, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ForecastItem item = new Select().from(ForecastItem.class).where("Id = ?", itemId).executeSingle();
        getDetailActivityViewsValues(item);
        setDetailActivityViewsValues();

        //TODO: Fix problems with loading icon and description from database
        //FIXME: Change format of date and metrics
    }

    private void setDetailActivityViewsValues() {
        detailDay.setText(dayName);
        detailDate.setText(date);
        highTemperature.setText(highTemp);
        lowTemperature.setText(lowTemp);
        this.humidity.setText("Humidity: " + humidityValue);
        this.pressure.setText("Pressure: " + pressureValue);
        wind.setText("Wind: " + windSpeed + " \t" + windDeg);
    }

    private void getDetailActivityViewsValues(ForecastItem item) {
        dayName = capitalizeString(getDayName(getApplicationContext(), item.getDt_txt().getTime()));
        date = item.getDt_txt().toString();
        highTemp = Math.round(item.getMain().getTempMax()) + " °C";
        lowTemp = Math.round(item.getMain().getTempMin()) + " °C";
        humidityValue = Math.round(item.getMain().getHumidity()) + " %";
        pressureValue = Math.round(item.getMain().getPressure()) + " hPa";
        windSpeed = Math.round(item.getWind().getSpeed()) + " km/h";
        windDeg = Utility.getFormattedWind(item.getWind().getDeg());
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
        getSupportActionBar().setTitle(R.string.title_activity_details);
    }
}