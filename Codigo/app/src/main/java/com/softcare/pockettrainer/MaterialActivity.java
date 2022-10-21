package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.softcare.pockettrainer.adminbasededatos.Material;
import com.softcare.pockettrainer.adminbasededatos.MaterialesPresentador;
import com.softcare.pockettrainer.rutinas.Ejercicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MaterialActivity extends AppCompatActivity {
    private Ejercicio ejercicio;
    private Material materialEjercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        Intent intent = getIntent();

        ejercicio = (Ejercicio) intent.getSerializableExtra("ejercicio");

        MaterialesPresentador materialesC = new MaterialesPresentador(this);

        ArrayList<Material> materiales = materialesC.obtenerMaterial();

        materialEjercicio = null;

        for (Material material : materiales) {
            if(material.getIdMaterial() == ejercicio.getId_material()){
                materialEjercicio = material;
            }
        }

        Button btn1 = (Button) findViewById(R.id.button6);
        Button btn2 = (Button) findViewById(R.id.button7);

        /*
        if(materialEjercicio.getImagen2() == null){
            btn1.setEnabled(false);
        }
         */

        btn2.setEnabled(false);
    }

    /*
    public void previousImagen(View v){
        Log.i("Info", "Button pressed");

        ImageView imagenEj = (ImageView) findViewById(R.id.ivstr);
        String archivo = "imagenes/materiales/" + materialEjercicio.getImagen1() + ".png";

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
     */

    /*
    public void switchImagen(View v){
        Log.i("Info", "Button pressed");

        ImageView imagenEj = (ImageView) findViewById(R.id.ivstr);
        String archivo = "imagenes/materiales/" + materialEjercicio.getImagen2() + ".png";

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
     */


    public void lastImagen(View v){

    }
}