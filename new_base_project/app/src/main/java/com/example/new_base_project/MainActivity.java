package com.example.new_base_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        configureInsertButton();

        /*update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatetdata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);
                if (checkupdatetdata == true){
                    Toast.makeText(MainActivity.this, "Uspješno ažurirano", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Nije ažurirano", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        /*delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if (checkdeletedata == true){
                    Toast.makeText(MainActivity.this, "Uspješno obrisano", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Brisanje nije uspjelo", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Krivi unos!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Ime i prezime: "+res.getString(0)+"\n");
                    buffer.append("Kontakt: "+res.getString(1)+"\n");
                    buffer.append("Datum rođenja: "+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Korisnički unos");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    private void configureInsertButton(){
        Button nextBTN = (Button) findViewById(R.id.btnInsert);
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Dodavanje.class));
            }
        });
    }
}