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
import com.piotr.weatherforpoznan.utils.Utility;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.piotr.weatherforpoznan.utils.Utility.capitalizeString;

/**
 * Created by piotr on 02.10.15.
 */

public class ForecastAdapter extends ArrayAdapter<ForecastItem> {

    public ForecastAdapter(Context context, int resource, List<ForecastItem> objects) {
        super(context, resource, objects);
    }

    private static String getFormattedDate(Date date) {
        String formattedDay = new SimpleDateFormat("E d MMMM y HH:mm").format(date);
        return formattedDay;
        //FIXME: Settings -> Give user: 24h or AM/PM format
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ForecastItem item = getItem(position);
        ViewHolder vHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_forecast, null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        initConvertView(item, vHolder);

        return convertView;
    }

    private void initConvertView(ForecastItem item, ViewHolder vHolder) {
        initDateValue(item, vHolder);
        initTemperatureMaxValue(item, vHolder);
        initTemperatureMinValue(item, vHolder);
        initWeatherValue(item, vHolder);
        initIconDrawable(item, vHolder);
    }

    private void initDateValue(ForecastItem item, ViewHolder vHolder) {
        Date date = item.getDt_txt();
        vHolder.itemDate.setText(getFormattedDate(date));
    }

    private void initTemperatureMaxValue(ForecastItem item, ViewHolder vHolder) {
        vHolder.itemTemperatureMax.setText(Math.round(item.getMain().getTempMax()) + " °C");
    }

    private void initTemperatureMinValue(ForecastItem item, ViewHolder vHolder) {
        vHolder.itemTemperatureMin.setText(Math.round(item.getMain().getTempMin()) + " °C");
    }

    private void initIconDrawable(ForecastItem item, ViewHolder vHolder) {
        int iconName = item.getWeather().get(0).getWeatherId();
        int icon = Utility.getIconResourceForWeatherCondition(iconName);
        Picasso.with(getContext()).load(icon).into(vHolder.forecastIcon);
    }

    private void initWeatherValue(ForecastItem item, ViewHolder vHolder) {
        if (item.getWeather().size() > 0) {
            String description = item.getWeather().get(0).getDescription().toString();
            vHolder.itemDescription.setText(capitalizeString(description));
        } else {
            vHolder.itemDescription.setText(R.string.description_not_available);
        }
    }

    static class ViewHolder {
        ImageView forecastIcon;
        TextView itemDate;
        TextView itemDescription;
        TextView itemTemperatureMin;
        TextView itemTemperatureMax;

        public ViewHolder(View view) {
            this.forecastIcon = (ImageView) view.findViewById(R.id.forecastItemIcon);
            this.itemDate = (TextView) view.findViewById(R.id.forecastItemDate);
            this.itemTemperatureMin = (TextView) view.findViewById(R.id.forecastItemTemperatureMin);
            this.itemTemperatureMax = (TextView) view.findViewById(R.id.forecastItemTemperatureMax);
            this.itemDescription = (TextView) view.findViewById(R.id.forecastItemDescription);
        }
    }
}