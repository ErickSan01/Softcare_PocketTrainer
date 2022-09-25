package com.softcare.pockettrainer.rutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;
import com.softcare.pockettrainer.PantallaPrincipalActivity;
import com.softcare.pockettrainer.R;

public class Rutinas extends AppCompatActivity {
    private AyudanteBaseDeDatos ayudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutinas_activity);
        ayudante = new AyudanteBaseDeDatos(Rutinas.this);
        SQLiteDatabase db = ayudante.getWritableDatabase();

        Button backButton = (Button) findViewById(R.id.backBtn);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent mainPage = new Intent(Rutinas.this, PantallaPrincipalActivity.class);
                startActivity(mainPage);
            }
        });

    }
}