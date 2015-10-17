package com.piotr.weatherforpoznan.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.SettingsActivity_;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by piotr on 17.10.15.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity_> mRule = new ActivityTestRule<>(SettingsActivity_.class);

    @Test
    public void check_01_IfActivityNameIsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        onView(withText(R.string.title_activity_settings)).check(matches(isDisplayed()));
    }

}