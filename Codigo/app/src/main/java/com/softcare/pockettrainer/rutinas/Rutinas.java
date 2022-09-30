package com.softcare.pockettrainer.rutinas;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Rutinas extends AppCompatActivity {
    private AyudanteBaseDeDatos ayudante;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutinas_activity);
        preferences = getSharedPreferences("dias", Rutinas.MODE_PRIVATE);
        Button backButton = (Button) findViewById(R.id.backBtn);
        RecyclerView lista = (RecyclerView) findViewById(R.id.listaRutinas);

        ArrayList<String> dias = getDias();

        ayudante = new AyudanteBaseDeDatos(this);

        RutinasAdapter rutinasAdapter = new RutinasAdapter(dias, this);
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
}