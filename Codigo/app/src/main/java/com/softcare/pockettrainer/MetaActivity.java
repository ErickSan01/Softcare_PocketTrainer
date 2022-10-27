package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta);
        Intent i = getIntent();
        String eleccionDelgado = i.getStringExtra("cuerpo");
        if(eleccionDelgado.equalsIgnoreCase("delgado")){
            Button botonDelgado = findViewById(R.id.buttonPeso);
            Button botonAmbos = findViewById(R.id.buttonAmbos);
            deshabilitarBoton(botonDelgado);
            deshabilitarBoton(botonAmbos);
        }
    }

    public void deshabilitarBoton(Button boton){
        boton.setEnabled(false);
    }
    /*
    En estos métodos se llama a la siguiente actividad dependiendo de cuál fue el botón que
    se usó para llamarlo
    */
    public void peso(View v){
        Intent i = new Intent(this, ListoActivity.class);
        i.putExtra("meta", "peso");
        guardar("peso");
        startActivity(i);
    }
    public void musculo(View v){
        Intent i = new Intent(this, ListoActivity.class);
        i.putExtra("meta", "musculo");
        guardar("musculo");
        startActivity(i);
    }
    public void ambos(View v){
        Intent i = new Intent(this, ListoActivity.class);
        i.putExtra("meta", "ambos");
        guardar("ambos");
        startActivity(i);
    }

    /*
    Guarda las preferencias según su campo, también ajusta las otras elecciones no elegidas para
    que se conviertan en false automaticamente
    */
    public void guardar(String campo){
        SharedPreferences preferencias = getSharedPreferences("meta", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        if(campo.equals("peso")){
            editor.putBoolean(campo, true);
            editor.putBoolean("musculo", false);
            editor.putBoolean("ambos", false);
        }
        if(campo.equals("musculo")){
            editor.putBoolean("peso", false);
            editor.putBoolean(campo, true);
            editor.putBoolean("ambos", false);
        }
        if(campo.equals("ambos")){
            editor.putBoolean("peso", false);
            editor.putBoolean("musculo", false);
            editor.putBoolean(campo, true);
        }

        editor.apply();

        SharedPreferences datos = getSharedPreferences("primer_pantalla", MODE_PRIVATE);
        SharedPreferences.Editor editor_datos = datos.edit();
        editor_datos.putBoolean("tiene_datos",true);
        editor_datos.apply();
        finish();
    }
}