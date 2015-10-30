package com.piotr.weatherforpoznan.utils;

import com.piotr.weatherforpoznan.BuildConfig;
import com.piotr.weatherforpoznan.MainActivity_;
import com.piotr.weatherforpoznan.R;

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
 * Created by piotr on 27.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class UtilityTest {

    MainActivity_ activity = Robolectric.setupActivity(MainActivity_.class);

    @Test
    public void testGetDayNameTomorrow() throws Exception {
        assertThat((Utility.getDayName(activity.getApplicationContext(),
                System.currentTimeMillis()) + (1000 * 60 * 60 * 24)) == "Tomorrow");
    }

    @Test
    public void testGetDayNameToday() throws Exception {
        assertThat(Utility.getDayName(activity.getApplicationContext(),
                System.currentTimeMillis()) == "Today");
    }


    @Test
    public void testGetFormattedWind() throws Exception {
        assertThat(Utility.getFormattedWind(338).toString() == "N");
        assertThat(Utility.getFormattedWind(23).toString() == "NE");
        assertThat(Utility.getFormattedWind(68).toString() == "E");
        assertThat(Utility.getFormattedWind(113).toString() == "SE");
        assertThat(Utility.getFormattedWind(158).toString() == "S");
        assertThat(Utility.getFormattedWind(204).toString() == "SW");
        assertThat(Utility.getFormattedWind(249).toString() == "W");
        assertThat(Utility.getFormattedWind(294).toString() == "NW");
        assertThat(Utility.getFormattedWind(666).toString() == "Unknown");
    }

    @Test
    public void testGetIconResourceForWeatherCondition() throws Exception {
        assertTrue(Utility.getIconResourceForWeatherCondition(232) == (R.drawable.ic_storm));
        assertTrue(Utility.getIconResourceForWeatherCondition(321) == (R.drawable.ic_light_rain));
        assertTrue(Utility.getIconResourceForWeatherCondition(504) == (R.drawable.ic_rain));
        assertTrue(Utility.getIconResourceForWeatherCondition(511) == (R.drawable.ic_snow));
        assertTrue(Utility.getIconResourceForWeatherCondition(531) == (R.drawable.ic_rain));
        assertTrue(Utility.getIconResourceForWeatherCondition(622) == (R.drawable.ic_snow));
        assertTrue(Utility.getIconResourceForWeatherCondition(760) == (R.drawable.ic_fog));
        assertTrue(Utility.getIconResourceForWeatherCondition(761) == (R.drawable.ic_storm));
        assertTrue(Utility.getIconResourceForWeatherCondition(781) == (R.drawable.ic_storm));
        assertTrue(Utility.getIconResourceForWeatherCondition(800) == (R.drawable.ic_clear));
        assertTrue(Utility.getIconResourceForWeatherCondition(801) == (R.drawable.ic_light_clouds));
        assertTrue(Utility.getIconResourceForWeatherCondition(802) == (R.drawable.ic_cloudy));
        assertTrue(Utility.getIconResourceForWeatherCondition(888) == -1);
    }

    @Test
    public void testGetArtResourceForWeatherCondition() throws Exception {
        assertThat(Utility.getArtResourceForWeatherCondition(232) == (R.drawable.art_storm));
        assertThat(Utility.getArtResourceForWeatherCondition(321) == (R.drawable.art_light_rain));
        assertThat(Utility.getArtResourceForWeatherCondition(504) == (R.drawable.art_rain));
        assertThat(Utility.getArtResourceForWeatherCondition(511) == (R.drawable.art_snow));
        assertThat(Utility.getArtResourceForWeatherCondition(531) == (R.drawable.art_rain));
        assertThat(Utility.getArtResourceForWeatherCondition(622) == (R.drawable.art_snow));
        assertThat(Utility.getArtResourceForWeatherCondition(760) == (R.drawable.art_fog));
        assertThat(Utility.getArtResourceForWeatherCondition(761) == (R.drawable.art_storm));
        assertThat(Utility.getArtResourceForWeatherCondition(800) == (R.drawable.art_clear));
        assertThat(Utility.getArtResourceForWeatherCondition(801) == (R.drawable.art_light_clouds));
        assertThat(Utility.getArtResourceForWeatherCondition(802) == (R.drawable.art_clouds));
        assertThat(Utility.getArtResourceForWeatherCondition(888) == -1);
    }


    @Test
    public void testCapitalizeString() throws Exception {
        Utility.capitalizeString("hhhhhhhhhhhh");

    }
}