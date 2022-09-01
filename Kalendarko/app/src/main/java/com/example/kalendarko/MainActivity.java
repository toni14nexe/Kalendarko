package com.example.kalendarko;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private bazaPodataka dbHandler;
    private Button btnListaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            dbHandler = new bazaPodataka(this, "CalendarDatabase", null, 1);
            db = dbHandler.getWritableDatabase();
            db.execSQL("CREATE TABLE EventCalendar(id INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, Event TEXT, Description TEXT, Repeat INTEGER)");
        }catch(Exception e){
            e.printStackTrace();
        }

        btnListaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, obavijestView.class);
                startActivity(i);
            }
        });

        getAllData();
    }

    //IZVAN CREATEA

    private void configureBtnUnosActivity(){
        Button btnUnosActivity = (Button) findViewById(R.id.btnUnosActivity);
        btnUnosActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, dodavanje.class));
            }
        });
    }

    public ArrayList<obavijest> getAllData(){
        ArrayList<obavijest> arrayList = new ArrayList<>();
        db = dbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EventCalendar ORDER BY Date ASC", null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String event = cursor.getString(2);
            String description = cursor.getString(3);
            int repeat = cursor.getInt(4);
            obavijest obavijest = new obavijest(id, date, event, description, repeat);

            arrayList.add(obavijest);
            System.out.println(obavijest);
        }
        return arrayList;
    }
}