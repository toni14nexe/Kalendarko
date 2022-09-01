package com.example.base_app_java;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureBTNunos();
        configureBTNazuriranje();
        configureBTNobrisi();
        configureBTNprikazi();
        configureBTNprikaziDanas();
        configureBTNprikaziMjesec();
        configureBTNprikaziGodina();
    }

    private void configureBTNunos(){
        Button btnUnos = (Button) findViewById(R.id.btnUnos);
        btnUnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SpremiPodatak.class));
            }
        });
    }

    private void configureBTNazuriranje(){
        Button btnAzuriranje = (Button) findViewById(R.id.btnAzuriranje);
        btnAzuriranje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Azuriranje.class));
            }
        });
    }

    private void configureBTNobrisi(){
        Button btnObrisi = (Button) findViewById(R.id.btnObrisi);
        btnObrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Brisanje.class));
            }
        });
    }

    private void configureBTNprikazi(){
        Button btnPrikazi = (Button) findViewById(R.id.btnPrikazSve);
        btnPrikazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PrikaziPodatak.class));
            }
        });
    }

    private void configureBTNprikaziDanas(){
        Button btnPrikazi = (Button) findViewById(R.id.btnPrikazDanas);
        btnPrikazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PrikaziDanas.class));
            }
        });
    }

    private void configureBTNprikaziMjesec(){
        Button btnPrikazi = (Button) findViewById(R.id.btnPrikazMjesec);
        btnPrikazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PrikaziMjesec.class));
            }
        });
    }

    private void configureBTNprikaziGodina(){
        Button btnPrikazi = (Button) findViewById(R.id.btnPrikazGodina);
        btnPrikazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PrikaziGodina.class));
            }
        });
    }
}