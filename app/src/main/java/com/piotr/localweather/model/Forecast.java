package com.piotr.localweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author piotr on 15-10-01.
 */

public class Forecast {
    @Expose
    @SerializedName("list")
    private java.util.List<ForecastItem> forecastList;

    @Expose
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
