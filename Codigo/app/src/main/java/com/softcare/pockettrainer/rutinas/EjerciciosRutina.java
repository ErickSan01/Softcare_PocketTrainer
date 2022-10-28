package com.softcare.pockettrainer.rutinas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.R;
import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramada;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramadaPresentador;

import java.util.ArrayList;
import java.util.Map;

public class EjerciciosRutina extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_rutina);
        Intent intent = getIntent();

        String dia = (String) intent.getSerializableExtra("dia");
        String parteCuerpo = (String) intent.getSerializableExtra("parteCuerpo");

        RecyclerView listaEjercicios = (RecyclerView) findViewById(R.id.listaEjercicios);
        SharedPreferences preferences = getSharedPreferences("meta", MODE_PRIVATE);
        SharedPreferences dias = getSharedPreferences("dias", MODE_PRIVATE);
        SharedPreferences tieneRutina = getSharedPreferences("tiene_rutina", MODE_PRIVATE);
        Map<String, ?> meta = preferences.getAll();

        Button btnEx = (Button) findViewById(R.id.backBtnEx);
        TextView rutinaText = findViewById(R.id.rutinaText);

        rutinaText.setText(parteCuerpo);

        Rutinas rutinas = new Rutinas();

        EjerciciosAdapter adapterEj = new EjerciciosAdapter(this, dia);

        listaEjercicios.setHasFixedSize(false);
        listaEjercicios.setLayoutManager(new LinearLayoutManager(this));
        listaEjercicios.setAdapter(adapterEj);

        /* Boton para regresar a la página de rutinas */
        btnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}