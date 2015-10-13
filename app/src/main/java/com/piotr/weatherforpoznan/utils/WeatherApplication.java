package com.piotr.weatherforpoznan.utils;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.app.Application;
import com.activeandroid.query.Select;
import com.crashlytics.android.Crashlytics;
import com.piotr.weatherforpoznan.model.City;
import com.piotr.weatherforpoznan.model.ForecastItem;

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

    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        //Notice this initialization code here
        ActiveAndroid.initialize(this);
    }
}
