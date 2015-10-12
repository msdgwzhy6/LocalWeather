package com.piotr.weatherforpoznan.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Column.ForeignKeyAction;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by piotrek on 15-10-01.
 */

@Table(name = "ForecastItem")
public class ForecastItem extends Model {
    @Column(name = "dt_txt", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("dt_txt")
    Date dt_txt;

    @Column(name = "main", onUpdate = ForeignKeyAction.CASCADE, onDelete = ForeignKeyAction.CASCADE)
    @SerializedName("main")
    Main main;

    @Column(name = "weather", onUpdate = ForeignKeyAction.CASCADE, onDelete = ForeignKeyAction.CASCADE)
    @SerializedName("weather")
    List<Weather> weather;

    @Column(name = "weatherData", onUpdate = ForeignKeyAction.CASCADE, onDelete = ForeignKeyAction.CASCADE)
    Weather weatherData;



    @Column(name = "wind", onUpdate = ForeignKeyAction.CASCADE, onDelete = ForeignKeyAction.CASCADE)
    @SerializedName("wind")
    Wind wind;

    public ForecastItem() {
        super();
    }

    public ForecastItem(Date dt_txt, Main main, List<Weather> weather, Wind wind) {
        super();
        this.dt_txt = dt_txt;
        this.main = main;
        this.wind = wind;
    }


    public Long saveItemToDatabase() {
        getMain().save();
        for (int k = 0; k < getWeather().size(); k++) {
            getWeather().get(k).save();
        }

        if (getWeather() != null && getWeather().size() >= 1) {
            weatherData = getWeather().get(0);
            weatherData.save();
        }

        getWind().save();
        return save();
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