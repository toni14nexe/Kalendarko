package com.example.mykalendarko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Brisanje extends AppCompatActivity {

    com.example.mykalendarko.DBHelper DB;
    ListView lista;
    ArrayList<Obavijest> arrayList;
    MyAdapter myAdapter;
    Button btnIzbornik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikazi_podatak);

        DB = new com.example.mykalendarko.DBHelper(this);
        btnIzbornik = findViewById(R.id.btnIzbornik);
        lista = (ListView) findViewById(R.id.lista);
        arrayList = new ArrayList<>();
        loadDataInListView();

        btnIzbornik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Brisanje.this, MainActivity.class));
            }
        });
    }

    private void loadDataInListView() {
        arrayList = DB.getAllData();
        myAdapter = new MyAdapter(this, arrayList);
        lista.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        lista.setAdapter(myAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Obavijest obavijest = (Obavijest) arrayList.get(position);
                Intent intent = new Intent(getBaseContext(), com.example.mykalendarko.ObrisiPodatak.class);
                intent.putExtra("id", String.valueOf(obavijest.getID()));
                intent.putExtra("name", obavijest.getName());
                intent.putExtra("description", obavijest.getDodatno());
                intent.putExtra("replay", obavijest.getGodisnje());
                String day = obavijest.getDay();
                String month = obavijest.getMonth();
                String year = obavijest.getYear();

                intent.putExtra("date", day+'.'+month+'.'+year+'.');
                startActivity(intent);
            }
        });
    }
}