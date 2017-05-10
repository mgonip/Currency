package com.example.mateusz.currency;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mateusz on 07.01.16.
 */
public class Praser {
private static final String TAG="Praser";

    public Praser()
    {

    }
    public Storage praseCurrentReading(String response)
    {
        try {
            //

            //
            JSONObject jsonResponse = new JSONObject(response);
                String ratesString = null;
                String base = jsonResponse.optString("base");
                String date = jsonResponse.optString("date");
        //    double maslo=0;
                List<String> ratesID = new ArrayList<String>();
                List<String> ratesVAL= new ArrayList<String>();
               // String ratesVAL[] = new String[0];
                Log.i(base,date);
//trzeba zrobic liste
                Iterator<String> iter = jsonResponse.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Log.i("keyIter",iter.toString());
                    try {
                        Object value = jsonResponse.get("rates");
                        Log.i("keyVal", value.toString());
                        ratesString=value.toString();
                        //Log.i("key",value.);
                    } catch (JSONException e) {
                        // Something went wrong!
                    }

                }
            //Taking Currency Name and Currency Value
            JSONObject jsonResponse2 = new JSONObject(ratesString);
            Iterator<String> iter2 = jsonResponse2.keys();
           // int i=0;
            while (iter2.hasNext()) {
                String key2 = iter2.next();
                try {
                    Object value2 = jsonResponse2.get(key2);
                    Log.i(key2.toString(), value2.toString()); // ID/Value brakuje listy lub czegos podobnego
                   /* maslo=Double.parseDouble(value2.toString())+1000;
                    Log.i("maslo", Double.toString(maslo));*/
                    ratesID.add(key2.toString());
                    ratesVAL.add(value2.toString());
                   // ratesVAL[i]=value2.toString();
                   // i++;
                    //Log.i("key",value.);
                } catch (JSONException e) {
                    // Something went wrong!
                }
            }
            Log.i("HKD",ratesID.get(9));
                //cos jeszcze nie trybi w Storage,java <z rates>
                return new Storage(base,date,ratesID,ratesVAL);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }


}
