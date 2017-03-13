package com.piotr.localweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author piotr on 15-10-01.
 */

public class Wind {
    @Expose
    @SerializedName("speed")
    private
    Double speed;

    @Expose
    @SerializedName("deg")
    private
    float deg;

    public Wind() {
        super();
    }

    public Wind(Double speed, float deg) {
        super();
        this.speed = speed;
        this.deg = deg;

    }

    public Double getSpeed() {
        return speed;
    }

    public float getDeg() {
        return deg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                "deg" + deg +
                '}';
    }
}
