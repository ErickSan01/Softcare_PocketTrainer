package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
    }

    public void verImagen(View v){
        Intent img = new Intent(this, Tutorial_img.class);
        startActivity(img);
    }

    public void verGif(View v){
        Intent img = new Intent(this, Tutorial_gif.class);
        startActivity(img);
    }

    public void verMaterial(View v){
        Intent img = new Intent(this, MaterialActivity.class);
        startActivity(img);
    }
}