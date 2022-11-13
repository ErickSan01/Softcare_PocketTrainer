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
import com.softcare.pockettrainer.adminbasededatos.MaterialImagenes;
import com.softcare.pockettrainer.adminbasededatos.MaterialImagenesPresentador;
import com.softcare.pockettrainer.adminbasededatos.MaterialesPresentador;
import com.softcare.pockettrainer.adminbasededatos.Ejercicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends AppCompatActivity {
    private Ejercicio ejercicio;
    private Material materialEjercicio;
    private List<MaterialImagenes> imagenesMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        Intent intent = getIntent();

        ejercicio = (Ejercicio) intent.getSerializableExtra("ejercicio");

        MaterialesPresentador materialesC = new MaterialesPresentador(this);
        MaterialImagenesPresentador materialImagenesPresentador = new MaterialImagenesPresentador(this);
        ArrayList<MaterialImagenes> imagenes = materialImagenesPresentador.obtenerImagen();
        ArrayList<Material> materiales = materialesC.obtenerMaterial();

        materialEjercicio = null;

        for (Material material : materiales) {
            if(material.getIdMaterial() == ejercicio.getIdMaterial()){
                materialEjercicio = material;
            }
        }

        imagenesMaterial = materialEjercicio.obtenerImagenesPropias(imagenes);

        /*Button step2 = (Button) findViewById(R.id.button6);
        Button step3 = (Button) findViewById(R.id.button7);*/
        Button backBtn = findViewById(R.id.backBtnImg2);

        System.out.println(imagenesMaterial.size());
/*
        if(imagenesMaterial.size() == 1){
            step2.setEnabled(false);
        }
        if(imagenesMaterial.size() < 2){
            step3.setEnabled(false);
        }*/

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void previousImagen(View v){
        ImageView imagenEj = (ImageView) findViewById(R.id.ivstr);

        String archivo = "imagenes/materiales/" + imagenesMaterial.get(0).getNombreImagen() + ".png";

        try {
            InputStream is = getAssets().open(archivo);

            Drawable d = Drawable.createFromStream(is, null);

            imagenEj.setImageDrawable(d);
            is.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }


    public void switchImagen(View v){
        ImageView imagenEj = (ImageView) findViewById(R.id.ivstr);

        String archivo = "imagenes/materiales/" + imagenesMaterial.get(1).getNombreImagen() + ".png";

        try {
            InputStream is = getAssets().open(archivo);

            Drawable d = Drawable.createFromStream(is, null);

            imagenEj.setImageDrawable(d);
            is.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }



    public void lastImagen(View v){
        ImageView imagenEj = (ImageView) findViewById(R.id.ivstr);

        String archivo = "imagenes/materiales/" + imagenesMaterial.get(2).getNombreImagen() + ".png";

        try {
            InputStream is = getAssets().open(archivo);

            Drawable d = Drawable.createFromStream(is, null);

            imagenEj.setImageDrawable(d);
            is.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}