package com.softcare.pockettrainer.rutinas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;
import com.softcare.pockettrainer.R;
import com.softcare.pockettrainer.adminbasededatos.Horario;
import com.softcare.pockettrainer.adminbasededatos.HorarioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.adminbasededatos.RutinaPresentador;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramada;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramadaPresentador;

import java.util.ArrayList;
import java.util.Map;

public class Rutinas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutinas_activity);
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        SharedPreferences preferences = getSharedPreferences("dias", MODE_PRIVATE);
        SharedPreferences metaPref = getSharedPreferences("meta", MODE_PRIVATE);
        SharedPreferences fecha = getSharedPreferences("dia_semana", MODE_PRIVATE);
        SharedPreferences rutina = getSharedPreferences("rutina", MODE_PRIVATE);


        Button backButton = (Button) findViewById(R.id.backBtn);
        RecyclerView lista = (RecyclerView) findViewById(R.id.listaRutinas);

        ArrayList<String> dias = getDias(preferences);

        RutinasAdapter rutinasAdapter = new RutinasAdapter(dias, obtenerRutinaProgramada(), this);
        lista.setHasFixedSize(false);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(rutinasAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        divider.setDrawable(getDrawable(R.drawable.list_divider));

        lista.addItemDecoration(divider);

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


    public ArrayList<Rutina> obtenerRutinaProgramada(){
        RutinaProgramadaPresentador rutinaProgramadaPresentador = new RutinaProgramadaPresentador(this);
        RutinaPresentador rutinaPresentador = new RutinaPresentador(this);
        RutinaProgramada rutinaProgramada = rutinaProgramadaPresentador.obtenerRutinaProgramada().get(0);

        ArrayList<Rutina> rutinasObtenidas = rutinaPresentador.obtenerRutina();

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