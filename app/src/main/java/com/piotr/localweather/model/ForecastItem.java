package com.piotr.localweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * @author piotr on 15-10-01.
 */

public class ForecastItem {
    @Expose
    @SerializedName("dt_txt")
    private
    Date dt_txt;

    @Expose
    @SerializedName("main")
    private
    Main main;

    @Expose
    @SerializedName("weather")
    private
    List<Weather> weather;

    @Expose
    private
    Weather weatherData;


    @Expose
    @SerializedName("wind")
    private
    Wind wind;

    public ForecastItem() {
        super();
    }

    public ForecastItem(Date dt_txt, Main main, List<Weather> weather, Wind wind) {
        super();
        this.dt_txt = dt_txt;
        this.main = main;
        this.wind = wind;
        this.weather = weather;
    }

    public Weather getWeatherData() {
        return weatherData;
    }

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
                "dt_txt=" + dt_txt +
                ", main=" + main +
                ", weather=" + weather +
                ", weatherData=" + weatherData +
                ", wind=" + wind +
                '}';
    }
}