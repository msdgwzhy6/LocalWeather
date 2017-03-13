package com.piotr.localweather.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("sea_level")
    @Expose
    private String seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private String grndLevel;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("temp_kf")
    @Expose
    private String tempKf;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(String seaLevel) {
        this.seaLevel = seaLevel;
    }

    public String getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(String grndLevel) {
        this.grndLevel = grndLevel;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getTempKf() {
        return tempKf;
    }

    public void setTempKf(String tempKf) {
        this.tempKf = tempKf;
    }

}
