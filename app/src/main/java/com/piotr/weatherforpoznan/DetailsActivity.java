package com.piotr.weatherforpoznan;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import static com.piotr.weatherforpoznan.utils.Utility.capitalizeString;
import static com.piotr.weatherforpoznan.utils.Utility.getArtResourceForWeatherCondition;
import static com.piotr.weatherforpoznan.utils.Utility.getDayName;
import static com.piotr.weatherforpoznan.utils.Utility.getFormattedWind;

@EActivity(R.layout.activity_details)
public class DetailsActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;
    @ViewById
    FloatingActionButton fab;

    @ViewById(R.id.detail_day_textview)
    TextView detailDay;
    @ViewById(R.id.detail_date_textview)
    TextView detailDate;
    @ViewById(R.id.detail_high_textview)
    TextView highTemperature;
    @ViewById(R.id.detail_low_textview)
    TextView lowTemperature;
    @ViewById(R.id.detail_forecast_textview)
    TextView forecast;
    @ViewById(R.id.detail_humidity_textview)
    TextView humidity;
    @ViewById(R.id.detail_pressure_textview)
    TextView pressure;
    @ViewById(R.id.detail_wind_textview)
    TextView wind;
    @ViewById(R.id.detail_icon)
    ImageView forecast_icon;

    String dayName;
    String date;
    String highTemp;
    String lowTemp;
    String humidityValue;
    String pressureValue;
    String windSpeed;
    String windDeg;
    String description;
    int icon;

    @Extra
    long id;

    @Click(R.id.fab)
    public void onClick(View view) {
        Snackbar.make(view, R.string.function_not_available, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @AfterViews
    protected void onCreateView() {
        setDetailsActionBar(toolbar);
        setSupportActionBar(toolbar);
        long itemId = id;
        ForecastItem item = new Select().from(ForecastItem.class).where("Id = ?", itemId).executeSingle();
        Log.d("DetailsActivity", item.toString());
        getDetailActivityViewsValues(item);
        setDetailActivityViewsValues();
    }

    @UiThread
    protected void setDetailActivityViewsValues() {
        detailDay.setText(dayName);
        detailDate.setText(date);
        highTemperature.setText(highTemp);
        lowTemperature.setText(lowTemp);
        this.humidity.setText("Humidity: " + humidityValue);
        this.pressure.setText("Pressure: " + pressureValue);
        wind.setText("Wind: " + windSpeed + " \t" + windDeg);
        Picasso.with(getApplicationContext()).load(icon).into(forecast_icon);
        forecast.setText(capitalizeString(description));
    }

    @UiThread
    protected void getDetailActivityViewsValues(ForecastItem item) {
        dayName = capitalizeString(getDayName(getApplicationContext(), item.getDt_txt().getTime()));
        date = item.getDt_txt().toString();
        highTemp = Math.round(item.getMain().getTempMax()) + " °C";
        lowTemp = Math.round(item.getMain().getTempMin()) + " °C";
        humidityValue = Math.round(item.getMain().getHumidity()) + " %";
        pressureValue = Math.round(item.getMain().getPressure()) + " hPa";
        windSpeed = Math.round(item.getWind().getSpeed()) + " km/h";
        windDeg = getFormattedWind(item.getWind().getDeg());
        icon = getArtResourceForWeatherCondition(item.getWeatherData().getWeatherId());
        description = item.getWeatherData().getDescription();
    }

    @UiThread
    protected void setDetailsActionBar(Toolbar toolbar) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle(R.string.title_activity_settings);
    }
}