package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CuerpoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuerpo);

        //Parte solo para debug de logcat
        SharedPreferences preferencias = getSharedPreferences("cuerpo", MODE_PRIVATE);
        Log.d("Cuerpo elegido, delgado",""+preferencias.getBoolean("delgado",false));
        Log.d("Cuerpo elegido, robusto",""+preferencias.getBoolean("robusto",false));
        Log.d("Cuerpo elegido, fornido",""+preferencias.getBoolean("fornido",false));
    }
    /*
    En estos métodos se llama a la siguiente actividad dependiendo de cuál fue el botón que
    se usó para llamarlo
    */
    public void delgado(View v){
        Intent i = new Intent(this, MetaActivity.class);
        i.putExtra("cuerpo", "delgado");
        guardar("delgado");
        startActivity(i);
    }
    public void robusto(View v){
        Intent i = new Intent(this, MetaActivity.class);
        i.putExtra("cuerpo", "robusto");
        guardar("robusto");
        startActivity(i);
    }
    public void fornido(View v){
        Intent i = new Intent(this, MetaActivity.class);
        i.putExtra("cuerpo", "fornido");
        guardar("fornido");
        startActivity(i);
    }

    /*
    Guarda las preferencias según su campo, también ajusta las otras elecciones no elegidas para
    que se conviertan en false automaticamente
    */
    public void guardar(String campo){
        SharedPreferences preferencias = getSharedPreferences("cuerpo", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        if(campo.equals("delgado")){
            editor.putBoolean(campo, true);
            editor.putBoolean("robusto", false);
            editor.putBoolean("fornido", false);
        }
        if(campo.equals("robusto")){
            editor.putBoolean("delgado", false);
            editor.putBoolean(campo, true);
            editor.putBoolean("fornido", false);
        }
        if(campo.equals("fornido")){
            editor.putBoolean("delgado", false);
            editor.putBoolean("robusto", false);
            editor.putBoolean(campo, true);
        }

        editor.apply();
    }
}