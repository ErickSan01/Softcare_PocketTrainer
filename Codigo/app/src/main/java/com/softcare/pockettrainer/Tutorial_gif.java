package com.softcare.pockettrainer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenes;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenesPresentador;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Tutorial_gif extends AppCompatActivity {
    private Ejercicio ejercicio;
    private List<EjercicioImagenes> imagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_gif);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ImageView gif = findViewById(R.id.gifs);

        Intent intent = getIntent();

        ejercicio = (Ejercicio) intent.getSerializableExtra("ejercicio");
        EjercicioImagenesPresentador eip = new EjercicioImagenesPresentador(this);
        imagenes = ejercicio.obtenerImagenesPropias(eip.obtenerImagen());

        String gifSource = imagenes.get(0).getNombreImagen().substring(0, imagenes.get(0).getNombreImagen().length() - 1);
        System.out.println(gifSource);

        try {
            InputStream is = getAssets().open("gifs/" + gifSource + ".gif");

            System.out.println(gifSource);

            Drawable d = Drawable.createFromStream(is, null);

            Glide.with(this).load(d).into(gif);


            is.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }


        System.out.println(gifSource);
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