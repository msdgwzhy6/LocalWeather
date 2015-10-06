package com.piotr.weatherforpoznan.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by piotrek on 15-10-01.
 */

@Table(name = "ForecastItem")
public class ForecastItem {
    @Column
    @SerializedName("dt_txt")
    Date dt_txt;

    @Column
    @SerializedName("main")
    Main main;

    @Column
    @SerializedName("weather")
    List<Weather> weather;

    @Column
    @SerializedName("wind")
    Wind wind;

    public Date getDt_txt() {
        return dt_txt;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
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
                ", wind=" + wind +
                '}';
    }
}