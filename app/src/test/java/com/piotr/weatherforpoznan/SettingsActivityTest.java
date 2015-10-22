package com.piotr.weatherforpoznan;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertSame;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by piotr on 22.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsActivityTest {

    @Test
    public void testOnCreate() throws Exception {
        SettingsActivity_ activity = Robolectric.buildActivity(SettingsActivity_.class).create().get();
        activity.isTaskRoot();
        activity.getCallingActivity();
        testSetSettingsActionBar();
        assertThat(activity.getTheme().getResources().getLayout(R.layout.activity_settings).isWhitespace());
    }

    @Test
    public void testSetSettingsActionBar() throws Exception {
        SettingsActivity activity = Robolectric.setupActivity(SettingsActivity.class);
        assertThat(activity.getSupportActionBar().isShowing());
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        activity.getSupportActionBar().isShowing();
        activity.getSupportActionBar().openOptionsMenu();
        activity.getSupportActionBar().setTitle(R.string.title_activity_settings);
        assertSame(activity.getSupportActionBar().getTitle(),
                activity.getText(R.string.title_activity_settings));
    }

}