package com.piotr.weatherforpoznan.service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.piotr.weatherforpoznan.R;
import com.piotr.weatherforpoznan.model.BitmapFactory;
import com.piotr.weatherforpoznan.model.ForecastItem;
import com.piotr.weatherforpoznan.receiver.NotificationEventReceiver;
import com.piotr.weatherforpoznan.repositories.WeatherRepository;
import com.piotr.weatherforpoznan.utils.DateUtils;
import com.piotr.weatherforpoznan.utils.ImageUtils;
import com.piotr.weatherforpoznan.view.DetailsActivity_;

import java.util.Date;

import static com.piotr.weatherforpoznan.utils.ImageUtils.getArtResourceForWeatherCondition;
import static com.piotr.weatherforpoznan.utils.ImageUtils.getIconResourceForWeatherCondition;

/**
 * @author piotr 15.05.16.
 */
public class NotificationIntentService extends IntentService {

    private static final String TAG = "NotificationIntentService";

    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_START = "ACTION_START";
    private static final String ACTION_DELETE = "ACTION_DELETE";

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }

    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                processStartNotification(WeatherRepository.getNextWeatherForecast());
            }
            if (ACTION_DELETE.equals(action)) {
                processDeleteNotification(intent);
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    @SuppressLint("LongLogTag")
    private void processDeleteNotification(Intent intent) {
        Log.d(TAG, "processDeleteNotification() called with: " + "intent = [" + intent + "]");
    }

    private void processStartNotification(ForecastItem item) {
        Date date = item.getDt_txt();
        String description = item.getWeatherData().getDescription();
        double tempMax = Math.round(item.getMain().getTempMax());

        int iconId = item.getWeatherData().getWeatherId();
        int image = setImageResourceForWeatherNotification(iconId);

        android.support.v7.app.NotificationCompat.Builder builder =
                new android.support.v7.app.NotificationCompat.Builder(this);
        builder.setAutoCancel(true)
                .setSmallIcon(ImageUtils.getIconResourceForWeatherCondition((iconId)))
                .setLargeIcon(BitmapFactory.convert(ContextCompat
                        .getDrawable(getBaseContext(), image)))
                .setContentTitle(getBaseContext().getString(R.string.app_name))
                .setContentText(DateUtils.getFormattedDate(date))
                .setSubText(description + "\t" + tempMax + "°C");

        //FIXME: Change putExtra to get correct notification forecastItem Id
        Intent intent = new Intent(getBaseContext(), DetailsActivity_.class);
        intent.putExtra("id", 0);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));

        Notification notification = builder.build();
        NotificationManager mNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    private int setImageResourceForWeatherNotification(int iconId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getArtResourceForWeatherCondition(iconId);
        }
        return getIconResourceForWeatherCondition(iconId);
    }
}