package com.example.mateusz.currency.updater;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.mateusz.currency.AsyncHTTP;
import com.example.mateusz.currency.Database;
import com.example.mateusz.currency.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr on 26.02.2016.
 */

public class UpdateService extends Service {
    private static final String TAG = UpdateService.class.getSimpleName();
    private Context context;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = this;

        new AsyncHTTP(context) {

            @Override
            public <T> void invokeOnData(T t) {
                Log.d(TAG, "Updating currencies...");
                try {
                    Storage item = (Storage) t;
                    if (item == null) {
                        Log.d(TAG, "Failed to update currencies");
                        stopSelf();
                    }
                    Database db = new Database(context);
                    List<String> ratesNames = new ArrayList<String>();
                    List<String> ratesValues = new ArrayList<String>();
                    ratesNames = item.getRatesID();
                    ratesValues = item.getRatesVAL();
                    Cursor cursor = db.Read();
                    int i=1;
                    while(cursor.moveToNext() && i<=ratesNames.size()) {
                        db.Update(i, ratesNames.get(i-1), ratesValues.get(i-1), item.getDate());
                        i++;
                    }
                    cursor.close();
                    db.close();
                    Log.d(TAG, Integer.toString(i) + " currencies updated");
                    stopSelf();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.execute();
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
