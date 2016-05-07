package com.piotr.weatherforpoznan;

import com.piotr.weatherforpoznan.utils.StringUtils;
import com.piotr.weatherforpoznan.view.DetailsActivity;
import com.piotr.weatherforpoznan.view.DetailsActivity_;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by piotr on 22.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DetailsActivityTest {

    @Test
    public void testOnCreateView() throws Exception {
        DetailsActivity activity = Robolectric.setupActivity(DetailsActivity.class);
        assertNotNull(activity);
    }

    @Test
    public void testOnDayChange() {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String day = activity.getResources().getString(R.string.day);
        activity.dDay.setText(day);
        assertTrue(activity.dDay.getText().equals(day));
    }

    @Test
    public void testOnDateChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String date = activity.getResources().getString(R.string.date);
        activity.dDate.setText(date);
        assertTrue(activity.dDate.getText().equals(date));
    }

    @Test
    public void testOnCityNameChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String city_name = activity.getResources().getString(R.string.city_name);
        activity.dCity.setText(city_name);
        assertTrue(activity.dCity.getText().equals(city_name));
    }

    @Test
    public void testOnHighTemperatureChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String high_temp = activity.getResources().getString(R.string.high_temp);
        activity.dHighTemp.setText(high_temp);
        assertTrue(activity.dHighTemp.getText().equals(high_temp));
    }

    @Test
    public void testOnLowTemperatureChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String low_temp = activity.getResources().getString(R.string.low_temp);
        activity.dLowTemp.setText(low_temp);
        assertTrue(activity.dLowTemp.getText().equals(low_temp));
    }

    @Test
    public void testOnHumidityValueChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String humidity_val = activity.getResources().getString(R.string.humidity_val);
        activity.dHumidityVal.setText(humidity_val);
        assertTrue(activity.dHumidityVal.getText().equals(humidity_val));
    }

    @Test
    public void testonPressureValueChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String pressure_val = activity.getResources().getString(R.string.pressure_val);
        activity.dPressureVal.setText(pressure_val);
        assertTrue(activity.dPressureVal.getText().equals(pressure_val));
    }

    @Test
    public void testOnWindValueChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String wind_val = activity.getResources().getString(R.string.wind_val);
        activity.dWindVal.setText(wind_val);
        assertTrue(activity.dWindVal.getText().equals(wind_val));
    }

    @Test
    public void testOnIconDrawableChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        activity.dIcon.setImageDrawable(activity.getResources().getDrawable(R.drawable.art_clear));
        assertTrue(activity.dIcon.getDrawable().equals(activity.getResources()
                .getDrawable(R.drawable.art_clear)));
    }

    @Test
    public void testOnDescriptionChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String description = activity.getResources().getString(R.string.description);
        activity.dDescription.setText(StringUtils.capitalizeString(description));
        assertTrue(activity.dDescription.getText().equals(StringUtils.capitalizeString(description)));
    }


    @Test
    public void testOnClick() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        activity.findViewById(R.id.fab).performClick();
        assertTrue(activity.fab.isClickable());
        activity.fab.performClick();
    }

    //TODO: Write more unit tests

}