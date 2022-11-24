package com.softcare.pockettrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.softcare.pockettrainer.adminbasededatos.Usuario;
import com.softcare.pockettrainer.adminbasededatos.UsuarioPresentador;
import com.softcare.pockettrainer.nivel.ExperienciaActual;

import java.io.IOException;
import java.io.InputStream;

public class NivelActivity extends AppCompatActivity {

    private Usuario usuario;
    private int nivel;
    private double experiencia;
    private int cantidadEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(this);
        AyudanteBaseDeDatos ayuda = new AyudanteBaseDeDatos(this);
        ayuda.agregarUsuario();
        usuario = usuarioPresentador.obtenerUsuario().get(0);


        ExperienciaActual nvl = new ExperienciaActual(usuario, this);

        nivel = usuario.getNivel();
        experiencia = usuario.getExp();
        cantidadEjercicios = usuario.getEjerciciosCompletados();

        ImageView imagenNvl = (ImageView) findViewById(R.id.imageView6);
        ImageView imagenIns1 = (ImageView) findViewById(R.id.imageView7);
        ImageView imagenIns2 = (ImageView) findViewById(R.id.imageView8);
        ImageView imagenIns3 = (ImageView) findViewById(R.id.imageView9);
        ImageView imagenIns4 = (ImageView) findViewById(R.id.imageView10);
        TextView exp = (TextView) findViewById(R.id.ex);

        exp.setText(String.valueOf((int) experiencia));

        String nvl0 = "@drawable/level0";
        String nv1 = "@drawable/" + "principiante";
        String nv2 = "@drawable/" + "intermedio";
        String nv3 = "@drawable/" + "avanzado";
        String nv4 = "@drawable/" + "experto";
        String nv5 = "@drawable/" + "leyenda";

        System.out.println(nivel);
        int idDra = getResources().getIdentifier(nvl0, null, getPackageName());
        Drawable nv0 = getResources().getDrawable(idDra);
        imagenNvl.setImageDrawable(nv0);
        if(nivel == 1){
            int idLvl1 = getResources().getIdentifier(nv1, null, getPackageName());
            Drawable nv1D = getResources().getDrawable(idLvl1);
            imagenNvl.setImageDrawable(nv1D);
        }

        if(nivel == 2){
            int idLvl2 = getResources().getIdentifier(nv2, null, getPackageName());
            Drawable nv2D = getResources().getDrawable(idLvl2);
            imagenNvl.setImageDrawable(nv2D);
        }

        if(nivel == 3){
            int idLvl3 = getResources().getIdentifier(nv3, null, getPackageName());
            Drawable nv3D = getResources().getDrawable(idLvl3);
            imagenNvl.setImageDrawable(nv3D);
        }

        if(nivel == 4){
            int idLvl4 = getResources().getIdentifier(nv4, null, getPackageName());
            Drawable nv4D = getResources().getDrawable(idLvl4);
            imagenNvl.setImageDrawable(nv4D);
        }

        if(nivel == 5){
            int idLvl5 = getResources().getIdentifier(nv5, null, getPackageName());
            Drawable nv5D = getResources().getDrawable(idLvl5);
            imagenNvl.setImageDrawable(nv5D);
        }


        String insg1 = "drawable/" + "bronce";
        String insg2 = "drawable/" + "plata";
        String insg3 = "drawable/" + "oro";
        String insg4 = "drawable/" + "platinum";

        if(cantidadEjercicios == 50){
            int idBr = getResources().getIdentifier(insg1, null, getPackageName());
            Drawable br = getResources().getDrawable(idBr);
            imagenIns1.setImageDrawable(br);
        }
        if(cantidadEjercicios == 100){
            int idBr = getResources().getIdentifier(insg1, null, getPackageName());
            Drawable br = getResources().getDrawable(idBr);
            imagenIns1.setImageDrawable(br);
            int idPlata = getResources().getIdentifier(insg2, null, getPackageName());
            Drawable plata = getResources().getDrawable(idPlata);
            imagenIns2.setImageDrawable(plata);
        }

        if(cantidadEjercicios == 150){
            int idBr = getResources().getIdentifier(insg1, null, getPackageName());
            Drawable br = getResources().getDrawable(idBr);
            imagenIns1.setImageDrawable(br);
            int idPlata = getResources().getIdentifier(insg2, null, getPackageName());
            Drawable plata = getResources().getDrawable(idPlata);
            imagenIns2.setImageDrawable(plata);
            int idOro = getResources().getIdentifier(insg3, null, getPackageName());
            Drawable oro = getResources().getDrawable(idOro);
            imagenIns3.setImageDrawable(oro);
        }

        if(cantidadEjercicios == 200){
            int idBr = getResources().getIdentifier(insg1, null, getPackageName());
            Drawable br = getResources().getDrawable(idBr);
            imagenIns1.setImageDrawable(br);
            int idPlata = getResources().getIdentifier(insg2, null, getPackageName());
            Drawable plata = getResources().getDrawable(idPlata);
            imagenIns2.setImageDrawable(plata);
            int idOro = getResources().getIdentifier(insg3, null, getPackageName());
            Drawable oro = getResources().getDrawable(idOro);
            imagenIns3.setImageDrawable(oro);
            int idPlat = getResources().getIdentifier(insg4, null, getPackageName());
            Drawable platinum = getResources().getDrawable(idPlat);
            imagenIns4.setImageDrawable(platinum);
        }
    }

    public void verNivel(View v){
        Intent img = new Intent(this, RecompensasActivity.class);
        startActivity(img);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}