package com.softcare.pockettrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.rutinas.Rutinas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn2, btn3, btn4, btn5;
    private AyudanteBaseDeDatos ayudante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences datos = getSharedPreferences("primer_pantalla", MODE_PRIVATE);
        boolean tieneTodosLosDatos = datos.getBoolean("tiene_datos",false);

        ayudante = new AyudanteBaseDeDatos(this);

        if(!tieneTodosLosDatos) {
            Intent i = new Intent(this, PrimerPantallaActivity.class);
            startActivity(i);
        }

        Button btn2 = (Button)findViewById(R.id.btnRutinas);
        Button btn3 = (Button)findViewById(R.id.btnAjustes);
        Button btn4 = (Button)findViewById(R.id.btnNivel);

        ayudante.agregarEjercicios();
        ayudante.agregarMateriales();
        ayudante.agregarHorario();
        ayudante.agregarEjercicioImagenes();
        ayudante.agregarMaterialImagenes();
        ayudante.agregarRutinas();
        ayudante.agregarUsuario();

        btn2.setOnClickListener(new View.OnClickListener() {
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