package com.softcare.pockettrainer.rutinas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;
import com.softcare.pockettrainer.R;
import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.RutinaPresentador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class Rutinas extends AppCompatActivity {
    private AyudanteBaseDeDatos ayudante;
    private SharedPreferences preferences;
    private SharedPreferences metaPref;
    private SharedPreferences fecha;
    private SharedPreferences rutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutinas_activity);
        preferences = getSharedPreferences("dias", MODE_PRIVATE);
        metaPref = getSharedPreferences("meta", MODE_PRIVATE);
        fecha = getSharedPreferences("dia_semana", MODE_PRIVATE);
        rutina = getSharedPreferences("rutina", MODE_PRIVATE);


        Button backButton = (Button) findViewById(R.id.backBtn);
        RecyclerView lista = (RecyclerView) findViewById(R.id.listaRutinas);

        ArrayList<String> dias = getDias(preferences);

        ayudante = new AyudanteBaseDeDatos(this);
        Map<String, ?> meta = metaPref.getAll();
        RutinaPresentador rutinaPresentador = new RutinaPresentador(this);

        RutinasAdapter rutinasAdapter = new RutinasAdapter(dias, rutinaPresentador.obtenerRutina(), this);
        lista.setHasFixedSize(false);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(rutinasAdapter);

        /* Boton para regresar a la página principal */
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * Método para obtener los días libres según las preferencias que el usuario
     * ingresó en el cuestionario inicial.
     * @return Regresa un arreglo de Strings que contienen los días libres.
     */
    public ArrayList<String> getDias(SharedPreferences diasP){
        Map<String, ?> dias = diasP.getAll();

        ArrayList<String> diasArr = new ArrayList<String>();
        ArrayList<Boolean> valsArr = new ArrayList<Boolean>();

        for (String day: dias.keySet()) {
            diasArr.add(day);
        }
        for (Object val: dias.values()) {
            valsArr.add((Boolean) val);
        }

        ArrayList<String> diasS = new ArrayList<String>();

        for(int pos = 0; pos < diasArr.size(); pos++){
            if(valsArr.get(pos)){
                String dia = diasArr.get(pos);
                dia = dia.substring(0,1).toUpperCase() + dia.substring(1);
                diasS.add(dia);
            }
        }

        return diasS;
    }

    /*
    public ArrayList<Ejercicio> createRutina(Map<String, ?> metaM, Context context, String dia, SharedPreferences diasP, SharedPreferences tieneRutinaP){
        String meta = obtenerMeta(metaM);

        EjercicioPresentador controller = new EjercicioPresentador(context);

        ArrayList<Ejercicio> ejercicios = controller.obtenerEjercicio(context);
        ArrayList<Ejercicio> ejerciciosRutina = new ArrayList<Ejercicio>();
        ArrayList<String> dias = getDias(diasP);

        Boolean tieneRutina = tieneRutinaP.getBoolean("tiene_rutina", false);



        if(!tieneRutina){
            switch(meta){
                case("musculo"):
                    ArrayList<String> partesCuerpoM = new ArrayList<>();
                    partesCuerpoM.add("Brazo");
                    partesCuerpoM.add("Hombros");
                    partesCuerpoM.add("Pierna");

                    int randomM = (int) ((Math.random() * (2)) + 0);

                    String parteCuerpoM = partesCuerpoM.get(randomM);

                    for (Ejercicio ejA : ejercicios){
                        if(ejA.getCategoria().toLowerCase().equals("aumentarmasa")){
                            if(ejA.getParteCuerpo().toLowerCase().equals(parteCuerpoM.toLowerCase())){
                                ejerciciosRutina.add(ejA);
                            }
                        }
                    }
                    return ejerciciosRutina;
                case("peso"):
                    ArrayList<String> partesCuerpoP = new ArrayList<>();
                    partesCuerpoP.add("Pecho");
                    partesCuerpoP.add("Pierna");

                    int randomP = (int) ((Math.random() * (1)) + 0);

                    String parteCuerpoP = partesCuerpoP.get(randomP);

                    for (Ejercicio ejA : ejercicios){
                        if(ejA.getCategoria().toLowerCase().equals("bajarpeso")){
                            if(ejA.getParteCuerpo().toLowerCase().equals(parteCuerpoP.toLowerCase())){
                                ejerciciosRutina.add(ejA);
                            }
                        }
                    }
                    return ejerciciosRutina;
                case("ambos"):
                    ArrayList<String> partesCuerpoA = new ArrayList<>();
                    partesCuerpoA.add("Abdomen");
                    partesCuerpoA.add("Brazo");
                    partesCuerpoA.add("Hombros");
                    partesCuerpoA.add("Pecho");
                    partesCuerpoA.add("Pierna");

                    int randomA = (int) ((Math.random() * (4)) + 0);

                    String parteCuerpo = partesCuerpoA.get(randomA);

                    for (Ejercicio ejA : ejercicios){
                        if(ejA.getCategoria().toLowerCase().equals(meta.toLowerCase())){
                            if(ejA.getParteCuerpo().toLowerCase().equals(parteCuerpo.toLowerCase())){
                                ejerciciosRutina.add(ejA);
                            }
                        }
                    }
                    return ejerciciosRutina;
            }
        }

        return null;
    }
     */

    public String obtenerMeta(Map<String, ?> meta){
        ArrayList<String> metaArr = new ArrayList<String>();
        ArrayList<Boolean> valsArr = new ArrayList<Boolean>();

        for (String metaS: meta.keySet()) {
            metaArr.add(metaS);
        }
        for (Object val: meta.values()) {
            valsArr.add((Boolean) val);
        }

        String m = "";

        for (int pos = 0; pos < 3; pos++) {
            if(valsArr.get(pos)){
                m = metaArr.get(pos);
            }
        }

        return m;
    }
}