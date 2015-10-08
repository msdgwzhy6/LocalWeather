package com.piotr.weatherforpoznan.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by piotrek on 15-10-01.
 */

@Table(name = "Weather")
public class Weather extends Model implements Serializable {

    @Column
    @SerializedName("id")
    Integer weatherId;
    @Column
    @SerializedName("description")
    String description;

    public Weather() {
        super();
    }

    public Weather(Integer id, String description, String icon) {
        super();
        this.weatherId = id;
        this.description = description;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + weatherId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
