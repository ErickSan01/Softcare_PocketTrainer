package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.Locale;

public class DiasLibresCuestionario extends AppCompatActivity {

    int minuto;
    int hora;

    EditText textLunes;
    EditText textMartes;
    EditText textMiercoles;
    EditText textJueves;
    EditText textViernes;
    EditText textSabado;
    EditText textDomingo;

    CheckBox checkLunes;
    CheckBox checkMartes;
    CheckBox checkMiercoles;
    CheckBox checkJueves;
    CheckBox checkViernes;
    CheckBox checkSabado;
    CheckBox checkDomingo;

    int anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_libres_cuestionario);

        Intent intent = getIntent();

        anterior = intent.getIntExtra("origen", 0);

        Button botonElegirLunes = findViewById(R.id.buttonLunes);
        Button botonElegirMartes = findViewById(R.id.buttonMartes);
        Button botonElegirMiercoles = findViewById(R.id.buttonMiercoles);
        Button botonElegirJueves = findViewById(R.id.buttonJueves);
        Button botonElegirViernes = findViewById(R.id.buttonViernes);
        Button botonElegirSabado = findViewById(R.id.buttonSabado);
        Button botonElegirDomingo = findViewById(R.id.buttonDomingo);

        Button botonSiguiente = findViewById(R.id.siguiente);

        botonElegirLunes.setEnabled(false);
        botonElegirMartes.setEnabled(false);
        botonElegirMiercoles.setEnabled(false);
        botonElegirJueves.setEnabled(false);
        botonElegirViernes.setEnabled(false);
        botonElegirSabado.setEnabled(false);
        botonElegirDomingo.setEnabled(false);

        checkLunes = findViewById(R.id.checkBoxLunes);
        checkMartes = findViewById(R.id.checkBoxMartes);
        checkMiercoles = findViewById(R.id.checkBoxMiercoles);
        checkJueves = findViewById(R.id.checkBoxJueves);
        checkViernes = findViewById(R.id.checkBoxViernes);
        checkSabado = findViewById(R.id.checkBoxSabado);
        checkDomingo = findViewById(R.id.checkBoxDomingo);

        textLunes = findViewById(R.id.editTextTimeLunes);
        textMartes = findViewById(R.id.editTextTimeMartes);
        textMiercoles = findViewById(R.id.editTextTimeMiercoles);
        textJueves = findViewById(R.id.editTextTimeJueves);
        textViernes = findViewById(R.id.editTextTimeViernes);
        textSabado = findViewById(R.id.editTextTimeSabado);
        textDomingo = findViewById(R.id.editTextTimeDomingo);

        //Encuentra cada check box y los marca
        checkLunes.setChecked(false);
        checkMartes.setChecked(false);
        checkMiercoles.setChecked(false);
        checkJueves.setChecked(false);
        checkViernes.setChecked(false);
        checkSabado.setChecked(false);
        checkDomingo.setChecked(false);

        if(anterior == 2){
            SharedPreferences pDias = getSharedPreferences("dias", MODE_PRIVATE);
            SharedPreferences pTiempo = getSharedPreferences("tiempo", MODE_PRIVATE);

            checkLunes.setChecked(pDias.getBoolean("lunes", false));
            checkMartes.setChecked(pDias.getBoolean("martes", false));
            checkMiercoles.setChecked(pDias.getBoolean("miercoles", false));
            checkJueves.setChecked(pDias.getBoolean("jueves", false));
            checkViernes.setChecked(pDias.getBoolean("viernes", false));
            checkSabado.setChecked(pDias.getBoolean("sabado", false));
            checkDomingo.setChecked(pDias.getBoolean("domingo", false));

            botonElegirLunes.setEnabled(pDias.getBoolean("lunes", false));
            botonElegirMartes.setEnabled(pDias.getBoolean("martes", false));
            botonElegirMiercoles.setEnabled(pDias.getBoolean("miercoles", false));
            botonElegirJueves.setEnabled(pDias.getBoolean("jueves", false));
            botonElegirViernes.setEnabled(pDias.getBoolean("viernes", false));
            botonElegirSabado.setEnabled(pDias.getBoolean("sabado", false));
            botonElegirDomingo.setEnabled(pDias.getBoolean("domingo", false));

            textLunes.setText(pTiempo.getString("lunes", ""));
            textMartes.setText(pTiempo.getString("martes", ""));
            textMiercoles.setText(pTiempo.getString("miercoles", ""));
            textJueves.setText(pTiempo.getString("jueves", ""));
            textViernes.setText(pTiempo.getString("viernes", ""));
            textSabado.setText(pTiempo.getString("sabado", ""));
            textDomingo.setText(pTiempo.getString("domingo", ""));
        }


        checkLunes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!botonElegirLunes.isEnabled()){
                    textLunes.setText("");
                    guardar("lunes", true);
                    botonElegirLunes.setEnabled(true);
                } else {
                    textLunes.setText("");
                    guardar("lunes", false);
                    botonElegirLunes.setEnabled(false);
                }
            }
        });

        checkMartes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!botonElegirMartes.isEnabled()){
                    textMartes.setText("");
                    guardar("martes", true);
                    botonElegirMartes.setEnabled(true);
                } else {
                    textMartes.setText("");
                    guardar("martes", false);
                    botonElegirMartes.setEnabled(false);
                }
            }
        });

        checkMiercoles.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!botonElegirMiercoles.isEnabled()){
                    textMiercoles.setText("");
                    guardar("miercoles", true);
                    botonElegirMiercoles.setEnabled(true);
                } else {
                    textMiercoles.setText("");
                    guardar("miercoles", false);
                    botonElegirMiercoles.setEnabled(false);
                }
            }
        });

        checkJueves.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!botonElegirJueves.isEnabled()){
                    textJueves.setText("");
                    guardar("jueves", true);
                    botonElegirJueves.setEnabled(true);
                } else {
                    textJueves.setText("");
                    guardar("jueves", false);
                    botonElegirJueves.setEnabled(false);
                }
            }
        });

        checkViernes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!botonElegirViernes.isEnabled()){
                    textLunes.setText("");
                    guardar("viernes", true);
                    botonElegirViernes.setEnabled(true);
                } else {
                    textViernes.setText("");
                    guardar("viernes", false);
                    botonElegirViernes.setEnabled(false);
                }
            }
        });

        checkSabado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!botonElegirSabado.isEnabled()){
                    textSabado.setText("");
                    guardar("sabado", true);
                    botonElegirSabado.setEnabled(true);
                } else {
                    textSabado.setText("");
                    guardar("sabado", false);
                    botonElegirSabado.setEnabled(false);
                }
            }
        });

        checkDomingo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!botonElegirDomingo.isEnabled()){
                    textDomingo.setText("");
                    guardar("domingo", true);
                    botonElegirDomingo.setEnabled(true);
                } else {
                    textDomingo.setText("");
                    guardar("domingo", false);
                    botonElegirDomingo.setEnabled(false);
                }
            }
        });

        botonElegirLunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTimePicker(view);
            }
        });

        botonElegirMartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTimePicker(view);
            }
        });

        botonElegirMiercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTimePicker(view);
            }
        });

        botonElegirJueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTimePicker(view);
            }
        });

        botonElegirViernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTimePicker(view);
            }
        });

        botonElegirSabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTimePicker(view);
            }
        });

        botonElegirDomingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTimePicker(view);
            }
        });

        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(anterior == 1){
                    siguiente();
                }
                if(anterior == 2){
                    if(verificarCampos()){
                        guardar("lunes", textLunes.getText().toString());
                        guardar("martes", textMartes.getText().toString());
                        guardar("miercoles", textMiercoles.getText().toString());
                        guardar("jueves", textJueves.getText().toString());
                        guardar("viernes", textViernes.getText().toString());
                        guardar("sabado", textSabado.getText().toString());
                        guardar("domingo", textDomingo.getText().toString());

                        AyudanteBaseDeDatos ayudanteBaseDeDatos = new AyudanteBaseDeDatos(DiasLibresCuestionario.this);

                        HorarioPresentador horarioPresentador = new HorarioPresentador(DiasLibresCuestionario.this);
                        Horario horarioViejo = horarioPresentador.obtenerHorario().get(0);

                        horarioPresentador.eliminarHorario(horarioViejo);

                        ayudanteBaseDeDatos.agregarHorario();

                        RutinaPresentador rutinaPresentador = new RutinaPresentador(DiasLibresCuestionario.this);
                        ArrayList<Rutina> rutinas = rutinaPresentador.obtenerRutina();

                        for (Rutina rutina :
                                rutinas) {
                            rutinaPresentador.eliminarRutina(rutina);
                        }

                        ayudanteBaseDeDatos.agregarRutinas();

                        crearRutina();

                        finish();
                    }else{
                        Toast.makeText(DiasLibresCuestionario.this, "Selecciona al menos una hora libre por día para ejercitarte", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void guardar(String campo, boolean valor){
        SharedPreferences preferencias = getSharedPreferences("dias", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean(campo,valor);

        editor.apply();
    }

    public void guardar(String campo, String valor){
        SharedPreferences preferencias = getSharedPreferences("tiempo", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(campo, valor);
        editor.apply();
    }

    public void mostrarTimePicker(View v){
        TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hora_seleccionada, int min_seleccionado) {
                hora = hora_seleccionada;
                minuto = min_seleccionado;
                if(v.getId() == R.id.buttonLunes){
                    textLunes.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonMartes){
                    textMartes.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonMiercoles){
                    textMiercoles.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonJueves){
                    textJueves.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonViernes){
                    textViernes.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonSabado){
                    textSabado.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonDomingo){
                    textDomingo.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
            }
        };
        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;

        TimePickerDialog time_picker = new TimePickerDialog(this, style, time_listener, hora, minuto, false);
        time_picker.setTitle("Selecciona la hora:");
        time_picker.show();
    }
    private boolean verificarCampos(){
        ArrayList<Boolean> resultado = new ArrayList<>();

        if(checkLunes.isChecked()){
            if(!textLunes.getText().toString().equals("")){
                resultado.add(true);
            }else{
                resultado.add(false);
            }
        }
        if(checkMartes.isChecked()){
            if(!textMartes.getText().toString().equals("")){
                resultado.add(true);
            }else{
                resultado.add(false);
            }
        }
        if(checkMiercoles.isChecked()){
            if(!textMiercoles.getText().toString().equals("")){
                resultado.add(true);
            }else{
                resultado.add(false);
            }
        }
        if(checkJueves.isChecked()){
            if(!textJueves.getText().toString().equals("")){
                resultado.add(true);
            }else{
                resultado.add(false);
            }
        }
        if(checkViernes.isChecked()){
            if(!textViernes.getText().toString().equals("")){
                resultado.add(true);
            }else{
                resultado.add(false);
            }
        }
        if(checkSabado.isChecked()){
            if(!textSabado.getText().toString().equals("")){
                resultado.add(true);
            }else{
                resultado.add(false);
            }
        }
        if(checkDomingo.isChecked()){
            if(!textDomingo.getText().toString().equals("")){
                resultado.add(true);
            }else{
                resultado.add(false);
            }
        }
        if(resultado.contains(false) || resultado.size() == 0){
            return false;
        }else{
            return true;
        }

    }

    public void siguiente(){
        if(verificarCampos()) {
            guardar("lunes", textLunes.getText().toString());
            guardar("martes", textMartes.getText().toString());
            guardar("miercoles", textMiercoles.getText().toString());
            guardar("jueves", textJueves.getText().toString());
            guardar("viernes", textViernes.getText().toString());
            guardar("sabado", textSabado.getText().toString());
            guardar("domingo", textDomingo.getText().toString());

            Intent i = new Intent(this, CuerpoActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Selecciona al menos una hora libre por día para ejercitarte", Toast.LENGTH_SHORT).show();
        }
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