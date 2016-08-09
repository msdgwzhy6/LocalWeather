package com.piotr.localweather.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author piotr on 15-10-01.
 */

@Table(name = "Wind")
public class Wind extends Model implements Serializable {
    @Column
    @SerializedName("speed")
    private
    Double speed;

    @Column
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
