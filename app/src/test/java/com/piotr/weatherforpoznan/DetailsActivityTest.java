package com.piotr.weatherforpoznan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;

import static com.piotr.weatherforpoznan.utils.Utility.capitalizeString;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by piotr on 22.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DetailsActivityTest {

    @Test
    public void testOnCreateView() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        assertThat(activity.toolbar.isClickable());
        testGetDetailActivityViewsValues();
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
        assertTrue(activity.dIcon.getDrawable().equals(activity.getResources().getDrawable(R
                .drawable
                .art_clear)));
    }

    @Test
    public void testOnDescriptionChange() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        String description = activity.getResources().getString(R.string.description);
        activity.dDescription.setText(capitalizeString(description));
        assertTrue(activity.dDescription.getText().equals(capitalizeString(description)));
    }

    @Test
    public void testGetDetailActivityViewsValues() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        assertThat(activity.dDate.getText() != null);
        assertThat(activity.dHighTemp.getText() != null);
        assertThat(activity.dLowTemp.getText() != null);
        assertThat(activity.dHumidityVal.getText() != null);
        assertThat(activity.dPressureVal.getText() != null);
        assertThat(activity.dWindVal.getText() != null);
        assertThat(activity.dIcon.getDrawable() != null);
        assertThat(activity.dDescription.getText() != null);
    }


    @Test
    public void testSetDetailActivityViewsValues() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        assertThat(activity.findViewById(R.id.dDay).isShown());
        assertThat(activity.findViewById(R.id.dDate).isShown());
        assertThat(activity.findViewById(R.id.dCity).isShown());
        assertThat(activity.findViewById(R.id.dHighTemp).isShown());
        assertThat(activity.findViewById(R.id.dLowTemp).isShown());
        assertThat(activity.findViewById(R.id.dPressureVal).isShown());
        assertThat(activity.findViewById(R.id.dPressureVal).isShown());
        assertThat(activity.findViewById(R.id.dWindVal).isShown());
        assertThat(activity.dDate.getText() != null);
        assertThat(activity.dHighTemp.getText() != null);
        assertThat(activity.dLowTemp.getText() != null);
        assertThat(activity.dHumidityVal.getText() != null);
        assertThat(activity.dPressureVal.getText() != null);
        assertThat(activity.dWindVal.getText() != null);
        assertThat(activity.dIcon.getDrawable() != null);
        assertThat(activity.dDescription.getText() != null);

    }

    @Test
    public void testGetDetailsActionBar() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        assertThat(activity.day = new Date().toString());
        assertThat(activity.date = String.valueOf(new Date()));
        assertThat(activity.city_name = String.valueOf(activity.findViewById(R.id.dCity)));
        assertThat(activity.high_temp = String.valueOf(activity.findViewById(R.id.dCity)));
        assertThat(activity.low_temp = String.valueOf(activity.findViewById(R.id.dCity)));
        assertThat(activity.humidity_val = String.valueOf(activity.findViewById(R.id.dCity)));
        assertThat(activity.pressure_val = String.valueOf(activity.findViewById(R.id.dCity)));
        assertThat(activity.wind_speed = String.valueOf(activity.findViewById(R.id.dCity)));
        assertThat(activity.wind_deg = String.valueOf(activity.findViewById(R.id.dCity)));
        assertThat(activity.wind_val = String.valueOf(activity.findViewById(R.id.dWindVal)));
        assertThat(activity.icon = Integer.parseInt(String.valueOf(R.drawable.art_clear)));
        assertThat(activity.description = String.valueOf(activity.findViewById(R.id.dCity)));
    }

    @Test
    public void testOnClick() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        activity.findViewById(R.id.fab).performClick();
        assertThat(activity.fab.isClickable());
        activity.fab.performClick();
    }

    @Test
    public void testExtras() throws Exception {
/*        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        activity.setDetailActivityViewsValues();*/

    }
}