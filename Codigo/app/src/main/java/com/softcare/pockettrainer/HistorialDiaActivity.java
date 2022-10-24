package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.softcare.pockettrainer.adminbasededatos.Usuario;
import com.softcare.pockettrainer.adminbasededatos.UsuarioPresentador;

public class HistorialDiaActivity extends AppCompatActivity {

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_dia);

        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(this);
        AyudanteBaseDeDatos ayuda = new AyudanteBaseDeDatos(this);
        ayuda.agregarUsuario();
        usuario = usuarioPresentador.obtenerUsuario().get(0);

        Intent i = getIntent();

        String fecha = i.getStringExtra("fecha");
        initText(fecha);
    }

    public void initText(String dia){
        TextView textViewFecha = (TextView) findViewById(R.id.textViewFecha);
        textViewFecha.setText(dia);
    }
}