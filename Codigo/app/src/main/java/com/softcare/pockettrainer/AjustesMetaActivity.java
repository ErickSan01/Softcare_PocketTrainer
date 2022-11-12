package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AjustesMetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_meta);

        Button backBtn = (Button) findViewById(R.id.backBtnAjustesMeta);
        SharedPreferences preferencias = getSharedPreferences("cuerpo", MODE_PRIVATE);
        boolean esDelgado = preferencias.getBoolean("delgado", false);
        Button botonPeso = findViewById(R.id.buttonPeso);
        Button botonAmbos = findViewById(R.id.buttonFornido);

        if(esDelgado){
            botonPeso.setEnabled(false);
            botonAmbos.setEnabled(false);
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    /*
    En estos métodos se llama a la siguiente actividad dependiendo de cuál fue el botón que
    se usó para llamarlo
    */
    public void peso(View v){
        guardar("peso");
        finish();
    }
    public void musculo(View v){
        guardar("musculo");
        finish();
    }
    public void ambos(View v){
        guardar("ambos");
        finish();
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