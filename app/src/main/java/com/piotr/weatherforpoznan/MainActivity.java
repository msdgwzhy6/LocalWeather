package com.piotr.weatherforpoznan;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.piotr.weatherforpoznan.adapter.ForecastAdapter;
import com.piotr.weatherforpoznan.model.ForecastItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.piotr.weatherforpoznan.utils.Utility.getArtResourceForWeatherCondition;
import static com.piotr.weatherforpoznan.utils.Utility.getIconResourceForWeatherCondition;

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

    ForecastAdapter mForecastAdapter;

    @UiThread
    protected void setWeatherFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mActivity, new MainActivityFragment_())
                    .commit();
        }
        List<ForecastItem> forecastItems = new Select().from(ForecastItem.class).execute();
        Log.d("HHHHHHHHHHHH", "" + forecastItems.get(1).getDt_txt().toString());
        ForecastItem item = new Select().from(ForecastItem.class).where("id = ?", forecastItems.get(1).getId())
                .executeSingle();
        setWeatherNotification(item.getDt_txt(),
                item.getWeatherData().getDescription(),
                Math.round(item.getMain().getTempMax()),
                item.getWeatherData().getWeatherId());

    }

    private static String getFormattedDate(Date date) {
        String formattedDay = new SimpleDateFormat("d MMMM y HH:mm").format(date);
        return formattedDay;
    }

    protected void setWeatherNotification(Date date, String description, double tempMax,
                                          int iconId) {
        Intent intent = new Intent(this, DetailsActivity.class);
        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(this, requestID, intent, flags);
        Notification noti =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(getIconResourceForWeatherCondition((iconId)))
                        .setLargeIcon(drawableToBitmap(getResources()
                                .getDrawable(getArtResourceForWeatherCondition(iconId))))
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(getFormattedDate(date))
                        .setSubText(description + "\t" + tempMax + "°C")

                        .setContentIntent(pIntent).build();

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, noti);

    }

    @AfterViews
    public void setMainActivityActionBar() {
        setWeatherFragments(null);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    //FIXME: Reformat code, extract to methods
}