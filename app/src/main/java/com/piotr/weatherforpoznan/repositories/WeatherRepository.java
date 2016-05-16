package com.piotr.weatherforpoznan.repositories;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.WeatherApplication;
import com.piotr.weatherforpoznan.model.ForecastItem;

public class WeatherRepository {

    public static ForecastItem getNextWeatherForecast() {
        //FIXME: Add changing time condition
        return new Select().from(ForecastItem.class)
                .where("id = ?", WeatherApplication.getObjectsList().get(0).
                        getId()).executeSingle();
    }
}