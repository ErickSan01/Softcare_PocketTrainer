package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import com.softcare.pockettrainer.rutinas.Ejercicio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TutorialActivity extends AppCompatActivity{
    private Ejercicio ejercicio;

    public TutorialActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        Intent intent = getIntent();

        ejercicio = (Ejercicio) intent.getSerializableExtra("ejercicio");

        ImageView imagenEj = (ImageView) findViewById(R.id.imageView2);
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

    public void verImagen(View v){
        Intent img = new Intent(this, Tutorial_img.class);
        img.putExtra("ejercicio", ejercicio);
        startActivity(img);
    }

    public void verGif(View v){
        Intent img = new Intent(this, Tutorial_gif.class);
        img.putExtra("ejercicio", ejercicio);
        startActivity(img);
    }

    public void verMaterial(View v){
        Intent img = new Intent(this, MaterialActivity.class);
        img.putExtra("ejercicio", ejercicio);
        startActivity(img);
    }
}