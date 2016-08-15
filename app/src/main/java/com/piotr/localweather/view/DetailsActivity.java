package com.piotr.localweather.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.piotr.localweather.R;
import com.piotr.localweather.model.City;
import com.piotr.localweather.model.ForecastItem;
import com.piotr.localweather.model.Main;
import com.piotr.localweather.model.Wind;
import com.piotr.localweather.utils.StringUtils;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.IntegerRes;
import org.androidannotations.annotations.res.StringRes;

import static com.piotr.localweather.utils.DateUtils.getDayName;
import static com.piotr.localweather.utils.DateUtils.getFormattedDate;
import static com.piotr.localweather.utils.ImageUtils.getArtResourceForWeatherCondition;
import static com.piotr.localweather.utils.WeatherUtils.getFormattedWind;

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
    private City city;

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
        city = new Select().from(City.class).executeSingle();
        item = new Select().from(ForecastItem.class).where("id = ?", id).executeSingle();
        if ((item != null) && (city != null)) {
            Log.d(TAG, item.toString());
            getDetailActivityViewsValues(item);
            setDetailActivityViewsValues();
        }
    }

    private void setDetailActivityViewsValues() {
        dDay.setText(day);
        dDate.setText(date);
        dCity.setText(city.getName());
        dHighTemp.setText(high_temp);
        dLowTemp.setText(low_temp);
        dHumidityVal.setText(humidity_val);
        dPressureVal.setText(pressure_val);
        dWindVal.setText(wind_val);
        Picasso.with(getApplicationContext()).load(icon).into(dIcon);
        dDescription.setText(StringUtils.capitalizeString(description));
    }

    private void getDetailActivityViewsValues(ForecastItem item) {
        day = StringUtils.capitalizeString(getDayName(getApplicationContext(), item.getDt_txt().getTime()));
        date = getFormattedDate(item.getDt_txt());
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

    private void setDetailsActivityActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}