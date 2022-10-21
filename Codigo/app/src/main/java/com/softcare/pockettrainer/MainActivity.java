package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.softcare.pockettrainer.nivel.ExperienciaActual;
import com.softcare.pockettrainer.rutinas.Rutinas;

public class MainActivity extends AppCompatActivity {
    private Button btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences datos = getSharedPreferences("primer_pantalla", MODE_PRIVATE);
        boolean tieneTodosLosDatos = datos.getBoolean("tiene_datos",false);

        if(!tieneTodosLosDatos) {
            Intent i = new Intent(this, PrimerPantallaActivity.class);
            startActivity(i);
        }


        if(!tieneTodosLosDatos) {
            Intent i = new Intent(this, PrimerPantallaActivity.class);
            startActivity(i);
        }


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date currentDate = new Date();

        SharedPreferences fecha = getSharedPreferences("dia_semana", MODE_PRIVATE);
        SharedPreferences rutina = getSharedPreferences("tiene_rutina", MODE_PRIVATE);
        SharedPreferences rutinaD = getSharedPreferences("rutina", MODE_PRIVATE);
        SharedPreferences dias = getSharedPreferences("dias", MODE_PRIVATE);

        SharedPreferences.Editor editorRutina = rutina.edit();
        SharedPreferences.Editor editor = fecha.edit();
        SharedPreferences.Editor editorRutinaDia = rutinaD.edit();

        Rutinas rutinas = new Rutinas();
        ArrayList<String> diasAL = rutinas.getDias(dias);

        for (String dia : diasAL) {
            editorRutinaDia.putString(dia, "null");
        }

        System.out.println(dateFormat.format(currentDate));

        editor.putString("fecha", dateFormat.format(currentDate));
        editorRutina.putBoolean("tiene_rutina", false);

        editor.apply();
        editorRutina.apply();
        editorRutinaDia.apply();

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

        System.out.println(dateFormat.format(currentDatePlusOne));




        Button btn2 = (Button)findViewById(R.id.btnRutinas);
        Button btn3 = (Button)findViewById(R.id.btnAjustes);


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
    }
}