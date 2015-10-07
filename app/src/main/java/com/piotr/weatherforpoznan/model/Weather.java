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
    @SerializedName("id")
    Integer id;
    @Column
    @SerializedName("description")
    String description;

    public Weather() {
        super();
    }

    public Weather(Integer id, String description, String icon) {
        super();
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
