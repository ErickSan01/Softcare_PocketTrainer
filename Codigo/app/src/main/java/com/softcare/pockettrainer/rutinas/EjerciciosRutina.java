package com.softcare.pockettrainer.rutinas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        String dia = (String) intent.getSerializableExtra("dia");
        String parteCuerpo = (String) intent.getSerializableExtra("parteCuerpo");

        RecyclerView listaEjercicios = (RecyclerView) findViewById(R.id.listaEjercicios);

        Button btnEx = (Button) findViewById(R.id.backBtnEx);
        TextView rutinaText = findViewById(R.id.rutinaText);

        rutinaText.setText(parteCuerpo);

        EjerciciosAdapter adapterEj = new EjerciciosAdapter(this, dia);
        listaEjercicios.setHasFixedSize(false);
        listaEjercicios.setLayoutManager(new LinearLayoutManager(this));
        listaEjercicios.setAdapter(adapterEj);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        divider.setDrawable(getDrawable(R.drawable.list_divider));

        listaEjercicios.addItemDecoration(divider);


        /* Boton para regresar a la página de rutinas */
        btnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_ejercicios_rutina);
        Intent intent = getIntent();

        String dia = (String) intent.getSerializableExtra("dia");
        String parteCuerpo = (String) intent.getSerializableExtra("parteCuerpo");

        RecyclerView listaEjercicios = (RecyclerView) findViewById(R.id.listaEjercicios);

        Button btnEx = (Button) findViewById(R.id.backBtnEx);
        TextView rutinaText = findViewById(R.id.rutinaText);

        rutinaText.setText(parteCuerpo);

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}