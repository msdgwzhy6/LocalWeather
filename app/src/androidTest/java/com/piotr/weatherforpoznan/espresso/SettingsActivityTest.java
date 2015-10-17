package com.piotr.weatherforpoznan.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestRunner;

import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.SettingsActivity_;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by piotr on 17.10.15.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsActivityTest extends InstrumentationTestRunner {

    @Rule
    public ActivityTestRule<SettingsActivity_> mRule = new ActivityTestRule<>(SettingsActivity_.class);


    @Test
    public void check_01_IfActivityNameIsDisplayed() throws InterruptedException {
        onView(withText(R.string.title_activity_settings)).check(matches(isDisplayed()));
    }

    @Test
    public void check_02_IfToolbarIsDisplayed() throws InterruptedException {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void check_03_IfNavigateButtonIsDisplayed() throws InterruptedException {
        onView(withContentDescription("Navigate up")).check(matches(isDisplayed()));
    }

    @Test
    public void check_04_ifNavigateButtonIsClickable() throws InterruptedException {
        onView(withContentDescription("Navigate up")).check(matches(isClickable()));
    }

    @Test
    public void check_05_ifListViewIsNotDisplayed() throws InterruptedException {
        onView(withId(R.id.listview_forecast)).check(doesNotExist());
    }
}