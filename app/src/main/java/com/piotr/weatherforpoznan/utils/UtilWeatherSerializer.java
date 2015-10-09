package com.piotr.weatherforpoznan.utils;

/**
 * Created by Piotr on 09.10.2015.
 */

import com.activeandroid.serializer.TypeSerializer;
import com.google.gson.Gson;
import com.piotr.weatherforpoznan.model.Weather;

public class UtilWeatherSerializer extends TypeSerializer {
    private static final Gson gson = new Gson();

    @Override
    public Class<?> getDeserializedType() {
        return Weather.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public Object serialize(final Object o) {
        if (null == o) return null;
        final String json = gson.toJson(o);
        return json;
    }

    @Override
    public Object deserialize(final Object o) {
        if (null == o) return null;
        final Weather[] weatherItems = gson.fromJson(o.toString(), Weather[].class);
        return weatherItems;
    }
}