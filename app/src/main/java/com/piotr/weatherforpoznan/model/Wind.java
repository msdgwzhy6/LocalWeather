package com.piotr.weatherforpoznan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piotrek on 15-10-01.
 */
public class Wind {
    @SerializedName("speed")
    Double speed;

    @SerializedName("deg")
    Double deg;

    public Double getSpeed() {
        return speed;
    }

    public Double getDeg() {
        return deg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
