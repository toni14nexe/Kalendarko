package com.example.base_app_java;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class SpremiPodatak extends AppCompatActivity {

    Button insert;
    EditText etDate, name, napomena;
    Switch godisnje;
    DatePickerDialog.OnDateSetListener setListener;
    String dayTXT, monthTXT, yearTXT, switchTXT = "NE", date;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spremi_podatak);

        name = findViewById(R.id.name);
        napomena = findViewById(R.id.dodatno);
        godisnje = findViewById(R.id.swt);
        insert = findViewById(R.id.btnInsert);
        etDate = findViewById(R.id.et_date);

        DB = new DBHelper(this);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        godisnje.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(godisnje.isChecked()){
                    switchTXT = "DA";
                }
                else{
                    switchTXT = "NE";
                }
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "." + month + "." + year + ".";
            }
        };

        etDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SpremiPodatak.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        if (day<10){ dayTXT = "0"+ Integer.toString(day);}
                        else { dayTXT = Integer.toString(day); }
                        if (month<10){ monthTXT = "0"+ Integer.toString(month);}
                        else { monthTXT = Integer.toString(month); }
                        yearTXT = Integer.toString(year);
                        date = day + "." + month + "." + year + ".";
                        etDate.setText(dayTXT + "." + monthTXT + "." + yearTXT + ".");
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dodatnoTXT = napomena.getText().toString();

                if (nameTXT.isEmpty()){
                    Toast.makeText(SpremiPodatak.this, "GREŠKA: Unesite naziv obavijesti!", Toast.LENGTH_LONG).show();
                }else if(dayTXT==null || monthTXT==null || yearTXT==null || etDate.getText().toString().length()!=11){
                    Toast.makeText(SpremiPodatak.this, "GREŠKA: Neispravan datum!", Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkinsertdata = DB.insertuserdata(nameTXT, dodatnoTXT, dayTXT, monthTXT, yearTXT, switchTXT);
                    if (checkinsertdata == true){
                        Toast.makeText(SpremiPodatak.this, "Obavijest '" + nameTXT + "' je uspješno spremljena", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SpremiPodatak.this, MainActivity.class));
                    }else{
                        Toast.makeText(SpremiPodatak.this, "GREŠKA: Spremanje nije uspjelo!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}