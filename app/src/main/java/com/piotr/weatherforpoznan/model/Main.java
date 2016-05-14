package com.piotr.weatherforpoznan.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author piotr on 15-10-01.
 */

@Table(name = "Main")
public class Main extends Model implements Serializable {
    @Column
    @SerializedName("temp")
    private
    Double temp;

    @Column
    @SerializedName("temp_min")
    private
    Double temp_min;

    @Column
    @SerializedName("temp_max")
    private
    Double temp_max;

    @Column
    @SerializedName("pressure")
    private
    Double pressure;

    @Column
    @SerializedName("humidity")
    private
    Double humidity;

    public Main() {

    }

    public Main(Double temp, Double pressure, Double humidity) {
        super();
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public Double getTempMax() {
        return temp_max;
    }

    public Double getTempMin() {
        return temp_min;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", temp_max=" + temp_max +
                ", temp_min=" + temp_min +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}