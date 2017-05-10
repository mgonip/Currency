package com.example.mateusz.currency;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

/**
 * Created by Mateusz on 06.01.16.
 */

public class AsyncHTTP extends AsyncTask<Void, Void, Storage> implements InvokeOnData {
    private static final String TAG = AsyncHTTP.class.getSimpleName();
    public AsyncHTTP(Context context) {
    }

    @Override
    protected Storage doInBackground(Void... params) {
        try {
            Log.d(TAG, "Started task");
            HttpToString H = new HttpToString();
            String response = H.GettingString();
            Log.d(TAG, "Downloaded content");
            Log.d("responseFromAsyncHHTP",response);
            Praser P=new Praser();
            Storage item =P.praseCurrentReading(response);
            Log.d(TAG, "Parsed content");
            return item;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Storage item) {
        Log.d(TAG, "Downloaded LastReadings");
        invokeOnData(item);
    }

    @Override
    public <T> void invokeOnData(T t) {

    }

}
