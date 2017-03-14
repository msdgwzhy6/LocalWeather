package com.piotr.localweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.piotr.localweather.R;
import com.piotr.localweather.api.model.List;
import com.piotr.localweather.api.model.WeatherData;
import com.piotr.localweather.utils.ImageUtils;
import com.piotr.localweather.utils.StringUtils;
import com.squareup.picasso.Picasso;

/**
 * @author piotr on 13.03.17.
 */
public class WeatherDataAdapter extends RecyclerView.Adapter<WeatherDataAdapter.ViewHolder> {
    private WeatherData mWeatherList;
    private Context mContext;

    public WeatherDataAdapter(WeatherData androidArrayList, Context context) {
        this.mWeatherList = androidArrayList;
        this.mContext = context;
    }

    @Override
    public WeatherDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, null);
        view.setLayoutParams(
                new RecyclerView.LayoutParams(
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherDataAdapter.ViewHolder holder, int position) {
        initConvertView(mWeatherList.getList().get(position), holder);
    }

    private void initConvertView(List item, ViewHolder vHolder) {
        initDateValue(item, vHolder);
        initTemperatureMaxValue(item, vHolder);
        initTemperatureMinValue(item, vHolder);
        initWeatherValue(item, vHolder);
        initIconDrawable(item, vHolder);
    }

    private void initDateValue(List item, ViewHolder vHolder) {
        vHolder.forecastItemDate.setText(item.getDtTxt());
    }

    private void initTemperatureMaxValue(List item, ViewHolder vHolder) {
        vHolder.forecastItemTemperatureMax.setText(mContext.getString(
                R.string.temp_metrics, Math.round(item.getMain().getTempMax())));
    }

    private void initTemperatureMinValue(List item, ViewHolder vHolder) {
        vHolder.forecastItemTemperatureMin.setText(mContext.getString(
                R.string.temp_metrics, Math.round(item.getMain().getTempMin())));
    }

    private void initIconDrawable(List item, ViewHolder vHolder) {
        int iconName = item.getWeather().get(0).getId();
        int icon = ImageUtils.getIconResourceForWeatherCondition(iconName);
        Picasso.with(mContext).load(icon).into(vHolder.forecastItemIcon);
    }

    private void initWeatherValue(List item, ViewHolder vHolder) {
        String description = item.getWeather().get(0).getDescription();
        vHolder.forecastItemDescription.setText(StringUtils.capitalizeString(description));
    }

    @Override
    public int getItemCount() {
        return mWeatherList.getList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView forecastItemIcon;
        TextView forecastItemDate;
        TextView forecastItemDescription;
        TextView forecastItemTemperatureMin;
        TextView forecastItemTemperatureMax;

        ViewHolder(View itemView) {
            super(itemView);
            forecastItemIcon = (ImageView) itemView.findViewById(R.id.forecastItemIcon);
            forecastItemDate = (TextView) itemView.findViewById(R.id.forecastItemDate);
            forecastItemDescription = (TextView) itemView.findViewById(R.id.forecastItemDescription);
            forecastItemTemperatureMin = (TextView) itemView.findViewById(R.id.forecastItemTemperatureMin);
            forecastItemTemperatureMax = (TextView) itemView.findViewById(R.id.forecastItemTemperatureMax);
        }

        @Override
        public void onClick(View v) {
            // TODO: 14.03.17 Implement new DetailActivity
        }
    }
}