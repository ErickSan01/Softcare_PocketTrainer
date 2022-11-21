package com.softcare.pockettrainer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softcare.pockettrainer.adminbasededatos.Horario;
import com.softcare.pockettrainer.adminbasededatos.HorarioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.adminbasededatos.RutinaPresentador;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramada;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramadaPresentador;
import com.softcare.pockettrainer.rutinas.Rutinas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn2 = (Button)findViewById(R.id.btnRutinas);
        Button btn3 = (Button)findViewById(R.id.btnAjustes);
        Button btn4 = (Button)findViewById(R.id.btnNivel);
        Button btn5 = (Button)findViewById(R.id.btnAgenda);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistorialCalendarioActivity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Rutinas.class));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AjustesActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, NivelActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = getIntent();
        String ajustes = intent.getStringExtra("pantalla");

        System.out.println(ajustes);


    }

    @Override
    public void onBackPressed(){
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void asignarRutinas(){
        RutinaPresentador rutinaPresentador = new RutinaPresentador(this);
        RutinaProgramadaPresentador rutinaProgramadaPresentador = new RutinaProgramadaPresentador(this);
        HorarioPresentador horarioPresentador = new HorarioPresentador(this);
        RutinaProgramada rutinaProgramada;

        ArrayList<Rutina> rutinas = rutinaPresentador.obtenerRutina();
        Horario horario = horarioPresentador.obtenerHorario().get(0);
        int idRutinaLunes = 0;
        int idRutinaMartes = 0;
        int idRutinaMiercoles = 0;
        int idRutinaJueves = 0;
        int idRutinaViernes = 0;
        int idRutinaSabado = 0;
        int idRutinaDomingo = 0;

        int rutina = 0;

        if(horario.getLunesHorarioDisponible() != null){
            idRutinaLunes = rutinas.get(rutina).getIdRutina();
            rutina++;
        }
        if(horario.getMartesHorarioDisponible() != null){
            if(rutina < rutinas.size()){
                idRutinaMartes = rutinas.get(rutina).getIdRutina();
            } else {
                rutina = 0;
                idRutinaMartes = rutinas.get(rutina).getIdRutina();
            }
            rutina ++;
        }
        if(horario.getMiercolesHorarioDisponible() != null){
            if(rutina < rutinas.size()){
                idRutinaMiercoles = rutinas.get(rutina).getIdRutina();
            } else {
                rutina = 0;
                idRutinaMiercoles = rutinas.get(rutina).getIdRutina();
            }
            rutina ++;
        }
        if(horario.getJuevesHorarioDisponible() != null){
            if(rutina < rutinas.size()){
                idRutinaJueves = rutinas.get(rutina).getIdRutina();
            } else {
                rutina = 0;
                idRutinaJueves = rutinas.get(rutina).getIdRutina();
            }
            rutina ++;
        }
        if(horario.getViernesHorarioDisponible() != null){
            if(rutina < rutinas.size()){
                idRutinaViernes = rutinas.get(rutina).getIdRutina();
            } else {
                rutina = 0;
                idRutinaViernes = rutinas.get(rutina).getIdRutina();
            }
            rutina ++;
        }
        if(horario.getSabadoHorarioDisponible() != null){
            if(rutina < rutinas.size()){
                idRutinaSabado = rutinas.get(rutina).getIdRutina();
            } else {
                rutina = 0;
                idRutinaSabado = rutinas.get(rutina).getIdRutina();
            }
            rutina ++;
        }
        if(horario.getDomingoHorarioDisponible() != null){
            if (rutina >= rutinas.size()) {
                rutina = 0;
            }
            idRutinaDomingo = rutinas.get(rutina).getIdRutina();
        }

        rutinaProgramada = new RutinaProgramada(1, idRutinaLunes, idRutinaMartes, idRutinaMiercoles, idRutinaJueves, idRutinaViernes, idRutinaSabado, idRutinaDomingo);

        rutinaProgramadaPresentador.nuevaRutinaProgramada(rutinaProgramada);
    }

}