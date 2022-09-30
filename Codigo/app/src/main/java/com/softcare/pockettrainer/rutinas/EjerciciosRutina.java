package com.softcare.pockettrainer.rutinas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.R;

import java.util.ArrayList;
import java.util.Map;

public class EjerciciosRutina extends AppCompatActivity {
    private RecyclerView listaEjercicios;
    private Button btnEx;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_rutina);

        listaEjercicios = (RecyclerView) findViewById(R.id.listaEjercicios);
        preferences = getSharedPreferences("meta", MODE_PRIVATE);
        Map<String, ?> meta = preferences.getAll();

        Button btnEx = (Button) findViewById(R.id.backBtnEx);

        Rutinas rutinas = new Rutinas();
        ArrayList<Ejercicio> rutina = rutinas.createRutina(meta, this);

        EjerciciosAdapter adapterEj = new EjerciciosAdapter(rutina);

        listaEjercicios.setHasFixedSize(false);
        listaEjercicios.setLayoutManager(new LinearLayoutManager(this));
        listaEjercicios.setAdapter(adapterEj);

        /* Boton para regresar a la página de rutinas */
        btnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rutinas = new Intent(EjerciciosRutina.this, Rutinas.class);
                startActivity(rutinas);
            }
        });

    }

}