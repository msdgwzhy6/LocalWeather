package com.piotr.weatherforpoznan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by piotrek on 15-10-01.
 */

public class Forecast {
    @SerializedName("list")
    java.util.List<ForecastItem> forecastList;

    @SerializedName("city")
    City city;

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
