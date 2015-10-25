package com.piotr.weatherforpoznan.espresso;

import android.support.test.espresso.ViewAssertion;
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
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
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

    /*PART 1.
    * Checking action bar values and navigate button
    * */
    @Test
    public void check_001_IfActivityNameIsDisplayed() throws InterruptedException {
        onView(withId(R.id.toolbar)).check(matches(withChild(withText(R.string.title_activity_settings))));
    }

    @Test
    public void check_002_IfNavigateButtonIsDisplayed() throws InterruptedException {
        onView(withContentDescription("Navigate up")).check(matches(isDisplayed()));
    }

    @Test
    public void check_003_ifNavigateButtonIsClickable() throws InterruptedException {
        onView(withContentDescription("Navigate up")).check(matches(isClickable())).perform(click());
    }

    /* PART 2.
    * These tests should check if all parts of SettingsActivity are visible.
    * */
    @Test
    public void check_004_IfSettingsCityIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsCity)).check(matches(isDisplayed()));
    }

    @Test
    public void check_005_IfSettingsCityTitleIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsCityTitle)).check(matches(isDisplayed()));
    }

    @Test
    public void check_006_IfSettingsCityNameEditIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsCityNameEdit)).check(matches(isDisplayed()));
    }

    @Test
    public void check_007_IfSettingsMetricsIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsMetrics)).check(matches(isDisplayed()));
    }

    @Test
    public void check_008_IfSettingsMetricsTitleIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsMetricsTitle)).check(matches(isDisplayed()));
    }

    @Test
    public void check_009_IfSettingsMetricsChooseIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsMetricsChoose)).check(matches(isDisplayed()));
    }

    @Test
    public void check_010_IfSettingsMetricsImperialIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsMetricsImperial)).check(matches(isDisplayed()));
    }

    @Test
    public void check_011_IfSettingsMetricsMetricIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsMetricsMetric)).check(matches(isDisplayed()));
    }

    @Test
    public void check_012_IfSettingsEnableNotificationsIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsEnableNotifications)).check(matches(isDisplayed()));
    }

    /* PART 3.
    * These tests should check if all parts of SettingsActivity are working properly
    * */
    @Test
    public void check_013_IfSettingsCityNameEditIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsCityNameEdit)).perform(clearText(),
                typeText(String.valueOf(R.string.city_name)));
    }

    @Test
    public void check_014_checkcUnckeckBoxInEnableNotfications() throws InterruptedException {
        onView(withId(R.id.settingsEnableNotifications)).check(matches(isClickable()))
                .perform(click()).check((ViewAssertion) isChecked());
    }
}