package com.example.kalendarko;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Date;

public class dodavanje extends AppCompatActivity {

    private bazaPodataka dbHandler;
    private EditText editText;
    private EditText opis;
    private CalendarView calendarView;
    private String odabraniDatum;
    private SQLiteDatabase sqLiteDatabase;
    private Switch preGod;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodavanje);

        Date d = new Date();
        odabraniDatum = String.valueOf(DateFormat.format("dd.MM.yyyy.", d.getTime()));
        editText = findViewById(R.id.naziv);
        opis = findViewById(R.id.opis);
        calendarView = findViewById(R.id.kalendarUnos);
        btn = findViewById(R.id.tipkaUnos);
        preGod = findViewById(R.id.preGod);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int god, int mj, int dan) {
                String mje;
                if(mj < 10){ mje = "0" + Integer.toString(mj); }
                else{ mje = Integer.toString(mj); }
                odabraniDatum = Integer.toString(dan) + "." + mje + "." + Integer.toString(god) + ".";
                ReadDatabase(view);
            }
        });

        try{
            dbHandler = new bazaPodataka(this, "CalendarDatabase", null, 1);
            sqLiteDatabase = dbHandler.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE EventCalendar(id INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, Event TEXT, Description TEXT, Repeat INTEGER)");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void InsertDatabase(View view){
        Boolean preGodBool = preGod.isChecked();
        int preGoodInt = preGodBool ? 1 : 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", odabraniDatum);
        contentValues.put("Event", editText.getText().toString());
        contentValues.put("Description", opis.getText().toString());
        contentValues.put("Repeat", preGoodInt);
        if(editText.length() == 0){
            Toast.makeText(getBaseContext(), "Niste unijeli naziv!" , Toast.LENGTH_LONG ).show();
        }else{
            sqLiteDatabase.insert("EventCalendar", null, contentValues);
            Toast.makeText(getBaseContext(), editText.getText().toString()+" - spremljeno " + odabraniDatum , Toast.LENGTH_LONG ).show();
        }
    }

    public void ReadDatabase(View view){
        String query = "Select Event from EventCalendar where Date =" + odabraniDatum;
        try{
            Cursor cursor =  sqLiteDatabase.rawQuery(query, null);
            cursor.moveToFirst();
            editText.setText(cursor.getString(0));
        }catch(Exception e){
            e.printStackTrace();
            editText.setText("");
        }
    }
}