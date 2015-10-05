package com.piotr.weatherforpoznan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piotrek on 15-10-01.
 */
public class Wind {
    @SerializedName("speed")
    Double speed;

    public Double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                '}';
    }
}
