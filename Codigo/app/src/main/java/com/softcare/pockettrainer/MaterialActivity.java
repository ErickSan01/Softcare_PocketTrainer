package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
    }

    public void switchImagen(View v){
        Log.i("Info", "Button pressed");
        ImageView image = (ImageView) findViewById(R.id.ivstr);
        image.setImageResource(R.drawable.logo_pt);
    }

    public void previousImagen(View v){
        Log.i("Info", "Button pressed");
        ImageView image = (ImageView) findViewById(R.id.ivstr);
        image.setImageResource(R.drawable.is);
    }

    public void lastImagen(View v){
        Log.i("Info", "Button pressed");
        ImageView image = (ImageView) findViewById(R.id.ivstr);
        image.setImageResource(R.drawable.logo_pt);
    }
}