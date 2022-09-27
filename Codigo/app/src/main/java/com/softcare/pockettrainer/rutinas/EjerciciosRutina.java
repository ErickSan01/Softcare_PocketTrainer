package com.softcare.pockettrainer.rutinas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.R;

import java.util.ArrayList;

public class EjerciciosRutina extends AppCompatActivity {
    private RecyclerView listaEjercicios;
    private Button btnEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_rutina);

        listaEjercicios = (RecyclerView) findViewById(R.id.listaEjercicios);

        Button btnEx = (Button) findViewById(R.id.backBtnEx);

        ArrayList<String> ejerciciosPrueba  = new ArrayList<String>();
        ejerciciosPrueba.add("Sentadillas");
        ejerciciosPrueba.add("Zancadas Alternas");
        ejerciciosPrueba.add("Burpees");
        ejerciciosPrueba.add("Puente");
        ejerciciosPrueba.add("Gemelos");

        EjerciciosAdapter adapterEj = new EjerciciosAdapter(ejerciciosPrueba);

        listaEjercicios.setHasFixedSize(false);
        listaEjercicios.setLayoutManager(new LinearLayoutManager(this));
        listaEjercicios.setAdapter(adapterEj);

        btnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rutinas = new Intent(EjerciciosRutina.this, Rutinas.class);
                startActivity(rutinas);
            }
        });

    }

}