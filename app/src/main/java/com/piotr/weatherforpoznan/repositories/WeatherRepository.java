package com.piotr.weatherforpoznan.repositories;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.WeatherApplication;
import com.piotr.weatherforpoznan.model.ForecastItem;

public class WeatherRepository {
    @SuppressWarnings("deprecation")
    public static ForecastItem getActualWeatherForecast() {
        return new Select().from(ForecastItem.class)
                .where("id = ?", WeatherApplication.getObjectsList().get(0).
                        getId()).executeSingle();
    }
}