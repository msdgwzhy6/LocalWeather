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
import com.piotr.weatherforpoznan.model.City;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.IntegerRes;
import org.androidannotations.annotations.res.StringRes;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @ViewById
    TextView detail_day_textview;
    @ViewById
    TextView detail_date_textview;
    @ViewById
    TextView detail_city;
    @ViewById
    TextView detail_high_textview;
    @ViewById
    TextView detail_low_textview;
    @ViewById
    TextView detail_forecast_textview;
    @ViewById
    TextView detail_humidity_val_textview;
    @ViewById
    TextView detail_pressure_val_textview;
    @ViewById
    TextView detail_wind_val_textview;
    @ViewById
    ImageView detail_icon;

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
    String wind_speed;
    @StringRes
    String wind_val;
    @StringRes
    String wind_deg;
    @StringRes
    String description;

    @IntegerRes
    int icon;

    @Extra
    long id;

    private static String getFormattedDate(Date date) {
        String formattedDay = new SimpleDateFormat("d MMMM y hh:mm a").format(date);
        return formattedDay;
    }

    @Click(R.id.fab)
    public void onClick(View view) {
        Snackbar.make(view, R.string.function_not_available, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @AfterViews
    protected void onCreateView() {
        setDetailsActionBar(toolbar);
        City city = new Select().from(City.class).executeSingle();
        ForecastItem item = new Select().from(ForecastItem.class).where("Id = ?", id).executeSingle();
        Log.d("DetailsActivity", item.toString());
        getDetailActivityViewsValues(item, city);
        setDetailActivityViewsValues();
    }

    @UiThread
    protected void setDetailActivityViewsValues() {
        detail_day_textview.setText(day);
        detail_date_textview.setText(date);
        detail_city.setText(city_name);
        detail_high_textview.setText(high_temp);
        detail_low_textview.setText(low_temp);
        detail_humidity_val_textview.setText(humidity_val);
        detail_pressure_val_textview.setText(pressure_val);
        detail_wind_val_textview.setText(wind_val);
        Picasso.with(getApplicationContext()).load(icon).into(detail_icon);
        detail_forecast_textview.setText(capitalizeString(description));
    }

    @UiThread
    protected void getDetailActivityViewsValues(ForecastItem item, City city) {
        day = capitalizeString(getDayName(getApplicationContext(), item.getDt_txt().getTime()));
        date = getFormattedDate(item.getDt_txt());
        city_name = city.getName().toString();
        high_temp = Math.round(item.getMain().getTempMax()) + " °C";
        low_temp =  Math.round(item.getMain().getTempMin()) + " °C";
        humidity_val =  Math.round(item.getMain().getHumidity()) + " %";
        pressure_val =  Math.round(item.getMain().getPressure()) + " hPa";
        wind_speed = Math.round(item.getWind().getSpeed()) + " km/h";
        wind_deg = getFormattedWind(item.getWind().getDeg());
        wind_val = wind_speed + " \t" + wind_deg;
        icon = getArtResourceForWeatherCondition(item.getWeatherData().getWeatherId());
        description = item.getWeatherData().getDescription();
    }

    @UiThread
    protected void setDetailsActionBar(Toolbar toolbar) {
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