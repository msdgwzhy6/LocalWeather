package com.piotr.localweather.repositories;

import com.activeandroid.query.Select;
import com.piotr.localweather.WeatherApplication;
import com.piotr.localweather.model.ForecastItem;

public class WeatherRepository {
    public static ForecastItem getActualWeatherForecast() {
        if (WeatherApplication.getObjectsList().size() > 0)
            return new Select().from(ForecastItem.class)
                    .where("id = ?", WeatherApplication.getObjectsList().get(0).
                            getId()).executeSingle();

        return null;
    }
}