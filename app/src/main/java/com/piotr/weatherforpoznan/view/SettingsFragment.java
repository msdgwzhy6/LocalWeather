package com.piotr.weatherforpoznan.view;

/**
 * @author piotr on 06.08.16.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.piotr.weatherforpoznan.R;

public class SettingsFragment extends PreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "SettingsFragment";
    private static final String KEY_EDIT_TEXT_PREFERENCE = "settings_edit_city_name";
    private static EditTextPreference cityEditTextPreference;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_preferences);
        cityEditTextPreference = (EditTextPreference) findPreference("settings_edit_city_name");
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
        updatePreference(KEY_EDIT_TEXT_PREFERENCE);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        updatePreference(key);
    }

    private void updatePreference(String key) {
        if (key.equals(KEY_EDIT_TEXT_PREFERENCE)) {
            Preference preference = findPreference(key);
            if (preference instanceof EditTextPreference) {
                if (cityEditTextPreference.getText().trim().length() > 0) {
                    cityEditTextPreference.setSummary(getString(R.string
                            .settings_location_city_summary,
                            cityEditTextPreference.getText().toString()));
                } else {
                    cityEditTextPreference.setSummary(R.string.settings_location_city_summary);
                }
            }
        }
    }

}