package com.example.base_app_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PrikaziPodatak extends AppCompatActivity {

    DBHelper DB;
    ListView lista;
    ArrayList<Obavijest> arrayList;
    MyAdapter myAdapter;
    Button btnIzbornik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikazi_podatak);

        DB = new DBHelper(this);
        btnIzbornik = findViewById(R.id.btnIzbornik);
        lista = (ListView) findViewById(R.id.lista);
        arrayList = new ArrayList<>();
        loadDataInListView();

        btnIzbornik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrikaziPodatak.this, MainActivity.class));
            }
        });
    }

    private void loadDataInListView() {
        arrayList = DB.getAllData();
        myAdapter = new MyAdapter(this, arrayList);
        lista.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}