package com.example.inicioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import conexion.Interfaz;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        //Interfaz interfaz = intent.getParcelableExtra("Interfaz");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}