package com.piotr.weatherforpoznan;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.piotr.weatherforpoznan.model.settings.SettingsValues;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

@EActivity(R.layout.activity_settings)
public class SettingsActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @ViewById
    TextView settingsCityTitle;

    @ViewById
    EditText settingsCityNameEdit;

    @ViewById
    RadioButton settingsTimeFormat24H;

    @ViewById
    RadioButton settingsTimeFormat12H;

    @ViewById
    RadioButton settingsTemperatureFormatImperial;

    @ViewById
    RadioButton settingsTemperatureFormatMetric;


    @Click(R.id.settingsEnableNotifications)
    public void enableWeatherNotifications() {
        Snackbar.make(getCurrentFocus(), R.string.function_not_available, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Click(R.id.action_bar_back)
    public void onClickButton() {
        onBackPressed();
    }

    @AfterViews
    protected void onCreate() {
        setSettingsActionBar(toolbar);

        changeDefaultCity();
        changeTimeFormat12h();
        changeTimeFormat24h();
        changeTemperatureFormatMetric();
        changeTemperatureFormatImperial();
    }

    @UiThread
    protected void setSettingsActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_settings);
        ImageButton button = (ImageButton) findViewById(R.id.action_bar_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
                    BackgroundExecutor.cancelAll("save_city", true);
                    saveCity(s.toString());
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                    Snackbar.make(getCurrentFocus(), R.string.operation_successful, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Background(delay = 1000, id = "save_city")
    void saveCity(String s) {
        SettingsValues settingsValues = new SettingsValues();
        settingsValues.setCity(String.valueOf(s));
        settingsValues.save();
    }

    @Background
    public void changeTimeFormat12h() {
        settingsTimeFormat12H.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ActiveAndroid.beginTransaction();
                try {
                    SettingsValues settingsValues = new SettingsValues();
                    settingsValues.setTimeFormat12h(settingsTimeFormat12H.isChecked());
                    settingsValues.save();
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                    Snackbar.make(getCurrentFocus(), R.string.operation_successful, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Background
    public void changeTimeFormat24h() {
        settingsTimeFormat24H.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ActiveAndroid.beginTransaction();
                try {
                    SettingsValues settingsValues = new SettingsValues();
                    settingsValues.setTimeFormat12h(settingsTimeFormat24H.isChecked());
                    settingsValues.save();
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                    Snackbar.make(getCurrentFocus(), R.string.operation_successful, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Background
    public void changeTemperatureFormatImperial() {
        settingsTemperatureFormatImperial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ActiveAndroid.beginTransaction();
                try {
                    SettingsValues settingsValues = new SettingsValues();
                    settingsValues.setTimeFormat12h(settingsTemperatureFormatImperial.isChecked());
                    settingsValues.save();
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                    Snackbar.make(getCurrentFocus(), R.string.operation_successful, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Background
    public void changeTemperatureFormatMetric() {
        settingsTemperatureFormatMetric.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ActiveAndroid.beginTransaction();
                try {
                    SettingsValues settingsValues = new SettingsValues();
                    settingsValues.setTimeFormat12h(settingsTemperatureFormatMetric.isChecked());
                    settingsValues.save();
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