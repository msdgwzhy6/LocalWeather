package com.piotr.weatherforpoznan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;

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
    public void testGetFormattedDate() {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        activity.dDay.setText("Hello");
        activity.dDate.setText("Hello");
        activity.dCity.setText("Hello");
        activity.dHighTemp.setText("Hello");
        activity.dLowTemp.setText("Hello");
        activity.dDate.setText("Hello");

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
}