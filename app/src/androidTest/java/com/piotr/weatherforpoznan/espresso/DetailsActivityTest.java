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
import static android.support.test.espresso.action.ViewActions.longClick;
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
    public void check_004_ifDetailsItemIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsItem)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_005_ifDetailsDayAndDateIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsDayAndDate)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_006_ifDetailsDayIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsDay)).check(matches(isDisplayed()));
    }

    @Test
    public void check_007_ifDetailsDateIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsDate)).check(matches(isDisplayed()));
    }

    @Test
    public void check_008_ifDetailsCityIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsCity)).check(matches(isDisplayed()));
    }

    @Test
    public void check_009_ifDetailsTempAndIconIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsTempAndIcon)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_010_ifDetailsTemperatureIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsTemperature)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_011_ifDetailsHighTemperatureIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsHighTemp)).check(matches(isDisplayed()));
    }

    @Test
    public void check_012_ifDetailsLowTemperatureIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsLowTemp)).check(matches(isDisplayed()));
    }

    @Test
    public void check_013_ifDetailIconLayoutIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsIconLayout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_014_ifDetailIconIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsIcon)).check(matches(isDisplayed()));
    }

    @Test
    public void check_015_ifDetailsDescriptionLayoutIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsDescriptionLayout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_016_ifDetailsDescriptionIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsDescription)).check(matches(isDisplayed()));
    }

    @Test
    public void check_017_ifDetailsForecastIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsForecast)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_018_ifDetailsHumidityLayoutIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsHumidityLayout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_019_ifDetailsHumidityDescriptionIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsHumidityDesc)).check(matches(isDisplayed()));
    }

    @Test
    public void check_020_ifDetailsHumidityValueIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsHumidityVal)).check(matches(isDisplayed()));
    }

    @Test
    public void check_021_ifDetailsPressureLayoutIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsPressureLayout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_022_ifDetailsPressureDescriptionIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsPressureDesc)).check(matches(isDisplayed()));
    }

    @Test
    public void check_023_ifDetailsPressureValueIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsPressureVal)).check(matches(isDisplayed()));
    }

    @Test
    public void check_024_ifDetailsWindLayoutIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsWindLayout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void check_025_ifDetailsWindDescriptionIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsWindDesc)).check(matches(isDisplayed()));
    }

    @Test
    public void check_026_ifDetailsWindValueIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.detailsWindVal)).check(matches(isDisplayed()));
    }

    /*PART 3.
    * Checking action bar values and navigate button
    * */
    @Test
    public void check_027_ifFloatingActionButtonIsDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.fab)).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    @Test
    public void check_028_ifFloatingActionButtonIsClickable() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.fab)).check(matches(isClickable())).perform(longClick());
    }

    @Test
    public void check_029_ifSnackbarIsProperlyDisplayed() throws InterruptedException {
        goToDetailsActivityView();
        onView(withId(R.id.fab)).perform(click());
        onView(withText(R.string.function_not_available)).check(matches(isDisplayed()));
    }
}