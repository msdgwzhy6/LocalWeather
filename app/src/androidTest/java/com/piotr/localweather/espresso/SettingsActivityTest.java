package com.piotr.localweather.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.piotr.localweather.R;
import com.piotr.localweather.view.SettingsActivity;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author piotr on 08.08.16.
 */

@Ignore
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> mRule = new ActivityTestRule<>(SettingsActivity.class);

    @Test
    public void checkIfToolbarIsProperlyDisplayed() throws InterruptedException {
        onView(withText(R.string.action_settings)).check(matches(withParent(withId(R.id.toolbar))));
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }
}
