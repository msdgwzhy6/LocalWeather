package com.piotr.weatherforpoznan.repositories;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.WeatherApplication;
import com.piotr.weatherforpoznan.model.ForecastItem;

import java.util.Calendar;
import java.util.Date;

public class WeatherRepository {

    @SuppressWarnings("deprecation")
    public static ForecastItem getActualWeatherForecast() {
        //TODO: Add Service to refresh data
        Date actualItemTime = getFirstForecastItem().getDt_txt();
        int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minutes = Calendar.getInstance().get(Calendar.MINUTE);

        /*
        * NOTE: That's temporary solution until I won't make a refresh Forecast data Service
        * */
        int i = 0;
        while (i < WeatherApplication.getObjectsList().size()) {
            //noinspection deprecation
            if ((hours > actualItemTime.getHours()) ||
                    ((hours == actualItemTime.getHours()) && (minutes > 0))) {
                return new Select().from(ForecastItem.class)
                        .where("id = ?", WeatherApplication.getObjectsList().get(i + 1).
                                getId()).executeSingle();
            }
            if ((hours == actualItemTime.getHours()) && (minutes == 0)) {
                return new Select().from(ForecastItem.class)
                        .where("id = ?", WeatherApplication.getObjectsList().get(i).
                                getId()).executeSingle();
            }
            i++;
        }
        return null;
    }

    private static ForecastItem getFirstForecastItem() {
        return new Select().from(ForecastItem.class)
                .where("id = ?", WeatherApplication.getObjectsList().get(0).
                        getId()).executeSingle();
    }
}