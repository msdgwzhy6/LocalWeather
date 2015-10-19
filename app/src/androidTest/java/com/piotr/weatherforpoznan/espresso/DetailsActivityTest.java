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
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
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

    /* PART 1.
    * Checking action bar and navigation
    * */
    @Test
    public void check_001_IfActivityNameIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.toolbar)).check(matches(withChild(withText(R.string.title_activity_details))));
    }

    @Test
    public void check_002_IfNavigateButtonIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withContentDescription("Navigate up")).check(matches(isDisplayed()));
    }

    @Test
    public void check_003_ifNavigateButtonIsClickable() throws InterruptedException {
        goToDetailsActivityView();
        onView(withContentDescription("Navigate up")).check(matches(isClickable())).perform(click());
    }

    /* PART 2.
    * These tests should check if all parts of DetailsActivity are visible.
    * */

    @Test
    public void check_004_ifListViewIsNotDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.listview_forecast)).check(doesNotExist());
    }

    @Test
    public void check_005_ifDayAndDateAreDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsDayAndDate)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_006_ifTempAndIconAreDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsTempAndIcon)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_007_ifTemperatureIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsTemperature)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_008_ifDetailIconIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsIcon)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_009_ifDetailIconIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsIcon)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_010_ifDetailCityIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsCity)).check(matches(isDisplayed()));
    }

    @Test
    public void check_011_ifForecastIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsDescriptionLayout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_012_ifForecastDetailsIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsForecast)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_013_ifHumidityIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsHumidityLayout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_014_ifPressureIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsPressureLayout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_015_ifWindIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsWindLayout)).check(matches(isCompletelyDisplayed()));
    }

    /*PART 3.
    * Checking action bar values and navigate button
    * */

    @Test
    public void check_016_ifFloatingActionButtonIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.fab)).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    @Test
    public void check_017_ifFloatingActionButtonIsClickable() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.fab)).check(matches(isClickable()));
    }

    @Test
    public void check_018_ifSnackbarIsProperlyDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.fab)).perform(click());
        onView(withText(R.string.function_not_available)).check(matches(isDisplayed()));
    }
}