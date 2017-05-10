package com.example.mateusz.currency;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mateusz on 06.01.16.
 */
public class HttpToString {
    private static final String TAG = "HttpToString";

    public HttpToString() {
    }
   public String GettingString() {

        String result = null;
        StringBuffer sb = new StringBuffer();
        InputStream is = null;

        try {
            URL UrlString = new URL("http://api.fixer.io/latest");
            HttpURLConnection connection = (HttpURLConnection) UrlString.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();
        } catch (Exception e) {
            Log.i(TAG, "Error reading InputStream");
            result = null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.i(TAG, "Error closing InputStream");
                }
            }
        }
        Log.i(TAG,result);
        return result;
    }
}