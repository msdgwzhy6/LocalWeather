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
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity_> mRule = new ActivityTestRule<>(MainActivity_.class);

    @Test
    public void check_001_ifAppNameIsDisplayed() throws InterruptedException {
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
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
    public void check_005_ifSettingsAreDisplayed() throws InterruptedException {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.title_activity_settings)).check(matches(isDisplayed()));
    }

    @Test
    public void check_006_ifSettingsAreClickable() throws InterruptedException {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.title_activity_settings)).perform(click());
    }

    @Test
    public void check_007_ifSwipeToRefreshIsDisplayed() throws InterruptedException {
        onView(withId(R.id.listview_forecast)).check(matches(isDisplayed()));
    }

    @Test
    public void check_008_IfSwipeToRefreshIsWorking() throws InterruptedException {
        onView(withId(R.id.swiperefresh)).perform(swipeDown()).check(matches(isEnabled()));
    }

    @Test
    public void check_009_IfListViewIsDisplayed() throws InterruptedException {
        onView(withId(R.id.listview_forecast)).check(matches(isDisplayed()));
    }

    @Test
    public void check_010_IfNavigateButtonDoesNotExists() throws InterruptedException {
        onView(withContentDescription("Navigate up")).check((doesNotExist()));
    }
}