package com.piotr.weatherforpoznan.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author piotr on 13.10.2015.
 */
@Table(name = "Coord")
public class Coord extends Model implements Serializable {
    @Column
    @SerializedName("lon")
    private Double lon;

    @Column
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