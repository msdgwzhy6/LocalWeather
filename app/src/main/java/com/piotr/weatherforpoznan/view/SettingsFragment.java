package com.piotr.weatherforpoznan.view;

/**
 * @author piotr on 06.08.16.
 */

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.piotr.weatherforpoznan.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_preferences);
    }

}