package com.piotr.weatherforpoznan;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
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
    }

    @Test
    public void testSetDetailActivityViewsValues() throws Exception {
        //assertThat(activity.getApplication().get(R.id.detailsDay).toString()).contains(day);

    }

    @Test
    public void testGetDetailActivityViewsValues() throws Exception {

    }

    @Test
    public void testSetDetailsActionBar() throws Exception {

    }

    @Test
    public void testOnClick() throws Exception {
        DetailsActivity_ activity = Robolectric.setupActivity(DetailsActivity_.class);
        activity.findViewById(R.id.fab).performClick();
        assertThat(activity.fab.isClickable());
        activity.fab.performClick();
        assertEquals(activity.getText(R.string.function_not_available), String.valueOf(R.string.function_not_available));
    }
}