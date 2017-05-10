package com.example.mateusz.currency.updater;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Piotr on 26.02.2016.
 */

public class BootBrodcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmUtils.setUpdateCurrenciesAlarm(context);
    }
}
