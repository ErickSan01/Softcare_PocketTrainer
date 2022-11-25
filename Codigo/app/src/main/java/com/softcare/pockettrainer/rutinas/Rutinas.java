package com.softcare.pockettrainer.rutinas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.R;
import com.softcare.pockettrainer.adminbasededatos.Horario;
import com.softcare.pockettrainer.adminbasededatos.HorarioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.adminbasededatos.RutinaPresentador;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramada;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramadaPresentador;

import java.util.ArrayList;

public class Rutinas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutinas_activity);
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        SharedPreferences preferences = getSharedPreferences("dias", MODE_PRIVATE);

        RecyclerView lista = (RecyclerView) findViewById(R.id.listaRutinas);

        ArrayList<String> dias = getDias(preferences);

        RutinasAdapter rutinasAdapter = new RutinasAdapter(dias, obtenerRutinaProgramada(), this);
        lista.setHasFixedSize(false);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(rutinasAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        divider.setDrawable(getDrawable(R.drawable.list_divider));

        lista.addItemDecoration(divider);
    }

    /**
     * Método para obtener los días libres según las preferencias que el usuario
     * ingresó en el cuestionario inicial.
     * @return Regresa un arreglo de Strings que contienen los días libres.
     */
    public ArrayList<String> getDias(SharedPreferences diasP){
        HorarioPresentador horarioPresentador = new HorarioPresentador(this);
        Horario horario = horarioPresentador.obtenerHorario().get(0);
        ArrayList<String> dias = new ArrayList<>();

        if(horario.getLunesHorarioDisponible() != null){
            dias.add("Lunes");
        }
        if(horario.getMartesHorarioDisponible() != null){
            dias.add("Martes");
        }
        if(horario.getMiercolesHorarioDisponible() != null){
            dias.add("Miercoles");
        }
        if(horario.getJuevesHorarioDisponible() != null){
            dias.add("Jueves");
        }
        if(horario.getViernesHorarioDisponible() != null){
            dias.add("Viernes");
        }
        if(horario.getSabadoHorarioDisponible() != null){
            dias.add("Sábado");
        }
        if(horario.getDomingoHorarioDisponible() != null){
            dias.add("Domingo");
        }

        return dias;
    }

    public ArrayList<Rutina> obtenerRutinaProgramada(){
        RutinaProgramadaPresentador rutinaProgramadaPresentador = new RutinaProgramadaPresentador(this);
        RutinaPresentador rutinaPresentador = new RutinaPresentador(this);
        RutinaProgramada rutinaProgramada = rutinaProgramadaPresentador.obtenerRutinaProgramada().get(0);

        ArrayList<Integer> dias = new ArrayList<>();
        dias.add(rutinaProgramada.getId_rutina_lunes());
        dias.add(rutinaProgramada.getId_rutina_martes());
        dias.add(rutinaProgramada.getId_rutina_miercoles());
        dias.add(rutinaProgramada.getId_rutina_jueves());
        dias.add(rutinaProgramada.getId_rutina_viernes());
        dias.add(rutinaProgramada.getId_rutina_sabado());
        dias.add(rutinaProgramada.getId_rutina_domingo());

        ArrayList<Rutina> rutinas = new ArrayList<>();

        for (int idRutinaDia : dias){
            if(idRutinaDia != 0){
                Rutina rutinaAgregar = rutinaPresentador.obtenerRutina(idRutinaDia);
                rutinas.add(rutinaAgregar);
            }
        }

        return rutinas;

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
}