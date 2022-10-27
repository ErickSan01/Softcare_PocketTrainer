package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        cargarPreferenciasHorario();
        cargarPreferenciasCuerpo();
        cargarPreferenciasMeta();

        Button backBtn = (Button) findViewById(R.id.backBtnAjustes);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button salir = (Button) findViewById(R.id.buttonSalir);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AjustesActivity.this, MainActivity.class));
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void cargarPreferenciasHorario(){
        ListView lista = findViewById(R.id.ListView1);
        TextView texto = findViewById(R.id.textViewHorario);

        ArrayList<String> dias = new ArrayList<>();
        int conteo = 0;

        SharedPreferences preferenciasD = getSharedPreferences("dias", MODE_PRIVATE);
        SharedPreferences preferenciasT = getSharedPreferences("tiempo", MODE_PRIVATE);

        String hora;

        boolean lunes = preferenciasD.getBoolean("lunes",false);
        boolean martes = preferenciasD.getBoolean("martes",false);
        boolean miercoles = preferenciasD.getBoolean("miercoles",false);
        boolean jueves = preferenciasD.getBoolean("jueves",false);
        boolean viernes = preferenciasD.getBoolean("viernes",false);
        boolean sabado = preferenciasD.getBoolean("sabado",false);
        boolean domingo = preferenciasD.getBoolean("domingo",false);

        if(lunes){
            hora = preferenciasT.getString("lunes","");
            dias.add("Lunes: "+ hora);
            conteo++;
        }
        if(martes){
            hora = preferenciasT.getString("martes","");
            dias.add("Martes: "+ hora);
            conteo++;
        }
        if(miercoles){
            hora = preferenciasT.getString("miercoles","");
            dias.add("Miercoles: "+ hora);
            conteo++;
        }
        if(jueves){
            hora = preferenciasT.getString("jueves","");
            dias.add("Jueves: "+ hora);
            conteo++;
        }
        if(viernes){
            hora = preferenciasT.getString("viernes","");
            dias.add("Viernes: "+ hora);
            conteo++;
        }
        if(sabado){
            hora = preferenciasT.getString("sabado","");
            dias.add("Sabado: "+ hora);
            conteo++;
        }
        if(domingo){
            hora = preferenciasT.getString("domingo","");
            dias.add("Domingo: "+ hora);
            conteo++;
        }

        String temporal = texto.getText().toString();
        texto.setText(temporal+" ("+conteo+" días a la semana) ");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dias);
        lista.setAdapter(adapter);

    }

    private void cargarPreferenciasCuerpo(){
        TextView textoBase = findViewById(R.id.textViewCuerpoUsuario);
        SharedPreferences preferenciasC = getSharedPreferences("cuerpo", MODE_PRIVATE);

        boolean delgado = preferenciasC.getBoolean("delgado",false);
        boolean robusto = preferenciasC.getBoolean("robusto",false);
        boolean fornido = preferenciasC.getBoolean("fornido",false);

        if(delgado){
            textoBase.setText("Delgado");
        }
        if(robusto){
            textoBase.setText("Robusto");
        }
        if(fornido){
            textoBase.setText("Fornido");
        }
    }

    private void cargarPreferenciasMeta(){
        TextView textoMeta = findViewById(R.id.textViewMetaUsuario);
        SharedPreferences preferenciasM = getSharedPreferences("meta", MODE_PRIVATE);

        boolean delgado = preferenciasM.getBoolean("peso",false);
        boolean robusto = preferenciasM.getBoolean("musculo",false);
        boolean fornido = preferenciasM.getBoolean("ambos",false);

        if(delgado){
            textoMeta.setText("Perder peso");
        }
        if(robusto){
            textoMeta.setText("Ganar musculo");
        }
        if(fornido){
            textoMeta.setText("Perder peso y Ganar músculo");
        }
    }

    public void editar(View view){
        Intent i = new Intent(this, AjustesHorarioActivity.class);
        startActivity(i);
    }

    public void editarCuerpo(View view){
        Intent i = new Intent(this, AjustesCuerpoActivity.class);
        startActivity(i);
    }

    public void editarMeta(View view){
        Intent i = new Intent(this, AjustesMetaActivity.class);
        startActivity(i);
    }

    public void reset(View v){
        SharedPreferences prefD = getSharedPreferences("dias", MODE_PRIVATE);
        SharedPreferences prefT = getSharedPreferences("tiempo", MODE_PRIVATE);
        SharedPreferences prefC = getSharedPreferences("cuerpo", MODE_PRIVATE);
        SharedPreferences prefM = getSharedPreferences("meta", MODE_PRIVATE);
        SharedPreferences datos = getSharedPreferences("primer_pantalla", MODE_PRIVATE);


        SharedPreferences.Editor editorD = prefD.edit();
        editorD.putBoolean("lunes",false);
        editorD.putBoolean("martes",false);
        editorD.putBoolean("miercoles",false);
        editorD.putBoolean("jueves",false);
        editorD.putBoolean("viernes",false);
        editorD.putBoolean("sabado",false);
        editorD.putBoolean("domingo",false);
        editorD.apply();

        SharedPreferences.Editor editorT = prefT.edit();
        editorT.putString("lunes","");
        editorT.putString("martes","");
        editorT.putString("miercoles","");
        editorT.putString("jueves","");
        editorT.putString("viernes","");
        editorT.putString("sabado","");
        editorT.putString("domingo","");
        editorT.apply();

        SharedPreferences.Editor editorC = prefC.edit();
        editorC.putBoolean("delgado",false);
        editorC.putBoolean("robusto",false);
        editorC.putBoolean("fornido",false);
        editorC.apply();

        SharedPreferences.Editor editorM = prefM.edit();
        editorM.putBoolean("peso",false);
        editorM.putBoolean("musculo",false);
        editorM.putBoolean("ambos",false);
        editorM.apply();

        SharedPreferences.Editor D = datos.edit();
        D.putBoolean("tiene_datos",false);
        D.apply();

        finish();
    }

}