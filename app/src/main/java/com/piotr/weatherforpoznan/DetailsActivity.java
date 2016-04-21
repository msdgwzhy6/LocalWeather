package com.piotr.weatherforpoznan;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.model.City;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.piotr.weatherforpoznan.model.Main;
import com.piotr.weatherforpoznan.model.Wind;
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

    final String TAG = "DetailsActivity";
    @ViewById
    Toolbar toolbar;
    @ViewById
    FloatingActionButton fab;
    @ViewById
    TextView dDay;
    @ViewById
    TextView dDate;
    @ViewById
    TextView dCity;
    @ViewById
    TextView dHighTemp;
    @ViewById
    TextView dLowTemp;
    @ViewById
    TextView dDescription;
    @ViewById
    TextView dHumidityVal;
    @ViewById
    TextView dPressureVal;
    @ViewById
    TextView dWindVal;
    @ViewById
    ImageView dIcon;
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

    public static String getFormattedDate(Date date) {
        String formattedDay = new SimpleDateFormat("d MMMM y HH:mm").format(date);
        return formattedDay;
    }

    @Click(R.id.fab)
    public void onClick(View view) {
        Snackbar.make(view, R.string.function_not_available, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @AfterViews
    protected void afterViews() {
        setDetailsActionBar(toolbar);
        City city = new Select().from(City.class).executeSingle();
        ForecastItem item = new Select().from(ForecastItem.class).where("id = ?", id).executeSingle();
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
        dDescription.setText(capitalizeString(description));
    }

    protected void getDetailActivityViewsValues(ForecastItem item, City city) {
        day = capitalizeString(getDayName(getApplicationContext(), item.getDt_txt().getTime()));
        date = getFormattedDate(item.getDt_txt());
        city_name = city.getName().toString();
        //FIXME: Unable to get name of the city.
        //NOTE: For development purposes I changed default city_name in strings.xml
        Main main = item.getMain();
        Wind wind = item.getWind();
        high_temp = Math.round(main.getTempMax()) + " °C";
        low_temp = Math.round(main.getTempMin()) + " °C";
        humidity_val = Math.round(main.getHumidity()) + " %";
        pressure_val = Math.round(main.getPressure()) + " hPa";
        wind_speed = Math.round(wind.getSpeed()) + " km/h";
        wind_deg = getFormattedWind(wind.getDeg());
        wind_val = wind_speed + " \t" + wind_deg;
        icon = getArtResourceForWeatherCondition(item.getWeatherData().getWeatherId());
        description = item.getWeatherData().getDescription();
    }

    @UiThread
    protected void setDetailsActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_details);
        ImageButton button = (ImageButton) findViewById(R.id.action_bar_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}