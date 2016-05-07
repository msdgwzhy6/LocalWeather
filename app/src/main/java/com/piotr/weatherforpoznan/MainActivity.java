package com.piotr.weatherforpoznan;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.model.ForecastItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.Date;
import java.util.List;

import static com.piotr.weatherforpoznan.utils.ImageUtils.drawableToBitmap;
import static com.piotr.weatherforpoznan.utils.ImageUtils.getArtResourceForWeatherCondition;
import static com.piotr.weatherforpoznan.utils.ImageUtils.getIconResourceForWeatherCondition;
import static com.piotr.weatherforpoznan.utils.StringUtils.getFormattedDate;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.action_bar_title)
    TextView action_bar_title;

    @StringRes
    String geo_coord;

    @StringRes
    String latitude;

    @StringRes
    String longitude;

    @StringRes
    String latitude_short;

    @StringRes
    String longitude_short;

    @UiThread
    protected void setWeatherFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mActivity, new MainActivityFragment_())
                    .commit();
        }
        List<ForecastItem> forecastItems = new Select().from(ForecastItem.class).execute();
        //TODO: Set ACTUAL notification data on every application run
        if (forecastItems.size() != 0) {
            ForecastItem item = new Select().from(ForecastItem.class).where("id = ?", forecastItems.get(1).getId())
                    .executeSingle();

            setWeatherNotification(item);
        }

    }


    protected void setWeatherNotification(ForecastItem item) {
        Date date = item.getDt_txt();
        String description = item.getWeatherData().getDescription();
        double tempMax = Math.round(item.getMain().getTempMax());
        int iconId = item.getWeatherData().getWeatherId();

        Intent intent = new Intent(this, DetailsActivity.class);
        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(this, requestID, intent, flags);

        int image = setImageResourceForWeatherCondition(iconId);

        Notification noti =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(getIconResourceForWeatherCondition((iconId)))
                        .setLargeIcon(drawableToBitmap(getResources()
                                .getDrawable(image)))
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(getFormattedDate(date))
                        .setSubText(description + "\t" + tempMax + "°C")
                        .setContentIntent(pIntent).build();

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, noti);

    }

    private int setImageResourceForWeatherCondition(int iconId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getArtResourceForWeatherCondition(iconId);
        }
        return getIconResourceForWeatherCondition(iconId);
    }

    @AfterViews
    public void setMainActivityActionBar() {
        setWeatherFragments(null);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
    }
}