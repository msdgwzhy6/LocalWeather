package com.piotr.weatherforpoznan.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.piotr.weatherforpoznan.MainActivity_;
import com.piotr.weatherforpoznan.R;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by piotr on 17.10.15.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DetailsActivityTest {

    @Rule
    public ActivityTestRule<MainActivity_> mRule = new ActivityTestRule<>(MainActivity_.class);

    private void goToDetailsActivityView() {
        onView(withId(R.id.listview_forecast)).perform(click());
    }

    @Test
    public void check_01_IfActivityNameIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withText(R.string.title_activity_details)).check(matches(isDisplayed()));
    }

    @Test
    public void check_02_IfToolbarIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void check_03_IfNavigateButtonIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withContentDescription("Navigate up")).check(matches(isDisplayed()));
    }

    @Test
    public void check_04_ifNavigateButtonIsClickable() throws InterruptedException {
        goToDetailsActivityView();
        onView(withContentDescription("Navigate up")).check(matches(isClickable()));
    }

    @Test
    public void check_05_ifListViewIsNotDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.listview_forecast)).check(doesNotExist());
    }

    @Test
    public void check_06_ifDayAndDateAreDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.day_and_date)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_07_ifTempAndIconAreDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.temp_and_icon)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_08_ifTemperatureIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.itemTemperature)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_09_ifDetailIconIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detail_icon)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_10_ifDetailIconIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detail_icon)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_11_ifDetailCityIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detail_city)).check(matches(isDisplayed()));
    }

    @Test
    public void check_12_ifForecastIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.forecast)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_13_ifForecastDetailsIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.forecast_details)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_14_ifHumidityIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.humidity)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_15_ifPressureIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.pressure)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_16_ifWindIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.wind)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_17_ifFloatingActionButtonIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.fab)).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    @Test
    public void check_18_ifFloatingActionButtonIsClickable() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.fab)).check(matches(isClickable()));
    }

    @Test
    public void check_19_ifSnackbarIsProperlyDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.fab)).perform(click());
        onView(withText(R.string.function_not_available)).check(matches(isDisplayed()));
    }
}
