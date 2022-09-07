package com.example.mykalendarko;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AzurirajPodatak extends AppCompatActivity {

    Button update;
    EditText etDate, name, napomena;
    Switch godisnje;
    DatePickerDialog.OnDateSetListener setListener;
    String dayTXT, monthTXT, yearTXT, switchTXT = "NE", date;
    com.example.mykalendarko.DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azuriraj_podatak);

        String id = getIntent().getStringExtra("id");
        String nameStr = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        String description = getIntent().getStringExtra("description");
        String replay = getIntent().getStringExtra("replay");

        name = findViewById(R.id.name);
        napomena = findViewById(R.id.dodatno);
        godisnje = findViewById(R.id.swt);
        update = findViewById(R.id.btnUpdate);
        etDate = findViewById(R.id.et_date);

        name.setText(nameStr);
        napomena.setText(description);
        etDate.setText(date);
        if (replay.contains("DA")) { godisnje.setChecked(true); }

        DB = new com.example.mykalendarko.DBHelper(this);

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

        dayTXT = date.substring(0,2);
        monthTXT = date.substring(3,5);
        yearTXT = date.substring(6,10);

        etDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AzurirajPodatak.this, new DatePickerDialog.OnDateSetListener() {
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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dodatnoTXT = napomena.getText().toString();
                String date = etDate.getText().toString();
                dayTXT = date.substring(0,2);
                monthTXT = date.substring(3,5);
                yearTXT = date.substring(6,10);

                if (nameTXT.isEmpty()){
                    Toast.makeText(AzurirajPodatak.this, "GREŠKA: Unesite naziv obavijesti!", Toast.LENGTH_LONG).show();
                }else if(dayTXT==null || monthTXT==null || yearTXT==null || etDate.getText().toString().length()!=11){
                    Toast.makeText(AzurirajPodatak.this, "GREŠKA: Neispravan datum!", Toast.LENGTH_LONG).show();
                }else{
                    DB.deletedata(Integer.parseInt(id));
                    Boolean checkinsertdata = DB.insertuserdata(nameTXT, dodatnoTXT, dayTXT, monthTXT, yearTXT, switchTXT);
                    if (checkinsertdata == true){
                        Toast.makeText(AzurirajPodatak.this, "Obavijest '" + nameTXT + "' je uspješno ažurirana", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AzurirajPodatak.this, com.example.mykalendarko.Azuriranje.class));
                    }else{
                        Toast.makeText(AzurirajPodatak.this, "GREŠKA: Ažuriranje nije uspjelo!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}