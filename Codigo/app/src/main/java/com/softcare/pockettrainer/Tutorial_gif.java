package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;

import com.bumptech.glide.Glide;
import com.softcare.pockettrainer.rutinas.Ejercicio;

import java.io.IOException;
import java.io.InputStream;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class Tutorial_gif extends AppCompatActivity {
    private Ejercicio ejercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_gif);
        ImageView gif = findViewById(R.id.gifs);

        Intent intent = getIntent();

        ejercicio = (Ejercicio) intent.getSerializableExtra("ejercicio");

        String gifSource = ejercicio.getImagen1().substring(0, ejercicio.getImagen1().length() - 1);

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
}