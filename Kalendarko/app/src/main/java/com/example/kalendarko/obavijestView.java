package com.example.kalendarko;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class obavijestView extends AppCompatActivity {
    private ArrayList<obavijest> courseModalArrayList;
    private DBHandler dbHandler;
    private obavijestAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obavijest_view);

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(obavijestView.this);

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.dohvatiObavijesti();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new obavijestAdapter(courseModalArrayList, obavijestView.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(obavijestView.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }
}