package com.piotr.localweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author piotr on 15-10-01.
 */

public class Main {
    @Expose
    @SerializedName("temp")
    private
    Double temp;

    @Expose
    @SerializedName("temp_min")
    private
    Double temp_min;

    @Expose
    @SerializedName("temp_max")
    private
    Double temp_max;

    @Expose
    @SerializedName("pressure")
    private
    Double pressure;

    @Expose
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