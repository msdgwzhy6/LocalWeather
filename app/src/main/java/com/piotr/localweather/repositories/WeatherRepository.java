package com.piotr.localweather.repositories;

import com.activeandroid.query.Select;
import com.piotr.localweather.WeatherApplication;
import com.piotr.localweather.model.ForecastItem;

public class WeatherRepository {
    @SuppressWarnings("deprecation")
    public static ForecastItem getActualWeatherForecast() {
        return new Select().from(ForecastItem.class)
                .where("id = ?", WeatherApplication.getObjectsList().get(0).
                        getId()).executeSingle();
    }
}