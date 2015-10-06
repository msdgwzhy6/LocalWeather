package com.piotr.weatherforpoznan.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

/**
 * Created by piotrek on 15-10-01.
 */

@Table(name = "Wind")
public class Wind {
    @Column
    @SerializedName("speed")
    Double speed;

    public Wind() {

    }

    public Wind(Double speed) {
        super();
        this.speed = speed;

    }

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
