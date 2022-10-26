package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(this);
        usuario = usuarioPresentador.obtenerUsuario().get(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
        Button backBtn = (Button) findViewById(R.id.backBtnNivel);
        ExperienciaActual nvl = new ExperienciaActual(usuario);

        ImageView imagenNvl = (ImageView) findViewById(R.id.imageView6);
        ImageView imagenIns1 = (ImageView) findViewById(R.id.imageView7);
        ImageView imagenIns2 = (ImageView) findViewById(R.id.imageView8);
        ImageView imagenIns3 = (ImageView) findViewById(R.id.imageView9);
        ImageView imagenIns4 = (ImageView) findViewById(R.id.imageView10);
        TextView exp = (TextView) findViewById(R.id.ex);


        nivel = nvl.getLevel();
        experiencia = usuario.getExp();
        cantidadEjercicios = usuario.getEjerciciosCompletados();

        exp.setText(String.valueOf((int) experiencia));

        String nv1 = "drawable/" + "principiante.jpeg";
        String nv2 = "drawable/" + "intermedio.jpeg";
        String nv3 = "drawable/" + "avanzado.jpeg";
        String nv4 = "drawable/" + "experto.jpeg";
        String nv5 = "drawable/" + "leyenda.jpeg";

        try {

            if(nivel == 1){
                InputStream prin = getAssets().open(nv1);
                Drawable p = Drawable.createFromStream(prin, null);
                imagenNvl.setImageDrawable(p);
            }

            if(nivel == 2){
                InputStream inter = getAssets().open(nv2);
                Drawable i = Drawable.createFromStream(inter, null);
                imagenNvl.setImageDrawable(i);
            }

            if(nivel == 3){
                InputStream avan = getAssets().open(nv3);
                Drawable a = Drawable.createFromStream(avan, null);
                imagenNvl.setImageDrawable(a);
            }

            if(nivel == 4){
                InputStream exper = getAssets().open(nv4);
                Drawable e = Drawable.createFromStream(exper, null);
                imagenNvl.setImageDrawable(e);
            }

            if(nivel == 5){
                InputStream leyen = getAssets().open(nv5);
                Drawable l = Drawable.createFromStream(leyen, null);
                imagenNvl.setImageDrawable(l);
            }


        } catch (IOException ioe){
            ioe.printStackTrace();
        }


        String insg1 = "drawable/" + "bronce.jpg";
        String insg2 = "drawable/" + "plata.jpg";
        String insg3 = "drawable/" + "oro.jpg";
        String insg4 = "drawable/" + "platinum.jpg";

        try {

            if(cantidadEjercicios == 50){
                InputStream bronce = getAssets().open(insg1);
                Drawable br = Drawable.createFromStream(bronce, null);
                imagenIns1.setImageDrawable(br);
            }

            if(cantidadEjercicios == 100){
                InputStream plata = getAssets().open(insg2);
                Drawable pla = Drawable.createFromStream(plata, null);
                imagenIns2.setImageDrawable(pla);
            }

            if(cantidadEjercicios == 150){
                InputStream oro = getAssets().open(insg3);
                Drawable or = Drawable.createFromStream(oro, null);
                imagenIns3.setImageDrawable(or);
            }

            if(cantidadEjercicios == 200){
                InputStream platinum = getAssets().open(insg4);
                Drawable plnm = Drawable.createFromStream(platinum, null);
                imagenIns4.setImageDrawable(plnm);
            }


        } catch (IOException ioe){
            ioe.printStackTrace();
        }


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void verNivel(View v){
        Intent img = new Intent(this, RecompensasActivity.class);
        startActivity(img);
    }
}