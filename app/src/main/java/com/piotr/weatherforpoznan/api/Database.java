package com.piotr.weatherforpoznan.api;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.model.ForecastItem;

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

        //Notice this initialization code here
        ActiveAndroid.initialize(this);
    }
}
