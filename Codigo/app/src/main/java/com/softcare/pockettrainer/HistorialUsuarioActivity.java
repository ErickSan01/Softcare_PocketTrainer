package com.softcare.pockettrainer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class HistorialUsuarioActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_usuario);
        cargarPreferencias();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void cargarPreferencias(){
        String fechaGuardada = "1/1/2022";
        SharedPreferences preferenciasEjercicios = getSharedPreferences("ejerciciosCompletado", MODE_PRIVATE);
        SharedPreferences preferenciasFechas = getSharedPreferences("fechaEjercicio", MODE_PRIVATE);

        ArrayList<String> ejercicios = new ArrayList<>();
        ArrayList<String> fechas = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dateActual = LocalDate.now();
        String fechaActual = dateActual.format(formatter);

        LocalDate localDateGuardada = LocalDate.now();
        localDateGuardada = localDateGuardada.minusMonths(1);

        while(!fechaGuardada.equalsIgnoreCase(fechaActual)){
            fechaGuardada = localDateGuardada.format(formatter);
            boolean tieneRutinaHecha = preferenciasFechas.getBoolean(fechaGuardada, false);

            if(tieneRutinaHecha){
                String ejercicioHecho = preferenciasEjercicios.getString(fechaGuardada, "error");
                fechas.add(0, fechaGuardada);
                ejercicios.add(0, ejercicioHecho);
            }
            localDateGuardada = localDateGuardada.plusDays(1);
        }

        //Sigue meter to do dentro del list view
        //for(int i = 0)

    }
}