package com.example.mykalendarko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ObrisiUpitnik extends AppCompatActivity {

    TextView textUpitnik;
    Button obrisi, odustani;
    com.example.mykalendarko.DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrisi_upitnik);

        String id = getIntent().getStringExtra("id");

        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");

        obrisi = findViewById(R.id.obrisiBTN);
        odustani = findViewById(R.id.odustaniBTN);

        textUpitnik = findViewById(R.id.textUpitnik);
        textUpitnik.setText("Želite li stvarno obrisati obavijest '" + name + "' od " + date + "?");

        DB = new com.example.mykalendarko.DBHelper(this);

        obrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkinsertdata = DB.deletedata(Integer.parseInt(id));
                if (checkinsertdata == true){
                    Toast.makeText(ObrisiUpitnik.this, "Obavijest '" + name + "' je obrisana", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ObrisiUpitnik.this, com.example.mykalendarko.Brisanje.class));
                    }else{
                        Toast.makeText(ObrisiUpitnik.this, "GREŠKA: Brisanje nije uspjelo!", Toast.LENGTH_LONG).show();
                    }
            }
        });

        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ObrisiUpitnik.this, com.example.mykalendarko.Brisanje.class));
            }
        });
    }
}
