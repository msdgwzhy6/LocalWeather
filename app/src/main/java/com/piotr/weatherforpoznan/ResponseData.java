package com.piotr.weatherforpoznan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piotr on 17.09.15.
 */

public class ResponseData {
    @SerializedName("sys")
    System sys;
    // @SerializedName(“weather”)
//Weather weather;
    @SerializedName("main")
    Main main;
    @SerializedName("base")
    String base;
    @SerializedName("dt")
    String dt;
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String cityName;
    @SerializedName("cod")
    String cod;
    @SerializedName("wind")
    Wind wind;

    @SerializedName("weather")
    Weather[] weathers;
    @SerializedName("clouds")
    Clouds clouds;

    public Weather[] getWeathers() {
        return weathers;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }


    public System getSys() {
        return sys;
    }

    public Main getMain() {
        return main;
    }

    public String getBase() {
        return base;
    }

    public String getDt() {
        return dt;
    }

    public String getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCod() {
        return cod;
    }
}
