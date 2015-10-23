package com.piotr.weatherforpoznan;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by piotr on 23.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Test
    public void testFirstMenuItemCalled() throws Exception {

    }

    @Test
    public void testSetWeatherFragments() throws Exception {

    }

    @Test
    public void testSetMainActivityActionBar() throws Exception {
        MainActivity_ activity = Robolectric.setupActivity(MainActivity_.class);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        activity.getSupportActionBar().setTitle(R.string.app_name);
        activity.getSupportActionBar().setSubtitle(R.string.geo_coord);
    }

    @Test
    public void testGetGeoCoordinates() throws Exception {
        MainActivity_ activity = Robolectric.setupActivity(MainActivity_.class);
        activity.latitude = String.valueOf(activity.getText(R.string.latitude));
        assertThat(activity.latitude != null);
        activity.longitude = String.valueOf(activity.getText(R.string.longitude));
        assertThat(activity.latitude != null);
        assertThat(activity.longitude != null);
        activity.geo_coord = activity.latitude + " | " + activity.longitude;
        assertTrue(activity.geo_coord != null);

    }
}