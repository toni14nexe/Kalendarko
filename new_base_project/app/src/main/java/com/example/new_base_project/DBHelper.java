package com.example.new_base_project;

import static android.os.Build.ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("DROP TABLE IF EXISTS Userdetails");
        DB.execSQL("CREATE TABLE EventCalendar(ID INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, Event TEXT, Description TEXT, Repeat INTEGER");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists EventCalendar");
    }

    public Boolean insertuserdata(String date, String event, String description, int repeat) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", date);
        contentValues.put("Event", event);
        contentValues.put("Description", description);
        contentValues.put("Repeat", repeat);
        long result = DB.insert("EventCalendar", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(int id, String date, String event, String description, int repeat) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", date);
        contentValues.put("Event", event);
        contentValues.put("Description", description);
        contentValues.put("Repeat", repeat);
        Cursor cursor = DB.rawQuery("Select * from EventCalendar where ID = '" + id + "'", null);
        if (cursor.getCount() > 0){
            long result = DB.update("EventCalendar", contentValues, "ID='" + id + "'", null);
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deletedata(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from EventCalendar where ID = '" + id + "'", null);
        if (cursor.getCount() > 0){
            long result = DB.delete("EventCalendar", "id='" + id + "'", null);
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from EventCalendar", null);
        return cursor;
    }
}