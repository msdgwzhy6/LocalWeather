package com.piotr.weatherforpoznan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piotrek on 15-10-01.
 */
public class City {
    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("coord")
    Coord coord;

    @SerializedName("country")
    String country;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                ", country='" + country + '\'' +
                '}';
    }
}
