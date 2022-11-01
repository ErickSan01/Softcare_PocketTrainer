package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenes;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenesPresentador;
import com.softcare.pockettrainer.adminbasededatos.Ejercicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Tutorial_img extends AppCompatActivity {
    private Ejercicio ejercicio;
    private List<EjercicioImagenes> imagenes;
    private ImageView tutoimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_img);
        Intent intent = getIntent();

        ejercicio = (Ejercicio) intent.getSerializableExtra("ejercicio");

        Button btn1 = (Button) findViewById(R.id.btnprev);
        Button btn2 = (Button) findViewById(R.id.btnlast);
        Button backBtn = (Button) findViewById(R.id.backBtnImg);

        tutoimg = findViewById(R.id.tutoimg);

        EjercicioImagenesPresentador eip = new EjercicioImagenesPresentador(this);
        imagenes = ejercicio.obtenerImagenesPropias(eip.obtenerImagen());

        for (EjercicioImagenes imagen :
                imagenes) {
            System.out.println(imagen.getNombreImagen());
        }

        if(imagenes.size() == 1){
            btn1.setEnabled(false);
        }
        if(imagenes.size() <= 2){
            btn2.setEnabled(false);
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void previousImagen(View v){
        EjercicioImagenes imagen = imagenes.get(0);
        String archivo = "imagenes/" + imagen.getNombreImagen() + ".jpeg";

        try {
            InputStream is = getAssets().open(archivo);

            Drawable d = Drawable.createFromStream(is, null);

            tutoimg.setImageDrawable(d);
            is.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        System.out.println(ejercicio.getNombre());
    }

    public void switchImagen(View v){
        EjercicioImagenes imagen = imagenes.get(1);
        String archivo = "imagenes/" + imagen.getNombreImagen() + ".jpeg";

        try {
            InputStream is = getAssets().open(archivo);

            Drawable d = Drawable.createFromStream(is, null);

            tutoimg.setImageDrawable(d);
            is.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }


    public void lastImagen(View v){
        EjercicioImagenes imagen = imagenes.get(2);
        String archivo = "imagenes/" + imagen.getNombreImagen() + ".jpeg";

        try {
            InputStream is = getAssets().open(archivo);

            Drawable d = Drawable.createFromStream(is, null);

            tutoimg.setImageDrawable(d);
            is.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        System.out.println(ejercicio.getNombre());

    }


}