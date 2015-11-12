package com.piotr.weatherforpoznan.espresso;

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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by piotr on 17.10.15.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DetailsActivityTest {

    @Rule
    public ActivityTestRule<MainActivity_> mRule = new ActivityTestRule<>(MainActivity_.class);

    private int[] allDetailsViewsIdis = {
            R.id.dItem,
            R.id.dDayAndDate,
            R.id.dDay,
            R.id.dDate,
            R.id.dCity,
            R.id.dTempAndIcon,
            R.id.dTemperature,
            R.id.dHighTemp,
            R.id.dLowTemp,
            R.id.dIconLayout,
            R.id.dIcon,
            R.id.dDescriptionLayout,
            R.id.dDescription,
            R.id.dForecast,
            R.id.dHumidityLayout,
            R.id.dHumidityDesc,
            R.id.dHumidityVal,
            R.id.dPressureLayout,
            R.id.dPressureDesc,
            R.id.dPressureVal,
            R.id.dWindLayout,
            R.id.dWindDesc,
            R.id.dWindVal
    };

    private int[] detailsViewsWithChangingValuesIdis = {
            R.id.dIcon,
            R.id.dDay,
            R.id.dDate,
            R.id.dHighTemp,
            R.id.dLowTemp,
            R.id.dDescription,
            R.id.dHumidityVal,
            R.id.dPressureVal,
            R.id.dWindVal,
            R.id.dIcon,
    };

    private int[] defaultViewsValuesIdis = {
            R.drawable.art_default,
            R.string.day,
            R.string.date,
            R.string.high_temp,
            R.string.low_temp,
            R.string.description,
            R.string.humidity_val,
            R.string.pressure_val,
            R.string.wind_val,
            R.id.dWindVal
    };

    @Before
    public void goToDetailsActivityView() {
        onView(withId(R.id.mListView)).perform(click());
    }

    @Test
    public void check_001_IfActivityNameIsDisplayed() throws InterruptedException {
        onView(withId(R.id.action_bar_details))
                .check(matches(hasDescendant(withChild(withText(R.string.title_activity_details)))));
    }

    @Test
    public void check_002_ifNavigateButtonIsClickable() throws InterruptedException {
        onView(withId(R.id.action_bar_details))
                .check(matches(hasDescendant(withId(R.id.action_bar_back))));
        onView(withId(R.id.action_bar_back)).check(matches(isClickable())).perform(click());
    }

    @Test
    public void check_003_ifAllDetailsActivityViewsAreDisplayed() throws InterruptedException {
        for (int i = 0; i < allDetailsViewsIdis.length; i++)
            onView(withId(allDetailsViewsIdis[i])).check(matches(isDisplayed()));
    }

    @Test
    public void check_04_ifDetailsDayHasNoDefaultValue() throws InterruptedException {
        onView(withId(detailsViewsWithChangingValuesIdis[0])).check(matches(not(withId(defaultViewsValuesIdis[0]))));
        for (int i = 1; i < detailsViewsWithChangingValuesIdis.length; i++)
            onView(withId(detailsViewsWithChangingValuesIdis[i])).check(matches(not(withText(defaultViewsValuesIdis[i]))));
    }

    @Test
    public void check_05_ifSnackbarIsProperlyDisplayed() throws InterruptedException {
        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click());
        onView(withText(R.string.function_not_available)).check(matches(isDisplayed()));
    }
}