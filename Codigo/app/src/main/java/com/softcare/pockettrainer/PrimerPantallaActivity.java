package com.softcare.pockettrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;



public class PrimerPantallaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer_pantalla);
        setTitle("Home");

        SharedPreferences datos = getSharedPreferences("primer_pantalla", MODE_PRIVATE);
        boolean tieneTodosLosDatos = datos.getBoolean("tiene_datos",false);

        if(tieneTodosLosDatos){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }


    public void iniciarCuestionario(View v){

        AyudanteBaseDeDatos ayudante = new AyudanteBaseDeDatos(this);

        //ayudante.agregarMateriales();
        //ayudante.agregarEjercicios();
        Intent i = new Intent(this, DiasLibresCuestionario.class);
        i.putExtra("origen", 1);
        startActivity(i);
        Log.d("iniciar cuesitonario", "Llendo a selección de horario");
    }

    public void ajustes(View v){
        Intent i = new Intent(this, AjustesActivity.class);
        startActivity(i);
    }

}