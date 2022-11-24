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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_libres_cuestionario);

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
                siguiente();
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
        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog time_picker = new TimePickerDialog(this, style, time_listener, hora, minuto, false);
        time_picker.setTitle("Selecciona la hora:");
        time_picker.show();
    }

    private boolean verificarCampos(){
        boolean resultado = false;
        if(checkLunes.isChecked()){
            resultado = !textLunes.getText().toString().equals("");
        }
        if(checkMartes.isChecked()){
            resultado = resultado && !textMartes.getText().toString().equals("");
        }
        if(checkMiercoles.isChecked()){
            resultado = resultado && !textMiercoles.getText().toString().equals("");
        }
        if(checkJueves.isChecked()){
            resultado = resultado && !textJueves.getText().toString().equals("");
        }
        if(checkViernes.isChecked()){
            resultado = resultado && !textViernes.getText().toString().equals("");
        }
        if(checkSabado.isChecked()){
            resultado = resultado && !textSabado.getText().toString().equals("");
        }
        if(checkDomingo.isChecked()){
            resultado = resultado && !textDomingo.getText().toString().equals("");
        }

        return resultado;
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
}