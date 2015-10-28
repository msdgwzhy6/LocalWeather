package com.piotr.weatherforpoznan;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by piotr on 22.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        activity.detailsDay.setText("Hello");
        activity.detailsDate.setText("Hello");
        activity.detailsCity.setText("Hello");
        activity.detailsHighTemp.setText("Hello");
        activity.detailsLowTemp.setText("Hello");
        activity.detailsDate.setText("Hello");

    }

    @Test
    public void testGetDetailActivityViewsValues() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        assertThat(activity.detailsDate.getText() != null);
        assertThat(activity.detailsHighTemp.getText() != null);
        assertThat(activity.detailsLowTemp.getText() != null);
        assertThat(activity.detailsHumidityVal.getText() != null);
        assertThat(activity.detailsPressureVal.getText() != null);
        assertThat(activity.detailsWindVal.getText() != null);
        assertThat(activity.detailsIcon.getDrawable() != null);
        assertThat(activity.detailsDescription.getText() != null);
    }


    @Test
    public void testSetDetailActivityViewsValues() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        assertThat(activity.findViewById(R.id.detailsDay).isShown());
        assertThat(activity.findViewById(R.id.detailsDate).isShown());
        assertThat(activity.findViewById(R.id.detailsCity).isShown());
        assertThat(activity.findViewById(R.id.detailsHighTemp).isShown());
        assertThat(activity.findViewById(R.id.detailsLowTemp).isShown());
        assertThat(activity.findViewById(R.id.detailsPressureVal).isShown());
        assertThat(activity.findViewById(R.id.detailsPressureVal).isShown());
        assertThat(activity.findViewById(R.id.detailsWindVal).isShown());
        assertThat(activity.detailsDate.getText() != null);
        assertThat(activity.detailsHighTemp.getText() != null);
        assertThat(activity.detailsLowTemp.getText() != null);
        assertThat(activity.detailsHumidityVal.getText() != null);
        assertThat(activity.detailsPressureVal.getText() != null);
        assertThat(activity.detailsWindVal.getText() != null);
        assertThat(activity.detailsIcon.getDrawable() != null);
        assertThat(activity.detailsDescription.getText() != null);

    }

    @Test
    public void testGetDetailsActionBar() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        assertThat(activity.day = new Date().toString());
        assertThat(activity.date = String.valueOf(new Date()));
        assertThat(activity.city_name = String.valueOf(activity.findViewById(R.id.detailsCity)));
        assertThat(activity.high_temp = String.valueOf(activity.findViewById(R.id.detailsCity)));
        assertThat(activity.low_temp = String.valueOf(activity.findViewById(R.id.detailsCity)));
        assertThat(activity.humidity_val = String.valueOf(activity.findViewById(R.id.detailsCity)));
        assertThat(activity.pressure_val = String.valueOf(activity.findViewById(R.id.detailsCity)));
        assertThat(activity.wind_speed = String.valueOf(activity.findViewById(R.id.detailsCity)));
        assertThat(activity.wind_deg = String.valueOf(activity.findViewById(R.id.detailsCity)));
        assertThat(activity.wind_val = String.valueOf(activity.findViewById(R.id.detailsWindVal)));
        assertThat(activity.icon = Integer.parseInt(String.valueOf(R.drawable.art_clear)));
        assertThat(activity.description = String.valueOf(activity.findViewById(R.id.detailsCity)));
    }

    @Test
    public void testOnClick() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        activity.findViewById(R.id.fab).performClick();
        assertThat(activity.fab.isClickable());
        activity.fab.performClick();
    }
}