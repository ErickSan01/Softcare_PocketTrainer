package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.rutinas.Rutinas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AyudanteBaseDeDatos ayudante = new AyudanteBaseDeDatos(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ayudante.agregarHorario();
        ayudante.agregarUsuario();
        ayudante.agregarRutinas();
        //ayudante.agregarEjercicios();
        crearRutina();

        Button btn2 = (Button)findViewById(R.id.btnRutinas);
        Button btn3 = (Button)findViewById(R.id.btnAjustes);
        Button btn4 = (Button)findViewById(R.id.btnNivel);
        Button btn5 = (Button)findViewById(R.id.buttonPrueba);
        Button btn6 = (Button)findViewById(R.id.buttonHistorial);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistorialUsuarioActivity.class));
            }
        });

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
                startActivity(new Intent(MainActivity.this, AjustesActivity.class));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NivelActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    public void crearRutina(){
        EjercicioPresentador ejercicioPresentador = new EjercicioPresentador(this);

        ArrayList<Ejercicio> ejercicios = ejercicioPresentador.obtenerEjercicio(this);

        for (Ejercicio ejercicio :
                ejercicios){
            System.out.println(ejercicio.getNombre());
        }

    }


}