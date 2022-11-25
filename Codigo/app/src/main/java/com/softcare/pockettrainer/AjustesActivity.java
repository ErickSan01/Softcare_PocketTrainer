package com.softcare.pockettrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.softcare.pockettrainer.adminbasededatos.Horario;
import com.softcare.pockettrainer.adminbasededatos.HorarioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Usuario;
import com.softcare.pockettrainer.adminbasededatos.UsuarioPresentador;

import java.util.ArrayList;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        //Botón de retroceder
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        cargarPreferenciasHorario();
        cargarPreferenciasCuerpo();
        cargarPreferenciasMeta();

        TextView texto = findViewById(R.id.textViewHorario);
        texto.setText("Mi Horario:");
        /*Button backBtn = (Button) findViewById(R.id.backBtnAjustes);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
    }

    @SuppressLint("SetTextI18n")
    private void cargarPreferenciasHorario(){
        ListView lista = findViewById(R.id.ListView1);

        ArrayList<String> dias = new ArrayList<>();
        int conteo = 0;

        SharedPreferences preferenciasD = getSharedPreferences("dias", MODE_PRIVATE);
        SharedPreferences preferenciasT = getSharedPreferences("tiempo", MODE_PRIVATE);

        String hora;

        HorarioPresentador horarioPresentador = new HorarioPresentador(this);
        Horario horario = horarioPresentador.obtenerHorario().get(0);

        boolean lunesB = !(horario.getLunesHorarioDisponible() == null);
        boolean martesB = !(horario.getMartesHorarioDisponible() == null);
        boolean miercolesB = !(horario.getMiercolesHorarioDisponible() == null);
        boolean juevesB = !(horario.getJuevesHorarioDisponible() == null);
        boolean viernesB = !(horario.getViernesHorarioDisponible() == null);
        boolean sabadoB = !(horario.getSabadoHorarioDisponible() == null);
        boolean domingoB = !(horario.getDomingoHorarioDisponible() == null);

        boolean lunes = preferenciasD.getBoolean("lunes",false);
        boolean martes = preferenciasD.getBoolean("martes",false);
        boolean miercoles = preferenciasD.getBoolean("miercoles",false);
        boolean jueves = preferenciasD.getBoolean("jueves",false);
        boolean viernes = preferenciasD.getBoolean("viernes",false);
        boolean sabado = preferenciasD.getBoolean("sabado",false);
        boolean domingo = preferenciasD.getBoolean("domingo",false);

        if(lunesB){
            hora = preferenciasT.getString("lunes","");
            dias.add("Lunes: "+ hora);
            conteo++;
        }
        if(martesB){
            hora = preferenciasT.getString("martes","");
            dias.add("Martes: "+ hora);
            conteo++;
        }
        if(miercolesB){
            hora = preferenciasT.getString("miercoles","");
            dias.add("Miercoles: "+ hora);
            conteo++;
        }
        if(juevesB){
            hora = preferenciasT.getString("jueves","");
            dias.add("Jueves: "+ hora);
            conteo++;
        }
        if(viernesB){
            hora = preferenciasT.getString("viernes","");
            dias.add("Viernes: "+ hora);
            conteo++;
        }
        if(sabadoB){
            hora = preferenciasT.getString("sabado","");
            dias.add("Sabado: "+ hora);
            conteo++;
        }
        if(domingoB){
            hora = preferenciasT.getString("domingo","");
            dias.add("Domingo: "+ hora);
            conteo++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dias);
        lista.setAdapter(adapter);

    }

    private void cargarPreferenciasCuerpo(){
        TextView textoBase = findViewById(R.id.textViewCuerpoUsuario);
        SharedPreferences preferenciasC = getSharedPreferences("cuerpo", MODE_PRIVATE);

        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(this);
        Usuario usuario = usuarioPresentador.obtenerUsuario().get(0);
        String tipoCuerpo = "";

        switch(usuario.getTipoCuerpo()){
            case("delgado"):
                tipoCuerpo = "Delgado";
                break;
            case("robusto"):
                tipoCuerpo = "Robusto";
                break;
            case("fornido"):
                tipoCuerpo = "Fornido";
                break;
        }

        textoBase.setText(tipoCuerpo);
    }

    private void cargarPreferenciasMeta(){
        TextView textoMeta = findViewById(R.id.textViewMetaUsuario);
        SharedPreferences preferenciasM = getSharedPreferences("meta", MODE_PRIVATE);

        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(this);
        Usuario usuario = usuarioPresentador.obtenerUsuario().get(0);

        String meta = "";

        switch(usuario.getMeta()){
            case("bajarPeso"):
                meta = "Perder Peso";
                break;
            case("aumentarMasa"):
                meta = "Ganar musculo";
                break;
            case("ambos"):
                meta = "Perder peso y Ganar músculo";
                break;
        }

        textoMeta.setText(meta);
    }

    public void editar(View view){
        Intent i = new Intent(this, DiasLibresCuestionario.class);
        i.putExtra("origen", 2);
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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        cargarPreferenciasCuerpo();
        cargarPreferenciasHorario();
        cargarPreferenciasMeta();
        super.onRestart();
    }
}