package com.piotr.localweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author piotr on 15-10-01.
 */

public class Weather {
    @Expose
    @SerializedName("id")
    private
    Integer weatherId;
    @Expose
    @SerializedName("description")
    private
    String description;

    public Weather() {
        super();
    }

    public Weather(Integer id, String description, String icon) {
        super();
        this.weatherId = id;
        this.description = description;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + weatherId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
