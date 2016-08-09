package com.piotr.localweather.utils;

import android.content.Context;
import android.text.format.Time;

import com.piotr.localweather.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author piotr on 16.05.16.
 */
public class DateUtils {

    public static String getFormattedDate(Date date) {
        return new SimpleDateFormat("d MMMM y HH:mm").format(date);
    }

    public static String getDayName(Context context, long dateInMillis) {
        // If the date is today, return the localized version of "Today" instead of the actual
        // day name.

        Time t = new Time();
        t.setToNow();
        int julianDay = Time.getJulianDay(dateInMillis, t.gmtoff);
        int currentJulianDay = Time.getJulianDay(System.currentTimeMillis(), t.gmtoff);
        if (julianDay == currentJulianDay) {
            return context.getString(R.string.today);
        } else if (julianDay == currentJulianDay + 1) {
            return context.getString(R.string.tomorrow);
        } else {
            Time time = new Time();
            time.setToNow();
            // Otherwise, the format is just the day of the week (e.g "Wednesday".
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            return dayFormat.format(dateInMillis);
        }
    }
}
