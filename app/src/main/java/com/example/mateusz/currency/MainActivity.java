package com.example.mateusz.currency;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateusz.currency.updater.AlarmUtils;

import java.util.ArrayList;
import java.util.List;




public class MainActivity  extends ActionBarActivity {

    //24 lutego
    private EditText et;
    private TextView tx;
    int Lspinner=0;
    int Rspinner=31;
    //
    private static String urlString = "http://api.fixer.io/latest";
    //initial database data for base EUR
    private static final String date ="2016-02-11";
    private static final String AUD="1.6018";
    private static final String BGN="1.9558";
    private static final String BRL="4.4836";
    private static final String CAD="1.5842";
    private static final String CHF="1.1027";
    private static final String CNY="7.4592";
    private static final String CZK="27.069";
    private static final String DKK="7.4638";
    private static final String GBP="0.7874";
    private static final String HKD="8.8406";
    private static final String HRK="7.6391";
    private static final String HUF="311.98";
    private static final String IDR="15286.39";
    private static final String ILS="4.4151";
    private static final String INR="77.655";
    private static final String JPY="127.3";
    private static final String KRW="1362.83";
    private static final String MXN="21.6064";
    private static final String MYR="4.7033";
    private static final String NOK="9.7085";
    private static final String NZD="1.6973";
    private static final String PHP="53.953";
    private static final String PLN="4.4485";
    private static final String RON="4.4783";
    private static final String RUB="90.8866";
    private static final String SEK="9.5188";
    private static final String SGD="1.5774";
    private static final String THB="39.995";
    private static final String TRY="3.3217";
    private static final String USD="1.1347";
    private static final String ZAR="17.9877";
    private static final String EUR="1";

    Database db = new Database(this);


    private Context context;
    private TextView tvDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        //10 lutego
        //initial database add


        if(db.isEmpty())
        {
            Log.i("INITIAL", "Database is emepty !!!!");
            db.Add("AUD",AUD, date);
            db.Add("BGN",BGN, date);
            db.Add("BRL",BRL, date);
            db.Add("CAD",CAD, date);
            db.Add("CHF",CHF, date);
            db.Add("CNY",CNY, date);
            db.Add("CZK",CZK, date);
            db.Add("DKK",DKK, date);
            db.Add("GBP",GBP, date);
            db.Add("HKD",HKD, date);
            db.Add("HRK",HRK, date);
            db.Add("HUF",HUF, date);
            db.Add("IDR",IDR, date);
            db.Add("ILS",ILS, date);
            db.Add("INR",INR, date);
            db.Add("JPY",JPY, date);
            db.Add("KRW",KRW, date);
            db.Add("MXN",MXN, date);
            db.Add("MYR",MYR, date);
            db.Add("NOK",NOK, date);
            db.Add("NZD",NZD, date);
            db.Add("PHP",PHP, date);
            db.Add("PLN",PLN, date);
            db.Add("RON",RON, date);
            db.Add("RUB",RUB, date);
            db.Add("SEK",SEK, date);
            db.Add("SGD",SGD, date);
            db.Add("THB",THB, date);
            db.Add("TRY",TRY, date);
            db.Add("USD",USD, date);
            db.Add("ZAR",ZAR, date);
            db.Add("EUR",EUR, date);

        }
        else
        {
            Log.i("INITIAL", "Database is NOT emepty !!!!");
        }

        AlarmUtils.setUpdateCurrenciesAlarm(context);

        //
        tvDate = (TextView) findViewById(R.id.tvDate);

      //  String[] elementy = {"Opcja 1", "Opcja 2", "Opcja 3", "Opcja 4", "Opcja 5"}; //do adaptera

     //final TextView tv = (TextView) findViewById(R.id.tv);


     //  tv.setText("Welcome to android");

        //downloadLastReadings();
        new AsyncHTTP(context){
            @Override
            public <T> void invokeOnData(T t) {
                try {
                    Storage item = (Storage) t;
                    tvDate.setText("Last Update: "+item.getDate());
                    Log.i("DATA",item.getDate());
                //    Log.i("rates",item.getIter().next());//zle
                    List<String> ratesID = new ArrayList<String>();
                    List<String> ratesVAL= new ArrayList<String>();
                    ratesID=item.getRatesID();
                    ratesVAL=item.getRatesVAL();
                    Log.i("asd", ratesVAL.toString());
                    Log.i("zxc", ratesID.toString());
                    Log.i(ratesID.get(1).toString(), ratesVAL.get(1).toString());

                    //11 luty
                    //**Update data
                    //brakuje dodania auto-update
                    Cursor k=db.Read();
                    int zz=1;
                    while(k.moveToNext() && zz<=ratesID.size())
                    {
                        db.Update(zz,ratesID.get(zz-1),ratesVAL.get(zz-1),item.getDate());
                        zz++;
                    }

                    k.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();


        Button b = (Button) findViewById(R.id.button);
        et = (EditText) findViewById(R.id.EditTextL);
        tx = (TextView) findViewById(R.id.textViewR);


        b.setOnClickListener(new View.OnClickListener() {
        @Override

        public void onClick(View v) {
            double valL;
            double valR;
            Cursor k =db.Read();
            int control=0;
            Log.i("LSpinner1",Integer.toString(Lspinner));
            String Ls="1";
            String Rs="1";


            while(k.moveToNext())
            {
               if(control==Lspinner)
               {
                   Ls=k.getString(2);
               }
               if(control==Rspinner)
               {
                   Rs=k.getString(2);
               }
                control++;
            }
            Log.i("RVal",Rs);
            Log.i("LVal", Ls);
            double RValD=Double.parseDouble(Rs);
            double LValD=Double.parseDouble(Ls);

            if(et.getText().length()!=0)
            {
               double EValD=Double.parseDouble(et.getText().toString());
                double result=EValD/LValD*RValD;
                    tx.setText(Double.toString(result));

            }
            else
            {
                tx.setText("");
            }
            k.close();

        }
        });


        //***************
        //*Spinner Left *
        //***************
        String[] elementy = {"AUD", "BGN", "BRL", "CAD", "CHF","CNY","CZK","DKK","GBP","HKD","HRK","HUF","IDR","ILS","INR","JPY","KRW","MXN","MYR","NOK","NZD","PHP","PLN","RON","RUB","SEK","SGD","THB","TRY","USD","ZAR","EUR"};

        final Spinner spinnerL = (Spinner)findViewById(R.id.SpinnerLeft);
        ArrayAdapter adapterL = new ArrayAdapter(this, android.R.layout.simple_spinner_item, elementy);
        spinnerL.setAdapter(adapterL);
//
        spinnerL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                               @Override
                                               public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                                          int id, long position) {
                                                   String[] elementy = {"AUD", "BGN", "BRL", "CAD", "CHF","CNY","CZK","DKK","GBP","HKD","HRK","HUF","IDR","ILS","INR","JPY","KRW","MXN","MYR","NOK","NZD","PHP","PLN","RON","RUB","SEK","SGD","THB","TRY","USD","ZAR","EUR"};
                                                   Toast.makeText(MainActivity.this, "Converting " + (elementy[id]), Toast.LENGTH_SHORT).show();
                                                   switch((int)position)
                                                   {
                                                       case 0:
                                                           Log.i("SpinnerLeft","case0");
                                                           Lspinner=0;
                                                           break;
                                                       case 1:
                                                           Log.i("SpinnerLeft", "case1");
                                                           Lspinner=1;
                                                           break;
                                                       case 2:
                                                           Log.i("SpinnerLeft", "case2");
                                                           Lspinner=2;
                                                           break;
                                                       case 3:
                                                           Log.i("SpinnerLeft", "case3");
                                                           Lspinner=3;
                                                           break;
                                                       case 4:
                                                           Log.i("SpinnerLeft", "case4");
                                                           Lspinner=4;
                                                           break;
                                                       case 5:
                                                           Log.i("SpinnerLeft", "case5");
                                                           Lspinner=5;
                                                           break;
                                                       case 6:
                                                           Log.i("SpinnerLeft", "case6");
                                                           Lspinner=6;
                                                           break;
                                                       case 7:
                                                           Log.i("SpinnerLeft", "case7");
                                                           Lspinner=7;
                                                           break;
                                                       case 8:
                                                           Log.i("SpinnerLeft", "case8");
                                                           Lspinner=8;
                                                           break;
                                                       case 9:
                                                           Log.i("SpinnerLeft", "case9");
                                                           Lspinner=9;
                                                           break;
                                                       case 10:
                                                           Log.i("SpinnerLeft", "case10");
                                                           Lspinner=10;
                                                           break;
                                                       case 11:
                                                           Log.i("SpinnerLeft", "case11");
                                                           Lspinner=11;
                                                           break;
                                                       case 12:
                                                           Log.i("SpinnerLeft", "case12");
                                                           Lspinner=12;
                                                           break;
                                                       case 13:
                                                           Log.i("SpinnerLeft", "case13");
                                                           Lspinner=13;
                                                           break;
                                                       case 14:
                                                           Log.i("SpinnerLeft", "case14");
                                                           Lspinner=14;
                                                           break;
                                                       case 15:
                                                           Log.i("SpinnerLeft", "case15");
                                                           Lspinner=15;
                                                           break;
                                                       case 16:
                                                           Log.i("SpinnerLeft", "case16");
                                                           Lspinner=16;
                                                           break;
                                                       case 17:
                                                           Log.i("SpinnerLeft", "case17");
                                                           Lspinner=17;
                                                           break;
                                                       case 18:
                                                           Log.i("SpinnerLeft", "case18");
                                                           Lspinner=18;
                                                           break;
                                                       case 19:
                                                           Log.i("SpinnerLeft", "case19");
                                                           Lspinner=19;
                                                           break;
                                                       case 20:
                                                           Log.i("SpinnerLeft", "case20");
                                                           Lspinner=20;
                                                           break;
                                                       case 21:
                                                           Log.i("SpinnerLeft", "case21");
                                                           Lspinner=21;
                                                           break;
                                                       case 22:
                                                           Log.i("SpinnerLeft", "case22");
                                                           Lspinner=22;
                                                           break;
                                                       case 23:
                                                           Log.i("SpinnerLeft", "case23");
                                                           Lspinner=23;
                                                           break;
                                                       case 24:
                                                           Log.i("SpinnerLeft", "case24");
                                                           Lspinner=24;
                                                           break;
                                                       case 25:
                                                           Log.i("SpinnerLeft", "case25");
                                                           Lspinner=25;
                                                           break;
                                                       case 26:
                                                           Log.i("SpinnerLeft", "case26");
                                                           Lspinner=26;
                                                           break;
                                                       case 27:
                                                           Log.i("SpinnerLeft", "case27");
                                                           Lspinner=27;
                                                           break;
                                                       case 28:
                                                           Log.i("SpinnerLeft", "case28");
                                                           Lspinner=28;
                                                           break;
                                                       case 29:
                                                           Log.i("SpinnerLeft", "case29");
                                                           Lspinner=29;
                                                           break;
                                                       case 30:
                                                           Log.i("SpinnerLeft", "case30");
                                                           Lspinner=30;
                                                           break;
                                                       case 31:
                                                           Log.i("SpinnerLeft", "case31");
                                                           Lspinner=31;
                                                           break;
                                                   }
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> arg0) {

                                               }
                                           }
        );

//
        //***************
        //*Spinner Right*
        //***************
        final Spinner spinnerR = (Spinner)findViewById(R.id.SpinnerRight);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, elementy);
        spinnerR.setAdapter(adapter);

        spinnerR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int id, long position) {
                String[] elementy = {"AUD", "BGN", "BRL", "CAD", "CHF","CNY","CZK","DKK","GBP","HKD","HRK","HUF","IDR","ILS","INR","JPY","KRW","MXN","MYR","NOK","NZD","PHP","PLN","RON","RUB","SEK","SGD","THB","TRY","USD","ZAR","EUR"};
                Toast.makeText(MainActivity.this, "Convert To " + (elementy[id]), Toast.LENGTH_SHORT).show();
                switch((int)position)
                {
                    case 0:
                        Log.i("SpinnerRight","case0");
                        Rspinner=0;
                        break;
                    case 1:
                        Log.i("SpinnerRight", "case1");
                        Rspinner=1;
                        break;
                    case 2:
                        Log.i("SpinnerRight", "case2");
                        Rspinner=2;
                        break;
                    case 3:
                        Log.i("SpinnerRight", "case3");
                        Rspinner=3;
                        break;
                    case 4:
                        Log.i("SpinnerRight", "case4");
                        Rspinner=4;
                        break;
                    case 5:
                        Log.i("SpinnerRight", "case5");
                        Rspinner=5;
                        break;
                    case 6:
                        Log.i("SpinnerRight", "case6");
                        Rspinner=6;
                        break;
                    case 7:
                        Log.i("SpinnerRight", "case7");
                        Rspinner=7;
                        break;
                    case 8:
                        Log.i("SpinnerRight", "case8");
                        Rspinner=8;
                        break;
                    case 9:
                        Log.i("SpinnerRight", "case9");
                        Rspinner=9;
                        break;
                    case 10:
                        Log.i("SpinnerRight", "case10");
                        Rspinner=10;
                        break;
                    case 11:
                        Log.i("SpinnerRight", "case11");
                        Rspinner=11;
                        break;
                    case 12:
                        Log.i("SpinnerRight", "case12");
                        Rspinner=12;
                        break;
                    case 13:
                        Log.i("SpinnerRight", "case13");
                        Rspinner=13;
                        break;
                    case 14:
                        Log.i("SpinnerRight", "case14");
                        Rspinner=14;
                        break;
                    case 15:
                        Log.i("SpinnerRight", "case15");
                        Rspinner=15;
                        break;
                    case 16:
                        Log.i("SpinnerRight", "case16");
                        Rspinner=16;
                        break;
                    case 17:
                        Log.i("SpinnerRight", "case17");
                        Rspinner=17;
                        break;
                    case 18:
                        Log.i("SpinnerRight", "case18");
                        Rspinner=18;
                        break;
                    case 19:
                        Log.i("SpinnerRight", "case19");
                        Rspinner=19;
                        break;
                    case 20:
                        Log.i("SpinnerRight", "case20");
                        Rspinner=20;
                        break;
                    case 21:
                        Log.i("SpinnerRight", "case21");
                        Rspinner=21;
                        break;
                    case 22:
                        Log.i("SpinnerRight", "case22");
                        Rspinner=22;
                        break;
                    case 23:
                        Log.i("SpinnerRight", "case23");
                        Rspinner=23;
                        break;
                    case 24:
                        Log.i("SpinnerRight", "case24");
                        Rspinner=24;
                        break;
                    case 25:
                        Log.i("SpinnerRight", "case25");
                        Rspinner=25;
                        break;
                    case 26:
                        Log.i("SpinnerRight", "case26");
                        Rspinner=26;
                        break;
                    case 27:
                        Log.i("SpinnerRight", "case27");
                        Rspinner=27;
                        break;
                    case 28:
                        Log.i("SpinnerRight", "case28");
                        Rspinner=28;
                        break;
                    case 29:
                        Log.i("SpinnerRight", "case29");
                        Rspinner=29;
                        break;
                    case 30:
                        Log.i("SpinnerRight", "case30");
                        Rspinner=30;
                        break;
                    case 31:
                        Log.i("SpinnerRight", "case31");
                        Rspinner=31;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        }
        );

    }


}