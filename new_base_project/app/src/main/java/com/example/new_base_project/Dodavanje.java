package com.example.new_base_project;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Dodavanje extends AppCompatActivity {

    private EditText event, description;
    private String dateStr;
    private CalendarView calendarView;
    private SQLiteDatabase sqLiteDatabase;
    private Button tipkaUnos;
    private DBHelper DB;
    private Switch repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje);

        Date d = new Date();
        dateStr = String.valueOf(DateFormat.format("dd.MM.yyyy.", d.getTime()));
        event = findViewById(R.id.event);
        description = findViewById(R.id.description);
        calendarView = findViewById(R.id.kalendarUnos);
        repeat = findViewById(R.id.repeat);

        tipkaUnos = findViewById(R.id.tipkaUnos);
        DB = new DBHelper(this);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int god, int mj, int dan) {
                String mje;
                if(mj < 10){ mje = "0" + Integer.toString(mj); }
                else{ mje = Integer.toString(mj); }
                dateStr = Integer.toString(dan) + "." + mje + "." + Integer.toString(god) + ".";
                ReadDatabase(view);
            }
        });

        tipkaUnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean repeatBool = repeat.isChecked();
                int repeatInt = repeatBool ? 1 : 0;
                String eventStr = event.getText().toString();
                String descriptionStr = description.getText().toString();
                if (eventStr.length() == 0){
                    Toast.makeText(getBaseContext(), "Niste unijeli naziv!" , Toast.LENGTH_LONG ).show();
                }else{
                    Boolean checkinsertdata = DB.insertuserdata(dateStr, eventStr, descriptionStr, repeatInt);
                    if (checkinsertdata == true){
                        Toast.makeText(Dodavanje.this, event.getText().toString()+" - spremljeno " + dateStr, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Dodavanje.this, "Greška prilikom unosa", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void ReadDatabase(View view){
        String query = "Select Event from EventCalendar where Date =" + dateStr;
        try{
            Cursor cursor =  sqLiteDatabase.rawQuery(query, null);
            cursor.moveToFirst();
            event.setText(cursor.getString(0));
        }catch(Exception e){
            e.printStackTrace();
            event.setText("");
        }
    }
}
