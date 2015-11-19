package com.piotr.weatherforpoznan.espresso;

/**
 * @author Piotr on 16.10.2015.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.piotr.weatherforpoznan.MainActivity_;
import com.piotr.weatherforpoznan.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity_> mRule = new ActivityTestRule<>(MainActivity_.class);

    private int getRandomPosition() {
        Random rand = new Random();
        return rand.nextInt(30) + 1;
    }

    @Test
    public void checkIfMainActivityActionBarHasAppNameString() throws InterruptedException {
        onView(withId(R.id.action_bar_main))
                .check(matches(hasDescendant(withText(R.string.app_name))));
    }

    @Test
    public void refreshForecastDataUsingSwipeDown() throws InterruptedException {
        onView(withId(R.id.swipeRefresh)).check(matches(isEnabled())).perform(swipeDown());
    }

    @Test
    public void swipeToTheLastElementOfForecastAdapter() throws InterruptedException {
        onView(withId(R.id.mListView)).check(matches(isDisplayed()))
                .perform(swipeUp(), swipeUp(), swipeUp(), swipeUp());
    }

    @Test
    /* WARNING:
    * Going through the activities may cause unexpected cautions. Use it wisely!
    * */
    public void checkIfClickingOnItWillTakeMeToDetailsActivity()
            throws InterruptedException {
        onView(withId(R.id.mListView)).check(matches(isClickable())).perform(click());
        onView(withId(R.id.action_bar_details))
                .check(matches(withChild(withChild(withText(R.string.title_activity_details)))));
    }

    @Test
    public void checkIfForecastItemIsDisplayedOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItem)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemIconIsDisplayedOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemIcon)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemTableIsDisplayedOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTable)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemDateIsDisplayedOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemDate)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemDescriptionIsDisplayedOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemDescription)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemTemperatureIsDisplayedOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperature)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemTemperatureMaxIsDisplayedOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperatureMax)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemTemperatureMinIsDisplayedOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperatureMin)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemIconHasADefaultValueOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemIcon))
                .check(matches(not(withId(R.mipmap.ic_launcher))));
    }

    @Test
    public void checkIfForecastItemDateHasADefaultValueOnForecastAdapter()
            throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemDate))
                .check(matches(not(withText(R.string.date))));
    }

    @Test
    public void checkIfForecastItemDescriptionHasADefaultValue() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemDescription))
                .check(matches(not(withText(R.string.description))));
    }

    @Test
    public void checkIfForecastItemTemperatureMaxHasADefaultValue() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperatureMax))
                .check(matches(not(withText(R.string.high_temp))));
    }

    @Test
    public void checkIForecastItemTemperatureMinHasADefaultValue() throws InterruptedException {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(getRandomPosition()).
                onChildView(withId(R.id.forecastItemTemperatureMin))
                .check(matches(not(withText(R.string.low_temp))));
    }
}