package com.piotr.weatherforpoznan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Piotr on 30.04.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    @Test
    public void testOnCreateView() throws Exception {
        MainActivity_ activity = Robolectric.setupActivity(MainActivity_.class);
        assertNotNull(activity);
    }
}
