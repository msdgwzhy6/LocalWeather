package com.piotr.weatherforpoznan;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.model.settings.SettingsValues;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_settings)
public class SettingsActivity extends AppCompatActivity {

    @ViewById
    EditText settingsCityNameEdit;

    @ViewById
    Toolbar toolbar;

    @Click(R.id.settingsEnableNotifications)
    public void enableWeatherNotifications() {
        Snackbar.make(getCurrentFocus(), R.string.function_not_available, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @AfterViews
    protected void onCreate() {
        setSettingsActionBar(toolbar);
        SettingsValues settingsValues = new Select().from(SettingsValues.class).executeSingle();
        //Log.d("SettingsValues",settingsValues.toString());      //Not working!
        changeDefaultCity();
    }

    @UiThread
    protected void setSettingsActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle(R.string.title_activity_settings);
    }

    @Background
    public void changeDefaultCity() {
        settingsCityNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ActiveAndroid.beginTransaction();
                try {
                    for (int i = 0; i < 100; i++) {
                        SettingsValues settingsValues = new SettingsValues();
                        settingsValues.city = String.valueOf(s);
                        settingsValues.save();
                    }
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                    Snackbar.make(getCurrentFocus(), R.string.operation_successful, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}