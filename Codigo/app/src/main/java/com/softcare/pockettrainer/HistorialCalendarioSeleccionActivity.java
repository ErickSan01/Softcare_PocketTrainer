package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.softcare.pockettrainer.adminbasededatos.Usuario;
import com.softcare.pockettrainer.adminbasededatos.UsuarioPresentador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HistorialCalendarioSeleccionActivity extends AppCompatActivity {

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_calendario_seleccion);

        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(this);
        usuario = usuarioPresentador.obtenerUsuario().get(0);

        Button backBtn = (Button) findViewById(R.id.backBtnHistorial);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent i = getIntent();

        String fecha = i.getStringExtra("fecha");
        initText(fecha);
    }

    public void initText(String dia){
        SharedPreferences prefEjercicio = getSharedPreferences("ejerciciosCompletado", MODE_PRIVATE);
        SharedPreferences prefFecha = getSharedPreferences("fechaEjercicio", MODE_PRIVATE);

        TextView textViewFecha = (TextView) findViewById(R.id.textViewFecha);
        TextView textViewAccion = (TextView) findViewById(R.id.textViewAccion);
        TextView textViewRutina = (TextView) findViewById(R.id.textViewRutina);

        Date diaHoy = Calendar.getInstance().getTime();
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(diaHoy);

        boolean fechaSeleccionada = prefFecha.getBoolean(dia, false);

        System.out.println(dia);

        if(fechaSeleccionada){
            String ejercicioCompletado = prefEjercicio.getString(dia, "");
            if(!ejercicioCompletado.equals("")){
                textViewFecha.setText(dia);
                textViewAccion.setText("Parte trabajada ese día: ");
                textViewRutina.setText(ejercicioCompletado);
            }else{
                textViewFecha.setText(dia);
                textViewAccion.setText("Oops!, ocurrió un error al llamar a la base de datos! ");
                textViewRutina.setText(ejercicioCompletado);
            }
        }else{
            textViewFecha.setText(dia);
            textViewAccion.setText("No hubo rutina ese día!");
            textViewRutina.setText("");
        }

    }
}