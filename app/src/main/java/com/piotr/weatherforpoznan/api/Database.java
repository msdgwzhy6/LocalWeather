package com.piotr.weatherforpoznan.api;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.activeandroid.query.Select;
import com.crashlytics.android.Crashlytics;
import com.piotr.weatherforpoznan.model.ForecastItem;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Piotr on 06.10.2015.
 */
public class Database extends Application {
    public static ForecastItem getRandom(ForecastItem forecastItem) {
        return new Select()
                .from(ForecastItem.class)
                .where("dt_txt = ?", forecastItem.getId())
                .orderBy("RANDOM()")
                .executeSingle();
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        //Notice this initialization code here
        ActiveAndroid.initialize(this);
    }
}
