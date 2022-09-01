package com.example.lv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d( "MainActivity", "onCreate")

        val num1 = findViewById(R.id.editTextNumber) as EditText
        val num2 = findViewById(R.id.editTextNumber2) as EditText
        val tot = findViewById(R.id.editTextNumber4) as EditText
        val button = findViewById(R.id.button2) as Button

        button.setOnClickListener{
            val val1 = num1.text.toString().toInt()
            val val2 = num2.text.toString().toInt()
            val result = val1 + val2;
            tot.setText(result.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d( "MainActivity", "onResume")
    }

    override fun onPause() {
        super.onResume()
        Log.d( "MainActivity", "onPause")
    }

    override fun onDestroy() {
        super.onResume()
        Log.d( "MainActivity", "onDestroy")
    }
}