package com.piotr.localweather.view;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.piotr.localweather.R;

import java.util.List;

/**
 * @author piotr on 06.08.16.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.headers_preference, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SettingsFragment.class.getName().equals(fragmentName);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
/*
        // http://stackoverflow.com/questions/26564400/creating-a-preference-screen-with-support-v21-toolbar
*/
        super.onPostCreate(savedInstanceState);
        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        AppBarLayout bar = (AppBarLayout) LayoutInflater.from(this).inflate(R.layout.toolbar_settings, root,
                false);
        root.addView(bar, 0); // insert at top
        Toolbar toolbar = (Toolbar) bar.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}