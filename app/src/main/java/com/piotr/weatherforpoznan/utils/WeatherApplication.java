package com.piotr.weatherforpoznan.utils;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.app.Application;
import com.activeandroid.query.Select;
import com.crashlytics.android.Crashlytics;
import com.piotr.weatherforpoznan.model.City;
import com.piotr.weatherforpoznan.model.ForecastItem;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EApplication;

import java.util.List;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Piotr on 06.10.2015.
 */
@EApplication
public class WeatherApplication extends Application {

    public static List<Model> getObjectsList() {
        return new Select().from(ForecastItem.class).execute();
    }

    public static List<Model> getCityList() {
        return new Select().from(City.class).execute();
    }

        @AfterInject
        public void onCreateApplication(){
        Fabric.with(this, new Crashlytics());
        ActiveAndroid.initialize(this);
    }
}
