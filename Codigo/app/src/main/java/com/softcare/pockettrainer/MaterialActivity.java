package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenes;
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


        ImageSlider imageSlider = findViewById(R.id.ivstr);
        ArrayList<SlideModel> imageList = new ArrayList<>();

        for (MaterialImagenes imagen : imagenesMaterial) {
            String archivoS = "file:///android_asset/imagenes/materiales/" + imagen.getNombreImagen() + ".png";
            System.out.println(archivoS);
            imageList.add(new SlideModel("file:///android_asset/imagenes/materiales/" + imagen.getNombreImagen() + ".png", null));
        }

        imageSlider.setImageList(imageList);
    }
}