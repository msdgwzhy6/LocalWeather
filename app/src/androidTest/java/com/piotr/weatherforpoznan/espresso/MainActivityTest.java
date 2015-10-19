package com.piotr.weatherforpoznan.espresso;

/**
 * Created by Piotr on 16.10.2015.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.piotr.weatherforpoznan.MainActivity_;
import com.piotr.weatherforpoznan.R;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    final static int POSITION = 0;


    @Rule
    public ActivityTestRule<MainActivity_> mRule = new ActivityTestRule<>(MainActivity_.class);


    /*PART 1.
    * Checking action bar values and overflow menu items
    * */
    @Test
    public void check_001_ifAppNameIsDisplayed() throws InterruptedException {
        onView(withId(R.id.action_bar)).check(matches(withChild(withText(R.string.app_name))));
    }

    @Test
    public void check_002_ifOverFlowMenuButtonIsDisplayed() throws InterruptedException {
        onView(withContentDescription("More options")).check(matches(isDisplayed()));
    }

    @Test
    public void check_003_ifOverFlowMenuButtonIsEnabled() throws InterruptedException {
        onView(withContentDescription("More options")).check(matches(isEnabled()));
    }

    @Test
    public void check_004_ifOverFlowMenuButtonIsClickable() throws InterruptedException {
        onView(withContentDescription("More options")).check(matches(isClickable()));
    }

    @Test
    public void check_005_ifSettingsMenuItemIsDisplayed() throws InterruptedException {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.title_activity_settings)).check(matches(isDisplayed()));
    }

    @Test
    public void check_006_ifClickOnSettingsMenuItemTakesMeToSettingsActivity() throws InterruptedException {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.title_activity_settings)).perform(click());
        onView(withContentDescription("Navigate up")).check(matches(isDisplayed()));
    }

    @Test
    public void check_007_IfSwipeToRefreshIsWorking() throws InterruptedException {
        onView(withId(R.id.swiperefresh)).check(matches(isEnabled())).perform(swipeDown());
    }

    @Test
    public void check_008_IfSwipingUpTakesMeToEndOfList() throws InterruptedException {
        onView(withId(R.id.listview_forecast)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp());
    }

    @Test
    public void check_009_IfListViewIsDisplayed() throws InterruptedException {
        onView(withId(R.id.listview_forecast)).check(matches(isDisplayed()));
    }

    @Test
    public void check_010_IfListViewIsClickable() throws InterruptedException {
        onView(withId(R.id.listview_forecast)).check(matches(isClickable()));
    }

    @Test
    public void check_011_IfClickOnListViewTakesMeToDetailsActivity() throws InterruptedException {
        onView(withId(R.id.listview_forecast)).perform(click());
        onView(withText(R.string.title_activity_details)).check(matches(isDisplayed()));
    }

    @Test
    public void check_012_ifListViewTakesMeToDetailsActivity() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(POSITION).perform(click());
        onView(withText(R.string.title_activity_details)).check(matches(isDisplayed()));
    }

    @Test
    public void check_013_IfNavigateButtonDoesNotExists() throws InterruptedException {
        onView(withContentDescription("Navigate up")).check((doesNotExist()));
    }

    /* PART 2.
    * These tests should check if all parts of ListView.Item item are visible.
    * */

    @Test
    public void check_014_ifForecastItemIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(POSITION).
                onChildView(withId(R.id.forecastItem)).check(matches(isDisplayed()));
    }

    @Test
    public void check_015_ifForecastIconIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(POSITION).
                onChildView(withId(R.id.forecastIcon)).check(matches(isDisplayed()));
    }

    @Test
    public void check_016_ifItemTableIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(POSITION).
                onChildView(withId(R.id.itemTable)).check(matches(isDisplayed()));
    }

    @Test
    public void check_017_ifItemDateIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(POSITION).
                onChildView(withId(R.id.itemtDate)).check(matches(isDisplayed()));
    }

    @Test
    public void check_018_ifItemDescriptionIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(POSITION).
                onChildView(withId(R.id.itemDescription)).check(matches(isDisplayed()));
    }

    @Test
    public void check_019_ifForecastItemTemperatureIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(POSITION).
                onChildView(withId(R.id.itemTemperature)).check(matches(isDisplayed()));
    }

    @Test
    public void check_020_ifForecastItemTemperatureMaxIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(POSITION).
                onChildView(withId(R.id.itemTemperatureMax)).check(matches(isDisplayed()));
    }

    @Test
    public void check_021_ifForecastItemTemperatureMinIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(POSITION).
                onChildView(withId(R.id.itemTemperatureMin)).check(matches(isDisplayed()));
    }
}