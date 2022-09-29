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

        if(tieneTodosLosDatos) {
            Intent i = new Intent(this, AjustesActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void iniciarCuestionario(View v){


        Intent i = new Intent(this, SeleccionTiempoActivity.class);
        startActivity(i);
        Log.d("iniciar cuesitonario", "Llendo a selección de horario");
    }

    public void ajustes(View v){
        Intent i = new Intent(this, AjustesActivity.class);
        startActivity(i);
    }

}