package com.piotr.localweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author piotr on 13.10.2015.
 */

public class City {
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("coord")
    private Coord coord;


    public City() {
        super();
    }

    public City(String name, Coord coord) {
        super();
        this.name = name;
        this.coord = coord;

    }

    public Coord getCoord() {
        return coord;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + name + '\'' +
                ", coord=" + coord +
                '}';
    }
}
