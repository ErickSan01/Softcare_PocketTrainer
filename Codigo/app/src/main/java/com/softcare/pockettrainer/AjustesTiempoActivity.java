package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.softcare.pockettrainer.adminbasededatos.Horario;
import com.softcare.pockettrainer.adminbasededatos.HorarioPresentador;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramada;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramadaPresentador;

import java.util.Locale;

public class AjustesTiempoActivity extends AppCompatActivity {

    //Los campos edit text correspondientes a los dias de la semana
    EditText lunes;
    EditText martes;
    EditText miercoles;
    EditText jueves;
    EditText viernes;
    EditText sabado;
    EditText domingo;
    //minuto y hora necesarios para el time picker
    int minuto;
    int hora;
    //Booleanos que no ayudan a deshabilitar visualmente los botones
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
        setContentView(R.layout.activity_ajustes_tiempo);
        Intent i = getIntent();

        deshabilitarCajas();

        //Extraemos las selecciones del usuario en la pantalla anterior, y en con base a eso
        //decidimos que botones son los que se usan y cuales se deshabilitan
        dis_lunes = i.getBooleanExtra("Lunes", false);
        dis_martes = i.getBooleanExtra("Martes", false);
        dis_miercoles = i.getBooleanExtra("Miercoles", false);
        dis_jueves = i.getBooleanExtra("Jueves", false);
        dis_viernes = i.getBooleanExtra("Viernes", false);
        dis_sabado = i.getBooleanExtra("Sabado", false);
        dis_domingo = i.getBooleanExtra("Domingo", false);

        //Extraemos los edit text para poder usarlos más adelante con el SharedPreferences
        View view = findViewById(R.id.editTextTime);
        lunes = (EditText) view;
        view = findViewById(R.id.editTextTime2);
        martes = (EditText) view;
        view = findViewById(R.id.editTextTime3);
        miercoles = (EditText) view;
        view = findViewById(R.id.editTextTime4);
        jueves = (EditText) view;
        view = findViewById(R.id.editTextTime5);
        viernes = (EditText) view;
        view = findViewById(R.id.editTextTime6);
        sabado = (EditText) view;
        view = findViewById(R.id.editTextTime7);
        domingo = (EditText) view;

        Button backBtn = (Button) findViewById(R.id.backBtnAjustesTiempo);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //llamamos hasta el final el deshabilite de botones porque necesitamos los edit text para
        //mostrar las preferencias guardadas de shared preferences
        deshabilitarBotones();
    }

    //Deshabilitamos todas las cajas por default, para que no puedan ser editadas manualmente
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

    /*
    Tiene dos funciones principales: 1-Deshabilita los botones que no son necesarios usar
    de acuerdo con las decisiones del usuario en la pantalla anterior y 2-Muestra las preferencias guardadas
    previamente, en caso de que el usuario no eligió un día en específico pero ya se encontraba previamente,
    este no se mostrará, es por eso que se usó este método para hacerlo.
    */
    private void deshabilitarBotones(){
        SharedPreferences preferencias = getSharedPreferences("tiempo", MODE_PRIVATE);

        if(!dis_lunes){
            View view = findViewById(R.id.buttonLunes);
            Button but = (Button) view;
            lunes.setText("");
            but.setEnabled(false);
        }else{
            lunes.setText(preferencias.getString("lunes", ""));
        }
        if(!dis_martes){
            View view = findViewById(R.id.buttonMartes);
            Button but = (Button) view;
            martes.setText("");
            but.setEnabled(false);
        }else{
            martes.setText(preferencias.getString("martes", ""));
        }
        if(!dis_miercoles){
            View view = findViewById(R.id.buttonMiercoles);
            Button but = (Button) view;
            miercoles.setText("");
            but.setEnabled(false);
        }else{
            miercoles.setText(preferencias.getString("miercoles", ""));
        }
        if(!dis_jueves){
            View view = findViewById(R.id.buttonJueves);
            Button but = (Button) view;
            jueves.setText("");
            but.setEnabled(false);
        }else{
            jueves.setText(preferencias.getString("jueves", ""));
        }
        if(!dis_viernes){
            View view = findViewById(R.id.buttonViernes);
            Button but = (Button) view;
            viernes.setText("");
            but.setEnabled(false);
        }else{
            viernes.setText(preferencias.getString("viernes", ""));
        }
        if(!dis_sabado){
            View view = findViewById(R.id.buttonSabado);
            Button but = (Button) view;
            sabado.setText("");
            but.setEnabled(false);
        }else{
            sabado.setText(preferencias.getString("sabado", ""));
        }
        if(!dis_domingo){
            View view = findViewById(R.id.buttonDomingo);
            Button but = (Button) view;
            domingo.setText("");
            but.setEnabled(false);
        }else{
            domingo.setText(preferencias.getString("domingo", ""));
        }

    }

    //Controla el TimePicker y muestra a la vez los elegido en el edit text correspondiente
    public void mostrarTimePicker(View v){
        TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hora_seleccionada, int min_seleccionado) {
                hora = hora_seleccionada;
                minuto = min_seleccionado;
                if(v.getId() == R.id.buttonLunes){
                    lunes.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonMartes){
                    martes.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonMiercoles){
                    miercoles.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonJueves){
                    jueves.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonViernes){
                    viernes.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonSabado){
                    sabado.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
                if(v.getId() == R.id.buttonDomingo){
                    domingo.setText(String.format(Locale.getDefault(),"%02d:%02d", hora, minuto));
                }
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog time_picker = new TimePickerDialog(this, style, time_listener, hora, minuto, false);
        time_picker.setTitle("Selecciona la hora:");
        time_picker.show();
    }

    //Guarda las preferencias según su campo y valor
    public void guardar(String campo, String valor){
        SharedPreferences preferencias = getSharedPreferences("tiempo", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(campo, valor);
        editor.apply();
        finish();
    }

    //Al presionar el botón siguiente, este guarda las preferencias de la hora en formato String
    public void Siguiente(View v){

        if(verificarCampos()) {
            guardar("lunes", lunes.getText().toString());
            guardar("martes", martes.getText().toString());
            guardar("miercoles", miercoles.getText().toString());
            guardar("jueves", jueves.getText().toString());
            guardar("viernes", viernes.getText().toString());
            guardar("sabado", sabado.getText().toString());
            guardar("domingo", domingo.getText().toString());

            RutinaProgramadaPresentador rutinaProgramadaPresentador = new RutinaProgramadaPresentador(this);
            RutinaProgramada rutinaProgramada = rutinaProgramadaPresentador.obtenerRutinaProgramada().get(0);

            rutinaProgramadaPresentador.eliminarRutinaProgramada(rutinaProgramada);

            HorarioPresentador horarioPresentador = new HorarioPresentador(this);
            Horario horario = horarioPresentador.obtenerHorario().get(0);

            horarioPresentador.eliminarHorario(horario);

            AyudanteBaseDeDatos ayudante = new AyudanteBaseDeDatos(this);

            ayudante.agregarHorario();

            Intent i = new Intent(this, AjustesActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Selecciona al menos una hora libre por día para ejercitarte", Toast.LENGTH_LONG).show();
        }
    }

    //Verifica si los campos de los tiempos están completos o no, si es asi regresa true
    private boolean verificarCampos(){
        boolean resultado = true;
        if(dis_lunes){
            resultado = !lunes.getText().toString().equals("");
        }
        if(dis_martes){
            resultado = resultado && !martes.getText().toString().equals("");
        }
        if(dis_miercoles){
            resultado = resultado && !miercoles.getText().toString().equals("");
        }
        if(dis_jueves){
            resultado = resultado && !jueves.getText().toString().equals("");
        }
        if(dis_viernes){
            resultado = resultado && !viernes.getText().toString().equals("");
        }
        if(dis_sabado){
            resultado = resultado && !sabado.getText().toString().equals("");
        }
        if(dis_domingo){
            resultado = resultado && !domingo.getText().toString().equals("");
        }

        return resultado;
    }
}