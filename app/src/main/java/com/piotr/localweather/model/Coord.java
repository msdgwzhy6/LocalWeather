package com.piotr.localweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author piotr on 13.10.2015.
 */
public class Coord {
    @Expose
    @SerializedName("lon")
    private Double lon;

    @Expose
    @SerializedName("lat")
    private Double lat;

    public Coord() {
        super();
    }

    public Coord(Double lon, Double deg) {
        super();
        this.lon = lon;
        this.lat = deg;

    }

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
                "deg" + lat +
                '}';
    }
}