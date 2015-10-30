package com.piotr.weatherforpoznan;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by piotr on 22.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SettingsActivityTest {

    @Test
    public void testOnCreate() throws Exception {
        SettingsActivity_ activity = Robolectric.buildActivity(SettingsActivity_.class).create().get();
        activity.setContentView(R.layout.activity_details);
        assertThat(activity.getTheme().getResources().getLayout(R.layout.activity_settings).isWhitespace());
        assertThat(is(activity.findViewById(R.id.toolbar).getVisibility() != View.GONE));
        activity.setSettingsActionBar(activity.toolbar);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Test
    public void testSetSettingsActionBar() throws Exception {
        final SettingsActivity_ activity = Robolectric.setupActivity(SettingsActivity_.class);
        testOnCreate();
        activity.setContentView(R.layout.activity_settings);
        activity.setSupportActionBar(activity.toolbar);
        assertTrue(activity.getSupportActionBar().getTitle() != null);
        assertThat(activity.toolbar.isShown());

        activity.toolbar.setTitle(R.string.title_activity_settings);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
                assertTrue(activity.isFinishing());
            }
        });
        activity.toolbar.performClick();
    }
}