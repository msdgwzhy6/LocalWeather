package com.piotr.weatherforpoznan.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by piotr on 15.05.16.
 */
public final class NotificationServiceStarterReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationEventReceiver.setupAlarm(context);
    }
}
