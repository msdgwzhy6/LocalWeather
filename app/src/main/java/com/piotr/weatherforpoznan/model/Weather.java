package com.piotr.weatherforpoznan.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

/**
 * Created by piotrek on 15-10-01.
 */

@Table(name = "Weather")
public class Weather {

    @Column
    @SerializedName("description")
    String description;

    @Column
    @SerializedName("icon")
    String icon;

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
