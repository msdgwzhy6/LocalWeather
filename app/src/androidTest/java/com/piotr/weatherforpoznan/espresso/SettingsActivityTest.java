package com.piotr.weatherforpoznan.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestRunner;

import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.SettingsActivity_;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by piotr on 17.10.15.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsActivityTest extends InstrumentationTestRunner {

    @Rule
    public ActivityTestRule<SettingsActivity_> mRule = new ActivityTestRule<>(SettingsActivity_.class);
    private int[] settingsViewsIdis = {R.id.settingsCity,
            R.id.settingsCityTitle,
            R.id.settingsCityNameEdit,
            R.id.settingsTemperatureFormat,
            R.id.settingsTemperatureFormatTitle,
            R.id.settingsTemperatureFormatChoose,
            R.id.settingsTemperatureFormatImperial,
            R.id.settingsTemperatureFormatMetric,
            R.id.settingsEnableNotifications};

    @Before
    public void setup() {
        closeSoftKeyboard();
    }

    @Test
    public void check_001_IfActivityNameIsDisplayed() throws InterruptedException {
        onView(withId(R.id.toolbar)).check(matches(withChild(withText(R.string.title_activity_settings))));
    }

    @Test
    public void check_002_IfNavigateButtonIsClickable() throws InterruptedException {
        onView(withContentDescription("Navigate up")).check(matches(isDisplayed()))
                .check(matches(isClickable())).perform(longClick());
    }

    @Test
    public void check_003_IfSettingsCityIsDisplayed() throws InterruptedException {
        for (int i = 0; i < settingsViewsIdis.length; i++)
            onView(withId(settingsViewsIdis[i])).check(matches(isDisplayed()));
    }

    @Test
    public void check_013_ifSettingsCityNameEditIsDisplayed() throws InterruptedException {
        onView(withId(R.id.settingsCityNameEdit)).perform(clearText(),
                typeText(String.valueOf(R.string.city_name)));
    }

    @Test
    public void check_014_ifEnableNotificationsCheckBoxisUnchecked() throws InterruptedException {
        onView(withId(R.id.settingsEnableNotifications)).check(matches(not(isChecked())));
    }
}