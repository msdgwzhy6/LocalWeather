package com.piotr.weatherforpoznan.model.settings;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by piotr on 29.10.15.
 */
@Table(name = "Settings")
public class SettingsValues extends Model implements Serializable {
    @Column(name = "City", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("city")
    String city;

    @Column(name = "TimeFormat12h", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("timeFormat12h")
    Boolean timeFormat12h;

    @Column(name = "TimeFormat24h", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("timeFormat24h")
    Boolean timeFormat24h;

    @Column(name = "TemperatureMetric", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("temperatureMetric")
    Boolean temperatureMetric;

    @Column(name = "TemperatureImperial", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("temperatureImperial")
    Boolean temperatureImperial;

    public SettingsValues() {
        super();
    }

    public SettingsValues(String city) {
        super();
        this.city = city;
        this.timeFormat12h = timeFormat12h;
        this.timeFormat24h = timeFormat24h;
        this.temperatureMetric = temperatureMetric;
        this.temperatureImperial = temperatureImperial;
    }

    public Boolean getTemperatureImperial() {
        return temperatureImperial;
    }

    public void setTemperatureImperial(Boolean temperatureImperial) {
        this.temperatureImperial = temperatureImperial;
    }

    public Boolean getTemperatureMetric() {
        return temperatureMetric;
    }

    public void setTemperatureMetric(Boolean temperatureMetric) {
        this.temperatureMetric = temperatureMetric;
    }

    @Override
    public String toString() {
        return "SettingsValues{" +
                "city='" + city + '\'' +
                ", timeFormat12h=" + timeFormat12h +
                ", timeFormat24h=" + timeFormat24h +
                ", temperatureMetric=" + temperatureMetric +
                ", temperatureImperial=" + temperatureImperial +
                '}';
    }

    public Boolean getTimeFormat12h() {
        return timeFormat12h;
    }

    public void setTimeFormat12h(Boolean timeFormat) {
        this.timeFormat12h = timeFormat;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getTimeFormat24h() {
        return timeFormat24h;
    }

    public void setTimeFormat24h(Boolean timeFormat24h) {
        this.timeFormat24h = timeFormat24h;
    }
}