package com.example.mateusz.currency;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mateusz on 06.01.16.
 */
public class Storage {
    //10 lutego
    private static Storage mInstance = null;


    public Storage() {

    }


    public static Storage getInstance(){
        if(mInstance == null)
        {
            mInstance = new Storage();
        }
        return mInstance;
    }
    //koniec 10 lutego
    private String base;
    private String date;
    List<String> ratesID = new ArrayList<String>();
    List<String> ratesVAL= new ArrayList<String>();

    public Storage(String base, String date, List<String> ratesID, List<String> ratesVAL) {
        this.base = base;
        this.date = date;
        this.ratesID = ratesID;
        this.ratesVAL = ratesVAL;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getRatesID() {
        return ratesID;
    }

    public void setRatesID(List<String> ratesID) {
        this.ratesID = ratesID;
    }

    public List<String> getRatesVAL() {
        return ratesVAL;
    }

    public void setRatesVAL(List<String> ratesVAL) {
        this.ratesVAL = ratesVAL;
    }


    // private String ratesID[];
    //private String ratesVAL[];
  // Iterator<String> iter;


}
