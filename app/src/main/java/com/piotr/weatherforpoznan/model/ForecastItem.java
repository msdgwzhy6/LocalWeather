package com.piotr.weatherforpoznan.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by piotrek on 15-10-01.
 */
public class ForecastItem {
    @SerializedName("dt_txt")
    Date dt_txt;

    @SerializedName("main")
    Main main;

    @SerializedName("weather")
    Weather weather;

    @SerializedName("clouds")
    Clouds clouds;

    @SerializedName("wind")
    Wind wind;

    public Date getDt_txt() {
        return dt_txt;
    }

    public Main getMain() {
        return main;
    }

    public Weather getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }

    @Override
    public String toString() {
        return "ForecastItem{" +
                "dt_txt='" + dt_txt + '\'' +
                ", main=" + main +
                ", weather=" + weather +
                ", clouds=" + clouds +
                ", wind=" + wind +
                '}';
    }
}
