package com.piotr.weatherforpoznan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piotrek on 15-10-01.
 */
public class Main {
    @SerializedName("temp")
    Double temp;

    @SerializedName("temp_min")
    Double tempMin;

    @SerializedName("temp_max")
    Double tempMax;

    @SerializedName("pressure")
    Double pressure;

    @SerializedName("sea_level")
    Double seaLevel;

    @SerializedName("grnd_level")
    Double grndLevel;

    @SerializedName("humidity")
    Double humidity;

    @SerializedName("temp_kf")
    Double tempKf;

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
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", pressure=" + pressure +
                ", seaLevel=" + seaLevel +
                ", grndLevel=" + grndLevel +
                ", humidity=" + humidity +
                ", tempKf=" + tempKf +
                '}';
    }
}