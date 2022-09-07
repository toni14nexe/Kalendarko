package com.example.mykalendarko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ObrisiPodatak extends AppCompatActivity {

    Button delete;
    TextView dateTV, nameTV, descriptionTV, replayTV, odustani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrisi_podatak);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");
        String description = getIntent().getStringExtra("description");
        String replay = getIntent().getStringExtra("replay");

        nameTV = findViewById(R.id.name);
        dateTV = findViewById(R.id.date);
        descriptionTV = findViewById(R.id.description);
        replayTV = findViewById(R.id.replay);
        delete = findViewById(R.id.btnDelete);
        odustani = findViewById(R.id.btnOdustani);

        nameTV.setText(name);
        dateTV.setText(date);
        descriptionTV.setText(description);
        replayTV.setText("Svake godine ponoviti: " + replay);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ObrisiUpitnik.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ObrisiPodatak.this, Brisanje.class));
            }
        });
    }
}