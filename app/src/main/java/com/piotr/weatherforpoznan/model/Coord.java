package com.piotr.weatherforpoznan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piotrek on 15-10-01.
 */
public class Coord {
    @SerializedName("lon")
    Double lon;
    @SerializedName("lat")
    Double lat;

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
