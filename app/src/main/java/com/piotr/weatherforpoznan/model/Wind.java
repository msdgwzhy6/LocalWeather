package com.piotr.weatherforpoznan.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by piotrek on 15-10-01.
 */

@Table(name = "Wind")
public class Wind extends Model implements Serializable {
    @Column
    @SerializedName("speed")
    Double speed;

    public Wind() {
        super();
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
