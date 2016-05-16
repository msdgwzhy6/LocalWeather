package com.piotr.weatherforpoznan.utils;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.WeatherApplication;
import com.piotr.weatherforpoznan.model.Forecast;
import com.piotr.weatherforpoznan.model.ForecastItem;

public class DatabaseUtils {

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

    public static ForecastItem getNextWeatherForecast() {
        //FIXME: Add changing time condition
        return new Select().from(ForecastItem.class)
                .where("id = ?", WeatherApplication.getObjectsList().get(0).
                        getId()).executeSingle();
    }
}