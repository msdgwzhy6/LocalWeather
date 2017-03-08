package com.piotr.localweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.piotr.localweather.R;
import com.piotr.localweather.model.ForecastItem;
import com.piotr.localweather.utils.ImageUtils;
import com.piotr.localweather.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author piotr on 02.10.15.
 */

public class ForecastAdapter extends ArrayAdapter<ForecastItem> {

    List<ForecastItem> mForecastItemList;
    Context mContext;

    public ForecastAdapter(Context context, List<ForecastItem> objects) {
        super(context, R.layout.list_item_forecast, objects);
        this.mForecastItemList = objects;
        this.mContext = context;
    }

    private static String getFormattedDate(Date date) {
        String formattedDay = new SimpleDateFormat("E d MMMM y HH:mm").format(date);
        return formattedDay;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_forecast, null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        initConvertView(mForecastItemList.get(position), vHolder);

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
        vHolder.forecastItemDate.setText(getFormattedDate(date));
    }

    private void initTemperatureMaxValue(ForecastItem item, ViewHolder vHolder) {
        vHolder.forecastItemTemperatureMax.setText(Math.round(item.getMain().getTempMax()) + " °C");
    }

    private void initTemperatureMinValue(ForecastItem item, ViewHolder vHolder) {
        vHolder.forecastItemTemperatureMin.setText(Math.round(item.getMain().getTempMin()) + " °C");
    }

    private void initIconDrawable(ForecastItem item, ViewHolder vHolder) {
        int iconName = item.getWeather().get(0).getWeatherId();
        int icon = ImageUtils.getIconResourceForWeatherCondition(iconName);
        Picasso.with(getContext()).load(icon).into(vHolder.forecastItemIcon);
    }

    private void initWeatherValue(ForecastItem item, ViewHolder vHolder) {
        String description = item.getWeather().get(0).getDescription();
        vHolder.forecastItemDescription.setText(StringUtils.capitalizeString(description));
    }

    static class ViewHolder {
        final ImageView forecastItemIcon;
        final TextView forecastItemDate;
        final TextView forecastItemDescription;
        final TextView forecastItemTemperatureMin;
        final TextView forecastItemTemperatureMax;

        public ViewHolder(View view) {
            this.forecastItemIcon = (ImageView) view.findViewById(R.id.forecastItemIcon);
            this.forecastItemDate = (TextView) view.findViewById(R.id.forecastItemDate);
            this.forecastItemTemperatureMin = (TextView) view.findViewById(R.id.forecastItemTemperatureMin);
            this.forecastItemTemperatureMax = (TextView) view.findViewById(R.id.forecastItemTemperatureMax);
            this.forecastItemDescription = (TextView) view.findViewById(R.id.forecastItemDescription);
        }
    }
}