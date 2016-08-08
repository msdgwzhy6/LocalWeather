package com.piotr.weatherforpoznan.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.model.City;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.piotr.weatherforpoznan.model.Main;
import com.piotr.weatherforpoznan.model.Wind;
import com.piotr.weatherforpoznan.utils.StringUtils;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.IntegerRes;
import org.androidannotations.annotations.res.StringRes;

import static com.piotr.weatherforpoznan.utils.DateUtils.getDayName;
import static com.piotr.weatherforpoznan.utils.DateUtils.getFormattedDate;
import static com.piotr.weatherforpoznan.utils.ImageUtils.getArtResourceForWeatherCondition;
import static com.piotr.weatherforpoznan.utils.WeatherUtils.getFormattedWind;

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

    private ForecastItem item;

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
                R.string.share_message_subject, getFormattedDate(item.getDt_txt())));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string
                .share_message_choose)));
    }

    @AfterViews
    protected void initialize() {
        setDetailsActivityActionBar();
        City city = new Select().from(City.class).executeSingle();
        item = new Select().from(ForecastItem.class).where("id = ?", id).executeSingle();
        if ((item != null) && (city != null)) {
            Log.d(TAG, item.toString());
            getDetailActivityViewsValues(item, city);
            setDetailActivityViewsValues();
        }
    }

    @UiThread
    protected void setDetailActivityViewsValues() {
        dDay.setText(day);
        dDate.setText(date);
        dCity.setText(city_name);
        dHighTemp.setText(high_temp);
        dLowTemp.setText(low_temp);
        dHumidityVal.setText(humidity_val);
        dPressureVal.setText(pressure_val);
        dWindVal.setText(wind_val);
        Picasso.with(getApplicationContext()).load(icon).into(dIcon);
        dDescription.setText(StringUtils.capitalizeString(description));
    }

    void getDetailActivityViewsValues(ForecastItem item, City city) {
        day = StringUtils.capitalizeString(getDayName(getApplicationContext(), item.getDt_txt().getTime()));
        date = getFormattedDate(item.getDt_txt());
        city_name = city.getName();
        Main main = item.getMain();
        Wind wind = item.getWind();
        high_temp = Math.round(main.getTempMax()) + " °C";
        low_temp = Math.round(main.getTempMin()) + " °C";
        humidity_val = Math.round(main.getHumidity()) + " %";
        pressure_val = Math.round(main.getPressure()) + " hPa";
        String wind_speed = Math.round(wind.getSpeed()) + " km/h";
        String wind_deg = getFormattedWind(wind.getDeg());
        wind_val = wind_speed + " \t" + wind_deg;
        icon = getArtResourceForWeatherCondition(item.getWeatherData().getWeatherId());
        description = item.getWeatherData().getDescription();
    }

    @UiThread
    protected void setDetailsActivityActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}