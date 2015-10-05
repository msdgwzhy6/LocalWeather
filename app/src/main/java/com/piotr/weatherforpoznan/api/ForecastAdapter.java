package com.piotr.weatherforpoznan.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.model.ForecastItem;

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

        vHolder.forecastIcon = (ImageView) convertView.findViewById(R.id.itemWeatherIcon);
        vHolder.itemTemperature = (TextView) convertView.findViewById(R.id.itemTemperature);
        vHolder.itemPressure = (TextView) convertView.findViewById(R.id.itemPressure);
        vHolder.itemHumidity = (TextView) convertView.findViewById(R.id.itemHumidity);
        vHolder.itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_forecast, null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        vHolder.itemDate.setText(item.getDt_txt().toString());
        vHolder.itemTemperature.setText(item.getMain().getTemp().toString());
        vHolder.itemPressure.setText(item.getMain().getPressure().toString());
        vHolder.itemWind.setText(item.getWind().getSpeed().toString());
        vHolder.itemHumidity.setText(item.getMain().getHumidity().toString());

        if (item.getWeather().size() > 0) {
            vHolder.itemDescription.setText(item.getWeather().get(0).getDescription().toString());
        } else {
            vHolder.itemDescription.setText("Opis");
        }
        ;

        return convertView;
    }

    static class ViewHolder {
        ImageView forecastIcon;

        TextView itemDate;
        TextView itemDescription;
        TextView itemTemperature;
        TextView itemPressure;
        TextView itemWind;
        TextView itemHumidity;

        public ViewHolder(View convertView) {
        }
    }

}
