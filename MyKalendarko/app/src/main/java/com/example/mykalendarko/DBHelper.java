package com.example.mykalendarko;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, dodatno TEXT, day TEXT, month TEXT, year TEXT, godisnje TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String name, String dodatno, String day, String month, String year, String godisnje) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("dodatno", dodatno);
        contentValues.put("day", day);
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("godisnje", godisnje);
        long result = DB.insert("Userdetails", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;

        }
    }

    public Boolean deletedata(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where id = ?", new String[]{Integer.toString(id)});
        if (cursor.getCount() > 0){
            long result = DB.delete("Userdetails", "id=?", new String[]{Integer.toString(id)});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public ArrayList<Obavijest> getAllData(){
        ArrayList<Obavijest> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Userdetails ORDER BY year, month, day ASC", null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String dodatno = cursor.getString(2);
            String day = cursor.getString(3);
            String month = cursor.getString(4);
            String year = cursor.getString(5);
            String godisnje = cursor.getString(6);
            Obavijest obavijest = new Obavijest(id, name, dodatno, day, month, year, godisnje);

            arrayList.add(obavijest);
        }
        return arrayList;
    }

    public ArrayList<Obavijest> getTodayData(){
        Calendar calendar = Calendar.getInstance();
        String dayTXT, monthTXT, yearTXT;
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH)+1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        dayTXT = String.valueOf(day);
        monthTXT = String.valueOf(month);
        yearTXT = String.valueOf(year);
        if (day < 10) { dayTXT = "0" + dayTXT; };
        if (month < 10) { monthTXT = '0' + monthTXT; };

        ArrayList<Obavijest> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Userdetails WHERE day="+dayTXT+" ORDER BY year, month, day ASC", null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String dodatno = cursor.getString(2);
            dayTXT = cursor.getString(3);
            monthTXT = cursor.getString(4);
            yearTXT = cursor.getString(5);
            String godisnje = cursor.getString(6);
            Obavijest obavijest = new Obavijest(id, name, dodatno, dayTXT, monthTXT, yearTXT, godisnje);

            arrayList.add(obavijest);
        }
        return arrayList;
    }

    public ArrayList<Obavijest> getMonthData(){
        Calendar calendar = Calendar.getInstance();
        String dayTXT, monthTXT, yearTXT;
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.DAY_OF_MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        dayTXT = String.valueOf(day);
        monthTXT = String.valueOf(month);
        System.out.println(month + " " + monthTXT);
        yearTXT = String.valueOf(year);
        if (day < 10) { dayTXT = "0" + dayTXT; };
        if (month < 10) { monthTXT = '0' + monthTXT; };
        System.out.println(month + " " + monthTXT);

        ArrayList<Obavijest> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Userdetails WHERE month="+monthTXT+" ORDER BY year, month, day ASC", null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String dodatno = cursor.getString(2);
            dayTXT = cursor.getString(3);
            monthTXT = cursor.getString(4);
            yearTXT = cursor.getString(5);
            String godisnje = cursor.getString(6);
            Obavijest obavijest = new Obavijest(id, name, dodatno, dayTXT, monthTXT, yearTXT, godisnje);

            arrayList.add(obavijest);
        }
        return arrayList;
    }

    public ArrayList<Obavijest> getYearData(){
        Calendar calendar = Calendar.getInstance();
        String dayTXT, monthTXT, yearTXT;
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH)+1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        dayTXT = String.valueOf(day);
        monthTXT = String.valueOf(month);
        yearTXT = String.valueOf(year);
        if (day < 10) { dayTXT = "0" + dayTXT; };
        if (month < 10) { monthTXT = '0' + monthTXT; };

        ArrayList<Obavijest> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Userdetails WHERE year="+yearTXT+" ORDER BY year, month, day ASC", null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String dodatno = cursor.getString(2);
            dayTXT = cursor.getString(3);
            monthTXT = cursor.getString(4);
            yearTXT = cursor.getString(5);
            String godisnje = cursor.getString(6);
            Obavijest obavijest = new Obavijest(id, name, dodatno, dayTXT, monthTXT, yearTXT, godisnje);

            arrayList.add(obavijest);
        }
        return arrayList;
    }
}
