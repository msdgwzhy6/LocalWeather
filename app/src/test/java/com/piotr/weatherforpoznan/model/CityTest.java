package com.piotr.weatherforpoznan.model;

import android.app.Application;

import com.piotr.weatherforpoznan.BuildConfig;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by piotr on 27.10.15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CityTest {

    City city = Robolectric.buildService(Ciy.class);
    @Test
    public void testGetCityName() throws Exception {
        City city = null;
        city.name = "hello";
        assertThat(city.name != null);
        city.toString();
    }
}