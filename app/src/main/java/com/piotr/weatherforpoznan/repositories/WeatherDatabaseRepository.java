package com.piotr.weatherforpoznan.repositories;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.piotr.weatherforpoznan.WeatherApplication;
import com.piotr.weatherforpoznan.model.City;
import com.piotr.weatherforpoznan.model.Forecast;

/**
 * @author piotr on 16.05.16.
 */
public class WeatherDatabaseRepository {

    public void saveForecastItemToDatabase(Forecast forecast) {
        int i = 0;
        ActiveAndroid.beginTransaction();
        try {
            while (i < forecast.getForecastList().size()) {
                forecast.getForecastList().get(i).saveItemToDatabase();
                i++;
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public void saveCityDataToDatabase(Forecast forecast) {
        ActiveAndroid.beginTransaction();
        try {
            if (WeatherApplication.getCityList().size() > 0) {
                new Delete().from(City.class).execute();
            }
            forecast.getCity().getCoord().save();
            forecast.getCity().save();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        Log.d("DATABASE", "WeatherApplication: " + WeatherApplication.getCityList());
    }
}
