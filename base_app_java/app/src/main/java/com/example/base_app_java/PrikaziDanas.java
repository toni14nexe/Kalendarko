package com.example.base_app_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class PrikaziDanas extends AppCompatActivity {

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
                startActivity(new Intent(PrikaziDanas.this, MainActivity.class));
            }
        });
    }

    private void loadDataInListView() {
        arrayList = DB.getTodayData();
        myAdapter = new MyAdapter(this, arrayList);
        lista.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}
