package com.piotr.weatherforpoznan.utils;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.piotr.weatherforpoznan.WeatherApplication;
import com.piotr.weatherforpoznan.model.Forecast;

public class DatabaseUtils {
    public DatabaseUtils() {
    }

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
            forecast.getCity().getCoord().save();
            forecast.getCity().save();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        Log.d("DATABASE", "WeatherApplication: " + WeatherApplication.getCityList());
    }
}