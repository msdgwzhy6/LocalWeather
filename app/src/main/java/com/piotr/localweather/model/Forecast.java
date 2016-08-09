package com.piotr.localweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author piotr on 15-10-01.
 */

public class Forecast {
    @SerializedName("list")
    private java.util.List<ForecastItem> forecastList;

    @SerializedName("city")
    private City city;

    @Override
    public String toString() {
        return "Forecast{" +
                "forecastList=" + forecastList +
                ", city=" + city +
                '}';
    }

    public City getCity() {
        return city;
    }

    public List<ForecastItem> getForecastList() {
        return forecastList;
    }
}
