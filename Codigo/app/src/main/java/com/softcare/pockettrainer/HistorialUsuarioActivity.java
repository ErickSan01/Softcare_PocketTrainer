package com.softcare.pockettrainer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        ArrayList<String> textoFormateado = new ArrayList<>();
        String textoFormato = "";
        if(ejercicios.size() != 0) {
            for (int i = 0; i < ejercicios.size(); i++) {
                textoFormato = fechas.get(i);
                textoFormato = textoFormato + ": " + ejercicios.get(i);
                textoFormateado.add(0, textoFormato);

            }
            ListView listView = findViewById(R.id.listViewHistorial);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, textoFormateado);
            listView.setAdapter(adapter);
        }else{
            TextView textViewHistorial = findViewById(R.id.textViewHistorial);
            textViewHistorial.setText("");
            TextView textViewAdvertencia = findViewById(R.id.textViewAdvertencia);
            textViewAdvertencia.setText("Oops!, todavía no tienes rutinas\n completadas, trabaja duro para\n comenzar tu aventura!");
        }
    }
}