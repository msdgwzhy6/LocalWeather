package com.piotr.weatherforpoznan.espresso;

/**
 * Created by Piotr on 16.10.2015.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.piotr.weatherforpoznan.MainActivity_;
import com.piotr.weatherforpoznan.R;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Random;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity_> mRule = new ActivityTestRule<>(MainActivity_.class);

    @Before
    public void setup() {
        closeSoftKeyboard();
    }

    private int[] settingsViewsIdis = {R.id.settingsCity,
            R.id.settingsCityTitle,
            R.id.settingsCityNameEdit,
            R.id.settingsTemperatureFormat,
            R.id.settingsTemperatureFormatTitle,
            R.id.settingsTemperatureFormatChoose,
            R.id.settingsTemperatureFormatImperial,
            R.id.settingsTemperatureFormatMetric,
            R.id.settingsEnableNotifications};

    private int getRandomPosition() {
        Random rand = new Random();
        return rand.nextInt(30) + 1;
    }

    @Test
    public void check_001_ifAppNameIsDisplayed() throws InterruptedException {
        onView(withId(R.id.action_bar)).check(matches(withChild(withText(R.string.app_name))));
    }

    @Test
    public void check_002_ifOverFlowMenuButtonIsClickable() throws InterruptedException {
        onView(withContentDescription("More options")).check(matches(isClickable()));
    }

    @Test
    public void check_003_ifSettingsMenuItemIsDisplayed() throws InterruptedException {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.title_activity_settings))
                .check(matches(isDisplayed())).perform(click());
        onView(withContentDescription("Navigate up")).check(matches(isDisplayed()));
    }

    @Test
    public void check_005_IfSwipeToRefreshIsWorking() throws InterruptedException {
        onView(withId(R.id.swiperefresh)).check(matches(isEnabled())).perform(swipeDown());
    }

    @Test
    public void check_006_IfSwipingUpTakesMeToEndOfList() throws InterruptedException {
        onView(withId(R.id.listview_forecast)).check(matches(isDisplayed()))
                .perform(swipeUp(), swipeUp(), swipeUp(), swipeUp());
    }

    @Test
    public void check_007_IfClickOnListViewTakesMeToDetailsActivity() throws InterruptedException {
        onView(withId(R.id.listview_forecast)).perform(click()).
                check(matches(withText(R.string.title_activity_details)));
    }

    @Test
    public void check_008_ifForecastItemIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItem)).check(matches(isDisplayed()));
    }

    @Test
    public void check_009_ifForecastItemIconIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemIcon)).check(matches(isDisplayed()));
    }

    @Test
    public void check_010_ifForecastItemTableIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTable)).check(matches(isDisplayed()));
    }

    @Test
    public void check_011_ifForecastItemDateIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemDate)).check(matches(isDisplayed()));
    }

    @Test
    public void check_012_ifForecastItemDescriptionIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemDescription)).check(matches(isDisplayed()));
    }

    @Test
    public void check_013_ifForecastItemTemperatureIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperature)).check(matches(isDisplayed()));
    }

    @Test
    public void check_014_ifForecastItemTemperatureMaxIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperatureMax)).check(matches(isDisplayed()));
    }

    @Test
    public void check_015_ifForecastItemTemperatureMinIsDisplayed() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperatureMin)).check(matches(isDisplayed()));
    }

    @Test
    public void check_016_ifForecastIconHasNoDefaultDrawable() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemIcon)).check(matches(not(withId(R.mipmap.ic_launcher))));
    }

    @Test
    public void check_017_ifForecastItemDateHasNoDefaultValue() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemDate)).check(matches(not(withText(R.string.date))));
    }

    @Test
    public void check_018_ifForecastItemDescriptionHasNoDefaultValue() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemDescription)).check(matches(not(withText(R.string.description))));
    }

    @Test
    public void check_019_ifForecastItemTemperatureMaxHasNoDefaultValue() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperatureMax)).check(matches(not(withText(R.string.high_temp))));
    }

    @Test
    public void check_020_ifForecastItemTemperatureMinHasNoDefaultValue() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.listview_forecast)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperatureMin)).check(matches(not(withText(R.string.low_temp))));
    }
}