package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HistorialDiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_dia);

        Intent i = getIntent();

        String fecha = i.getStringExtra("fecha");
        initText(fecha);
    }

    public void initText(String dia){
        TextView textViewFecha = (TextView) findViewById(R.id.textViewFecha);
        textViewFecha.setText(dia);
    }
}