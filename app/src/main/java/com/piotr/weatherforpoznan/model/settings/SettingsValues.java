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
    @Column
    @SerializedName("city")
    public
    String city;

    public SettingsValues() {
        super();
    }

    public SettingsValues(String city) {
        super();
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "cityName='" + city + '\'' +
                '}';
    }
}