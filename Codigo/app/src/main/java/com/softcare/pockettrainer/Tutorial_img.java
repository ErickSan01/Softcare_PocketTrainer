package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.softcare.pockettrainer.rutinas.Ejercicio;

public class Tutorial_img extends AppCompatActivity {
    private Ejercicio ejercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_img);
    }

    public void switchImagen(View v){
        Log.i("Info", "Button pressed");
        ImageView image = (ImageView) findViewById(R.id.tutoimg);
        image.setImageResource(R.drawable.is);

    }

    public void previousImagen(View v){
        Log.i("Info", "Button pressed");
        ImageView image = (ImageView) findViewById(R.id.tutoimg);
        image.setImageResource(R.drawable.logo_softcare);

    }

    public void lastImagen(View v){
        Log.i("Info", "Button pressed");
        ImageView image = (ImageView) findViewById(R.id.tutoimg);
        image.setImageResource(R.drawable.pockettrainer);

    }


}