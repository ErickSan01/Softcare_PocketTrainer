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

import com.softcare.pockettrainer.rutinas.Ejercicio;

import java.io.IOException;
import java.io.InputStream;

public class Tutorial_img extends AppCompatActivity {
    private Ejercicio ejercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_img);
        Intent intent = getIntent();

        ejercicio = (Ejercicio) intent.getSerializableExtra("ejercicio");

        Button btn1 = (Button) findViewById(R.id.btnprev);
        Button btn2 = (Button) findViewById(R.id.btnlast);
        Button backBtn = (Button) findViewById(R.id.backBtnImg);

        if(ejercicio.getImagen2() == null){
            btn1.setEnabled(false);
        }
        if(ejercicio.getImagen3() == null){
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
        Log.i("Info", "Button pressed");
        ImageView imagenEj = (ImageView) findViewById(R.id.tutoimg);
        String archivo = "imagenes/" + ejercicio.getImagen1() + ".jpeg";

        try {
            InputStream is = getAssets().open(archivo);

            Drawable d = Drawable.createFromStream(is, null);

            imagenEj.setImageDrawable(d);
            is.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        System.out.println(ejercicio.getNombre());
    }

    public void switchImagen(View v){
        Log.i("Info", "Button pressed");
        ImageView imagenEj = (ImageView) findViewById(R.id.tutoimg);
        if(ejercicio.getImagen2() != null){

            String archivo = "imagenes/" + ejercicio.getImagen2() + ".jpeg";

            try {
                InputStream is = getAssets().open(archivo);

                Drawable d = Drawable.createFromStream(is, null);

                imagenEj.setImageDrawable(d);
                is.close();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

        }

        System.out.println(ejercicio.getNombre());
    }


    public void lastImagen(View v){
        Log.i("Info", "Button pressed");
        ImageView image = (ImageView) findViewById(R.id.tutoimg);
        image.setImageResource(R.drawable.pockettrainer);

    }


}