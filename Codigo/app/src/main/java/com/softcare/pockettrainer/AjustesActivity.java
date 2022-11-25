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

import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenes;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenesPresentador;
import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Horario;
import com.softcare.pockettrainer.adminbasededatos.HorarioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Material;
import com.softcare.pockettrainer.adminbasededatos.MaterialImagenes;
import com.softcare.pockettrainer.adminbasededatos.MaterialImagenesPresentador;
import com.softcare.pockettrainer.adminbasededatos.MaterialesPresentador;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.adminbasededatos.RutinaPresentador;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramada;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramadaPresentador;
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

        TextView texto = findViewById(R.id.textViewHorario);
        texto.setText("Mi Horario:");
        /*Button backBtn = (Button) findViewById(R.id.backBtnAjustes);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        Button btnReset = findViewById(R.id.buttonReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(view);
            }
        });
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
        SharedPreferences prefC = getSharedPreferences("cuerpo", MODE_PRIVATE);
        SharedPreferences prefM = getSharedPreferences("meta", MODE_PRIVATE);
        SharedPreferences datos = getSharedPreferences("primer_pantalla", MODE_PRIVATE);

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

        RutinaPresentador rutinaPresentador = new RutinaPresentador(this);

        ArrayList<Rutina> rutinas = rutinaPresentador.obtenerRutina();

        for (Rutina rutina :
                rutinas) {
            rutinaPresentador.eliminarRutina(rutina);
        }

        RutinaProgramadaPresentador rutinaProgramadaPresentador = new RutinaProgramadaPresentador(this);
        RutinaProgramada rutinaProgramada = rutinaProgramadaPresentador.obtenerRutinaProgramada().get(0);

        rutinaProgramadaPresentador.eliminarRutinaProgramada(rutinaProgramada);

        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(this);
        Usuario usuario = usuarioPresentador.obtenerUsuario().get(0);

        usuarioPresentador.eliminarUsuario(usuario);

        EjercicioImagenesPresentador ejercicioImagenesPresentador = new EjercicioImagenesPresentador(this);
        EjercicioPresentador ejercicioPresentador = new EjercicioPresentador(this);

        ArrayList<EjercicioImagenes> ejercicioImagenes = ejercicioImagenesPresentador.obtenerImagen();

        for (EjercicioImagenes ejercicioImagen : ejercicioImagenes){
            ejercicioImagenesPresentador.eliminarImagen(ejercicioImagen);
        }

        ArrayList<Ejercicio> ejercicios = ejercicioPresentador.obtenerEjercicio(this);
        for (Ejercicio ejercicio :
                ejercicios) {
            ejercicioPresentador.eliminarEjercicio(ejercicio);
        }

        MaterialImagenesPresentador materialImagenesPresentador = new MaterialImagenesPresentador(this);
        MaterialesPresentador materialesPresentador = new MaterialesPresentador(this);

        ArrayList<MaterialImagenes> materialImagenes = materialImagenesPresentador.obtenerImagen();

        for (MaterialImagenes materialImagen : materialImagenes){
            materialImagenesPresentador.eliminarImagen(materialImagen);
        }

        ArrayList<Material> materiales = materialesPresentador.obtenerMaterial();
        for (Material material :
                materiales) {
            materialesPresentador.eliminarMaterial(material);
        }

        Intent i = new Intent(this, CuerpoActivity.class);
        startActivity(i);
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
        cargarPreferenciasHorario();
        super.onRestart();
    }
}