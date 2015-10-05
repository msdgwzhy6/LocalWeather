package com.piotr.weatherforpoznan.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by piotr on 02.10.15.
 */
public class ForecastAdapter extends ArrayAdapter<ForecastItem> {

    public ForecastAdapter(Context context, int resource, List<ForecastItem> objects) {
        super(context, resource, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ForecastItem item = getItem(position);
        ViewHolder vHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_forecast, null);
            vHolder = new ViewHolder();
            vHolder.forecastIcon = (ImageView) convertView.findViewById(R.id.itemWeatherIcon);
            vHolder.itemDate = (TextView) convertView.findViewById(R.id.itemForecastDate);
            vHolder.itemTemperature = (TextView) convertView.findViewById(R.id.itemTemperature);
            vHolder.itemPressure = (TextView) convertView.findViewById(R.id.itemPressure);
            vHolder.itemHumidity = (TextView) convertView.findViewById(R.id.itemHumidity);
            vHolder.itemWind = (TextView) convertView.findViewById(R.id.itemWind);
            vHolder.itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        Date date = item.getDt_txt();
        String stringDate = DateFormat.getDateTimeInstance().format(date);

        vHolder.itemDate.setText(stringDate);
        vHolder.itemTemperature.setText(item.getMain().getTemp().toString() + " °C");
        vHolder.itemPressure.setText(item.getMain().getPressure().toString() + " hPa");
        vHolder.itemHumidity.setText(item.getMain().getHumidity().toString() + " %");
        vHolder.itemWind.setText(item.getWind().getSpeed().toString() + " m/s");

        if (item.getWeather().size() > 0) {
            String description = item.getWeather().get(0).getDescription().toString();
            vHolder.itemDescription.setText(capitalizeString(description));
        } else {
            vHolder.itemDescription.setText("Description is unavailable");
        }
        ;
        String iconName = item.getWeather().get(0).getIcon().toString();
        Picasso.with(getContext()).load("http://openweathermap.org/img/w/" + iconName + ".png").into(vHolder.forecastIcon);

        return convertView;
    }

    @NonNull
    private String capitalizeString(String description) {
        StringBuilder descriptionSb = new StringBuilder(description);
        descriptionSb.setCharAt(0, Character.toUpperCase(descriptionSb.charAt(0)));
        description = descriptionSb.toString();
        return description;
    }

    static class ViewHolder {
        ImageView forecastIcon;

        TextView itemDate;
        TextView itemDescription;
        TextView itemTemperature;
        TextView itemPressure;
        TextView itemWind;
        TextView itemHumidity;

        public ViewHolder() {
        }
    }

}
