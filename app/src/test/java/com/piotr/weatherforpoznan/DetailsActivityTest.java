package com.piotr.weatherforpoznan;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by piotr on 22.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DetailsActivityTest {

    DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
    TextView detailsDay = (TextView) activity.findViewById(R.id.detailsDay);
    TextView detailsDate = (TextView) activity.findViewById(R.id.detailsDate);
    TextView detailsCity = (TextView) activity.findViewById(R.id.detailsCity);
    TextView detailsHighTemp = (TextView) activity.findViewById(R.id.detailsHighTemp);
    TextView detailsLowTemp = (TextView) activity.findViewById(R.id.detailsLowTemp);
    TextView detailsHumidityVal = (TextView) activity.findViewById(R.id.detailsHumidityVal);
    TextView detailsPressureVal = (TextView) activity.findViewById(R.id.detailsPressureVal);
    TextView detailsWindVal = (TextView) activity.findViewById(R.id.detailsWindVal);

    ImageView detailsIcon = (ImageView) activity.findViewById(R.id.detailsIcon);

    @Test
    public void testOnCreateView() throws Exception {
        assertThat(activity.toolbar.isClickable());
    }

    @Test
    public void testGetFormattedDate() {
        String date = "Time: " + new Date().getHours() + ":" + new Date().getMinutes();
        detailsDate.setText(date);
    }

    @Test
    public void testSetDetailActivityViewsValues() throws Exception {
        detailsDay.setText(R.string.today);
        detailsDate.setText(R.string.date);
        detailsCity.setText(R.string.day);
        detailsHighTemp.setText(R.string.high_temp);
        detailsLowTemp.setText(R.string.low_temp);
        detailsHumidityVal.setText(R.string.humidity_val);
        detailsPressureVal.setText(R.string.pressure_val);
        detailsWindVal.setText(R.string.wind_val);
        detailsIcon.setImageResource(R.drawable.art_default);
    }

    @Test
    public void testGetDetailActivityViewsValues() throws Exception {
        assertThat(activity.findViewById(R.id.detailsDay).isShown());
        assertThat(activity.findViewById(R.id.detailsDate).isShown());
        assertThat(activity.findViewById(R.id.detailsCity).isShown());
        assertThat(activity.findViewById(R.id.detailsHighTemp).isShown());
        assertThat(activity.findViewById(R.id.detailsLowTemp).isShown());
        assertThat(activity.findViewById(R.id.detailsPressureVal).isShown());
        assertThat(activity.findViewById(R.id.detailsPressureVal).isShown());
        assertThat(activity.findViewById(R.id.detailsWindVal).isShown());
    }

    @Test
    public void testSetDetailsActionBar() throws Exception {
        final DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        activity.toolbar.setTitle(R.string.title_activity_details);
        assertThat(activity.toolbar.getTitle().toString() == "Settings");
        activity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        activity.findViewById(R.id.toolbar).performClick();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        activity.getSupportActionBar().hide();
        assertTrue(activity.getSupportActionBar().isShowing() == false);
    }

    @Test
    public void testOnClick() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        activity.findViewById(R.id.fab).performClick();
        assertThat(activity.fab.isClickable());
        activity.fab.performClick();
    }
}