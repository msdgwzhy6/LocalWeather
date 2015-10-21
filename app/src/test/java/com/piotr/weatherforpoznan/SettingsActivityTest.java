package com.piotr.weatherforpoznan;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsActivityTest {

    SettingsActivity activity = Robolectric.setupActivity(SettingsActivity.class);
    String appName = activity.getApplicationContext().getResources().getString(R.string.app_name);

    @Test
    public void check_01_ifAppNameIsRight() throws Exception {
        assertThat(activity.isTaskRoot());
    }

    @Test
    public void check_02_ifSupportActionBarIsShowing() throws Exception {
        assertThat(activity.getSupportActionBar().isShowing());
    }

    @Test
    public void check_03_ifActivityNameIsRight() throws Exception {
        assertThat(activity.getSupportActionBar().getTitle().toString()).isEqualTo(appName);
    }

}