package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.softcare.pockettrainer.rutinas.Rutinas;

import java.util.Locale;

public class SeleccionTiempoActivity extends AppCompatActivity {

    Button lunes;
    Button martes;
    Button miercoles;
    Button jueves;
    Button viernes;
    Button sabado;
    Button domingo;
    int minuto;
    int hora;
    boolean dis_lunes;
    boolean dis_martes;
    boolean dis_miercoles;
    boolean dis_jueves;
    boolean dis_viernes;
    boolean dis_sabado;
    boolean dis_domingo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_tiempo);
        Intent i = getIntent();
        deshabilitarCajas();

        dis_lunes = i.getBooleanExtra("Lunes", false);
        Log.d("CheckL", ""+lunes);
        dis_martes = i.getBooleanExtra("Martes", false);
        Log.d("CheckMa", ""+martes);
        dis_miercoles = i.getBooleanExtra("Miercoles", false);
        Log.d("CheckMi", ""+miercoles);
        dis_jueves = i.getBooleanExtra("Jueves", false);
        Log.d("CheckJ", ""+jueves);
        dis_viernes = i.getBooleanExtra("Viernes", false);
        Log.d("CheckV", ""+viernes);
        dis_sabado = i.getBooleanExtra("Sabado", false);
        Log.d("CheckS", ""+sabado);
        dis_domingo = i.getBooleanExtra("Domingo", false);
        Log.d("CheckD", ""+domingo);

        deshabilitarBotones();

        lunes = findViewById(R.id.buttonLunes);
        martes = findViewById(R.id.buttonMartes);
        miercoles = findViewById(R.id.buttonMiercoles);
        jueves = findViewById(R.id.buttonJueves);
        viernes = findViewById(R.id.buttonViernes);
        sabado = findViewById(R.id.buttonSabado);
        domingo = findViewById(R.id.buttonDomingo);

    }

    private void deshabilitarCajas(){

        View box = findViewById(R.id.editTextTime);
        EditText e = (EditText) box;
        e.setEnabled(false);

        box = findViewById(R.id.editTextTime2);
        e = (EditText) box;
        e.setEnabled(false);

        box = findViewById(R.id.editTextTime3);
        e = (EditText) box;
        e.setEnabled(false);

        box = findViewById(R.id.editTextTime4);
        e = (EditText) box;
        e.setEnabled(false);

        box = findViewById(R.id.editTextTime5);
        e = (EditText) box;
        e.setEnabled(false);

        box = findViewById(R.id.editTextTime6);
        e = (EditText) box;
        e.setEnabled(false);

        box = findViewById(R.id.editTextTime7);
        e = (EditText) box;
        e.setEnabled(false);

    }

    private void deshabilitarBotones(){
        if(!dis_lunes){
            View view = findViewById(R.id.buttonLunes);
            Button but = (Button) view;
            but.setEnabled(false);
        }
        if(!dis_martes){
            View view = findViewById(R.id.buttonMartes);
            Button but = (Button) view;
            but.setEnabled(false);
        }
        if(!dis_miercoles){
            View view = findViewById(R.id.buttonMiercoles);
            Button but = (Button) view;
            but.setEnabled(false);
        }
        if(!dis_jueves){
            View view = findViewById(R.id.buttonJueves);
            Button but = (Button) view;
            but.setEnabled(false);
        }
        if(!dis_viernes){
            View view = findViewById(R.id.buttonViernes);
            Button but = (Button) view;
            but.setEnabled(false);
        }
        if(!dis_sabado){
            View view = findViewById(R.id.buttonSabado);
            Button but = (Button) view;
            but.setEnabled(false);
        }
        if(!dis_domingo){
            View view = findViewById(R.id.buttonDomingo);
            Button but = (Button) view;
            but.setEnabled(false);
        }

    }

    public void mostrarTimePicker(View v){
        TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hora_seleccionada, int min_seleccionado) {
                hora = hora_seleccionada;
                minuto = min_seleccionado;
                if(v.getId() == R.id.buttonLunes){
                    View box = findViewById(R.id.editTextTime);
                    EditText e = (EditText) box;
                    e.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonMartes){
                    View box = findViewById(R.id.editTextTime2);
                    EditText e = (EditText) box;
                    e.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonMiercoles){
                    View box = findViewById(R.id.editTextTime3);
                    EditText e = (EditText) box;
                    e.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonJueves){
                    View box = findViewById(R.id.editTextTime4);
                    EditText e = (EditText) box;
                    e.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonViernes){
                    View box = findViewById(R.id.editTextTime5);
                    EditText e = (EditText) box;
                    e.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonSabado){
                    View box = findViewById(R.id.editTextTime6);
                    EditText e = (EditText) box;
                    e.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonDomingo){
                    View box = findViewById(R.id.editTextTime7);
                    EditText e = (EditText) box;
                    e.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog time_picker = new TimePickerDialog(this, style, time_listener, hora, minuto, false);
        time_picker.setTitle("Selecciona la hora:");
        time_picker.show();

        Button hecho = (Button) findViewById(R.id.buttonHechoHorario);

        hecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rutinas = new Intent(SeleccionTiempoActivity.this, Rutinas.class);
                startActivity(rutinas);
            }
        });
    }
}