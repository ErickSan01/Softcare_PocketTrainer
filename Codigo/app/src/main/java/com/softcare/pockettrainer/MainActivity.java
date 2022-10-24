package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.rutinas.Rutinas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn2, btn3, btn4, btn5;
    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences datos = getSharedPreferences("primer_pantalla", MODE_PRIVATE);
        boolean tieneTodosLosDatos = datos.getBoolean("tiene_datos",false);

        ayudante = new AyudanteBaseDeDatos(this);

        final Calendar calendario = Calendar.getInstance();

        day = calendario.get(Calendar.DAY_OF_MONTH);
        month = calendario.get(Calendar.MONTH);
        year = calendario.get(Calendar.YEAR);

        DatePickerDialog picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int daySeleccionado, int monthSeleccionado, int yearSeleccionado) {

                String dayFormato, monthFormato;

                if(daySeleccionado < 10){
                    dayFormato = "0"+String.valueOf(daySeleccionado);
                }else{
                    dayFormato = String.valueOf(daySeleccionado);
                }

                int monthReal = monthSeleccionado + 1;
                if(monthReal < 10){
                    monthFormato = "0" + String.valueOf(monthReal);
                }else{
                    monthFormato = String.valueOf(monthReal);
                }
            }
        }
        , year, month, day);
        picker.show();

        /*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date currentDate = new Date();
        SharedPreferences dias = getSharedPreferences("dias", MODE_PRIVATE);


        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        // manipulate date
        c.add(Calendar.YEAR, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 1);
        c.add(Calendar.MINUTE, 1);
        c.add(Calendar.SECOND, 1);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();

        System.out.println(dateFormat.format(currentDatePlusOne));*/

        Button btn2 = (Button)findViewById(R.id.btnRutinas);
        Button btn3 = (Button)findViewById(R.id.btnAjustes);
        Button btn4 = (Button)findViewById(R.id.btnNivel);
        Button btn5 = (Button)findViewById(R.id.buttonPrueba);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistorialActivity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Rutinas.class));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AjustesActivity.class));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NivelActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    public void crearRutina(){
        EjercicioPresentador ejercicioPresentador = new EjercicioPresentador(this);

        ArrayList<Ejercicio> ejercicios = ejercicioPresentador.obtenerEjercicio(this);

        for (Ejercicio ejercicio :
                ejercicios){
            System.out.println(ejercicio.getNombre());
        }

    }


}