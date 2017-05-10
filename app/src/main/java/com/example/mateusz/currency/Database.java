package com.example.mateusz.currency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mateusz on 04.01.16.
 */
public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "currency.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table curr(" +
                        "id integer primary key autoincrement," +
                        "namerates text," +
                        "valrates text," +
                        "date text);" +
                        "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean isEmpty()
    {
        boolean flag;
        String quString = "select exists(select 1 from " + "curr"  + ");";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(quString, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        if (count ==1) {
            flag =  false;
        } else {
            flag = true;
        }
        cursor.close();
        db.close();

        return flag;
    }

    public void Add(String namerates,String valrates, String date){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("namerates", namerates);
        val.put("valrates",valrates);
        val.put("date", date);
        db.insertOrThrow("curr", null, val);
    }

    public Cursor Read(){
        String[] p={"id","namerates","valrates","date"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =db.query("curr",p,null,null,null,null,null);
        return c;
    }
    public void Update(int id,String namerates,String valrates,String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues val= new ContentValues();
        val.put("namerates",namerates);
        val.put("valrates",valrates);
        val.put("date",date);
        String args[]={id+""};
        db.update("curr",val,"id=?",args);
    }
    public void Delete(int idd){
        SQLiteDatabase db = getWritableDatabase();
        String[] argumenty={""+idd};
        db.delete("curr", "id=?", argumenty);

    }



}
