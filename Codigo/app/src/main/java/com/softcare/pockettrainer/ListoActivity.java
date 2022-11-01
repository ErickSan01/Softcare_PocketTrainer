package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Horario;
import com.softcare.pockettrainer.adminbasededatos.HorarioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.adminbasededatos.RutinaPresentador;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramada;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramadaPresentador;
import com.softcare.pockettrainer.adminbasededatos.Usuario;
import com.softcare.pockettrainer.adminbasededatos.UsuarioPresentador;
import com.softcare.pockettrainer.rutinas.Rutinas;

import java.util.ArrayList;
import java.util.Locale;

public class ListoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listo);

        Button buttonComenzar = (Button) findViewById(R.id.buttonComenzar);

        buttonComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rutina = new Intent(ListoActivity.this, MainActivity.class);
                startActivity(rutina);
            }
        });

        AyudanteBaseDeDatos ayudante = new AyudanteBaseDeDatos(this);

        ayudante.agregarHorario();
        ayudante.agregarUsuario();
        ayudante.agregarRutinas();
        ayudante.agregarEjercicios();
        ayudante.agregarMateriales();
        ayudante.agregarMaterialImagenes();
        ayudante.agregarEjercicioImagenes();
        crearRutina();
    }

    public void crearRutina(){
        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(this);
        Usuario usuario = usuarioPresentador.obtenerUsuario().get(0);

        String metaUsuario = usuario.getMeta();
        EjercicioPresentador ejercicioPresentador = new EjercicioPresentador(this);
        RutinaPresentador rutinaPresentador = new RutinaPresentador(this);

        ArrayList<Ejercicio> ejercicios = ejercicioPresentador.obtenerEjercicio(this);

        for (Ejercicio ejercicio : ejercicios){
            if(ejercicio.getMeta().equals(metaUsuario)){
                ArrayList<Rutina> rutinas = rutinaPresentador.obtenerRutina();
                for (Rutina rutina : rutinas){
                    if (ejercicio.getParteCuerpo().equals(rutina.getParteCuerpo().toLowerCase(Locale.ROOT))) {
                        ejercicio.setIdRutina(rutina.getIdRutina());
                        ejercicioPresentador.guardarCambios(ejercicio);
                    }
                }
            }
        }

        asignarRutinas();

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