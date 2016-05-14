package com.piotr.weatherforpoznan.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.piotr.weatherforpoznan.utils.ImageUtils;
import com.piotr.weatherforpoznan.utils.StringUtils;
import com.piotr.weatherforpoznan.view.DetailsActivity;
import com.piotr.weatherforpoznan.view.MainActivity;

import java.util.Date;

import static com.piotr.weatherforpoznan.utils.ImageUtils.getArtResourceForWeatherCondition;
import static com.piotr.weatherforpoznan.utils.ImageUtils.getIconResourceForWeatherCondition;

public class WeatherNotification {
    private final MainActivity mMainActivity;

    public WeatherNotification(MainActivity mMainActivity) {
        this.mMainActivity = mMainActivity;
    }

    public void createWeatherNotification(ForecastItem item) {
        Date date = item.getDt_txt();
        String description = item.getWeatherData().getDescription();
        double tempMax = Math.round(item.getMain().getTempMax());
        int iconId = item.getWeatherData().getWeatherId();

        Intent intent = new Intent(mMainActivity, DetailsActivity.class);
        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(mMainActivity, requestID, intent, flags);

        int image = setImageResourceForWeatherNotification(iconId);

        Notification notification =
                new NotificationCompat.Builder(mMainActivity)
                        .setSmallIcon(ImageUtils.getIconResourceForWeatherCondition((iconId)))
                        .setLargeIcon(ImageUtils.drawableToBitmap(ContextCompat
                                .getDrawable(mMainActivity, image)))
                        .setContentTitle(mMainActivity.getString(R.string.app_name))
                        .setContentText(StringUtils.getFormattedDate(date))
                        .setSubText(description + "\t" + tempMax + "°C")
                        .setContentIntent(pIntent).build();

        NotificationManager mNotificationManager =
                (NotificationManager) mMainActivity.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notification);

    }

    private int setImageResourceForWeatherNotification(int iconId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getArtResourceForWeatherCondition(iconId);
        }
        return getIconResourceForWeatherCondition(iconId);
    }
}