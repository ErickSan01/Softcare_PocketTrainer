package com.softcare.pockettrainer.rutinas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;
import com.softcare.pockettrainer.PrimerPantallaActivity;
import com.softcare.pockettrainer.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Rutinas extends AppCompatActivity {
    private AyudanteBaseDeDatos ayudante;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutinas_activity);
        preferences = getSharedPreferences("dias", Rutinas.MODE_PRIVATE);
        Button backButton = (Button) findViewById(R.id.backBtn);
        RecyclerView lista = (RecyclerView) findViewById(R.id.listaRutinas);

        String[] dias = getDias();

        ayudante = new AyudanteBaseDeDatos(Rutinas.this);
        //SQLiteDatabase db = ayudante.getWritableDatabase();

        RutinasAdapter rutinasAdapter = new RutinasAdapter(dias, this);
        lista.setHasFixedSize(false);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(rutinasAdapter);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent mainPage = new Intent(Rutinas.this, PrimerPantallaActivity.class);
                startActivity(mainPage);
            }
        });
    }

    public String[] getDias(){
        Map<String, ?> dias = preferences.getAll();

        ArrayList<String> diasArr = new ArrayList<String>();
        ArrayList<Boolean> valsArr = new ArrayList<Boolean>();

        for (String day: dias.keySet()) {
            diasArr.add(day);
        }
        for (Object val: dias.values()) {
            valsArr.add((Boolean) val);
        }

        String[] diasS = new String[7];
        int posString = 0;

        for(int pos = 0; pos < diasArr.size(); pos++){
            if(valsArr.get(pos)){
                String dia = diasArr.get(pos);
                dia = dia.substring(0,1).toUpperCase() + dia.substring(1);
                diasS[posString] = dia;
                posString++;
            }
        }

        return diasS;
    }
}