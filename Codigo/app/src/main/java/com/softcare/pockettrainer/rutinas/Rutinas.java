package com.softcare.pockettrainer.rutinas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;
import com.softcare.pockettrainer.MainActivity;
import com.softcare.pockettrainer.PrimerPantallaActivity;
import com.softcare.pockettrainer.R;
import com.softcare.pockettrainer.adminejercicios.EjerciciosControlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Rutinas extends AppCompatActivity {
    private AyudanteBaseDeDatos ayudante;
    private SharedPreferences preferences;
    private SharedPreferences metaPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutinas_activity);
        preferences = getSharedPreferences("dias", Rutinas.MODE_PRIVATE);
        metaPref = getSharedPreferences("meta", Rutinas.MODE_PRIVATE);
        Button backButton = (Button) findViewById(R.id.backBtn);
        RecyclerView lista = (RecyclerView) findViewById(R.id.listaRutinas);
        Button mnBtn = (Button) findViewById(R.id.menuBtn);

        ArrayList<String> dias = getDias();

        ayudante = new AyudanteBaseDeDatos(this);
        Map<String, ?> meta = metaPref.getAll();

        RutinasAdapter rutinasAdapter = new RutinasAdapter(dias, this, createRutina(meta, this));
        lista.setHasFixedSize(false);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(rutinasAdapter);

        /* Boton para regresar a la página principal */
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent mainPage = new Intent(Rutinas.this, MainActivity.class);
                startActivity(mainPage);
            }
        });

        mnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRutina(meta, Rutinas.this);
            }
        });
    }

    /**
     * Método para obtener los días libres según las preferencias que el usuario
     * ingresó en el cuestionario inicial.
     * @return Regresa un arreglo de Strings que contienen los días libres.
     */
    public ArrayList<String> getDias(){
        Map<String, ?> dias = preferences.getAll();

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

    public String leerArchivo(){
        BufferedReader reader = null;
        String result = "";

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("pruebaTxt.txt")));
            String line = "";

            while((line = reader.readLine()) != null){
                result += line;
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        return result;
    }

    public ArrayList<Ejercicio> createRutina(Map<String, ?> metaM, Context context){
        String meta = obtenerMeta(metaM);

        EjerciciosControlador controller = new EjerciciosControlador(context);

        ArrayList<Ejercicio> ejercicios = controller.obtenerEjercicio(context);
        ArrayList<Ejercicio> ejerciciosRutina = new ArrayList<Ejercicio>();


        switch(meta){
            case("musculo"):
                ArrayList<String> partesCuerpoM = new ArrayList<>();
                partesCuerpoM.add("Brazo");
                partesCuerpoM.add("Hombros");
                partesCuerpoM.add("Pierna");

                int randomM = (int) ((Math.random() * (4)) + 0);

                String parteCuerpoM = partesCuerpoM.get(randomM);

                for (Ejercicio ejA : ejercicios){
                    if(ejA.getCategoria().toLowerCase().equals(meta.toLowerCase())){
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

                int randomP = (int) ((Math.random() * (3)) + 0);

                String parteCuerpoP = partesCuerpoP.get(randomP);

                for (Ejercicio ejA : ejercicios){
                    if(ejA.getCategoria().toLowerCase().equals(meta.toLowerCase())){
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

                int randomA = (int) ((Math.random() * (5)) + 0);

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
        return null;
    }

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