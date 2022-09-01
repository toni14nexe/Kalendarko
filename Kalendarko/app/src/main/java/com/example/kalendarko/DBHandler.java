package com.example.kalendarko;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "CalendarDatabase";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "EventCalendar";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, Event TEXT, Description TEXT, Repeat INTEGER)";
        db.execSQL(query);
    }

    public void addNewCourse(int id, String Date, String Event, String Description, int Repeat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("Date", Date);
        values.put("Event", Event);
        values.put("Description", Description);
        values.put("Repeat", Repeat);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<obavijest> dohvatiObavijesti() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<obavijest> obavijestiArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                obavijestiArrayList.add(new obavijest(
                        cursorCourses.getInt(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getInt(4)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return obavijestiArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}