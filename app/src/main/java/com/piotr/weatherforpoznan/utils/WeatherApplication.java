package com.piotr.weatherforpoznan.utils;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.app.Application;
import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.model.ForecastItem;

import java.util.List;

/**
 * Created by Piotr on 06.10.2015.
 */
public class WeatherApplication extends Application {
    public static ForecastItem getRandom(ForecastItem forecastItem) {
        return new Select()
                .from(ForecastItem.class)
                .where("dt_txt = ?", forecastItem.getId())
                .orderBy("RANDOM()")
                .executeSingle();
    }

    public static List<Model> getObjectsList() {
        return new Select().from(ForecastItem.class).execute();
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        //Fabric.with(this, new Crashlytics());

        //Notice this initialization code here
        ActiveAndroid.initialize(this);
    }
}
