package com.example.mateusz.currency.updater;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Piotr on 26.02.2016.
 */

public class UpdateBrodcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d("UBR", "update intent received");
        Intent service = new Intent(context, UpdateService.class);
        context.startService(service);
    }
}
