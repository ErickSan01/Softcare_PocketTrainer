package com.softcare.pockettrainer;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class ExtraccionFechas extends AppCompatActivity {

    public boolean ejercicioCompletado(String fecha){
        SharedPreferences preferenciasFechas = getSharedPreferences("fechaEjercicio", MODE_PRIVATE);
        return preferenciasFechas.getBoolean(fecha, false);
    }
}
