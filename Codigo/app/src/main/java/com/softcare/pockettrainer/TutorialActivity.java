package com.softcare.pockettrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenes;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenesPresentador;
import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.adminbasededatos.RutinaPresentador;
import com.softcare.pockettrainer.adminbasededatos.Usuario;
import com.softcare.pockettrainer.adminbasededatos.UsuarioPresentador;
import com.softcare.pockettrainer.nivel.ExperienciaActual;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TutorialActivity extends AppCompatActivity{
    private Ejercicio ejercicio;
    private Button btnCompletar;
    private Usuario usuario;

    public TutorialActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        btnCompletar = (Button) findViewById(R.id.btnCompletar);
        ejercicio = (Ejercicio) intent.getSerializableExtra("ejercicio");
        EjercicioPresentador ejercicioPresentador = new EjercicioPresentador(this);
        EjercicioImagenesPresentador eip = new EjercicioImagenesPresentador(this);
        List<EjercicioImagenes> imagenes = ejercicio.obtenerImagenesPropias(eip.obtenerImagen());
        EjercicioImagenes imagen1 = imagenes.get(0);

        String archivo = "imagenes/" + imagen1.getNombreImagen() + ".jpeg";
        if(ejercicio.isTerminado()){
            btnCompletar.setEnabled(false);
            btnCompletar.setBackgroundColor(Color.GREEN);
        }

        ImageSlider imageSlider = findViewById(R.id.imageView2);
        ArrayList<SlideModel> imageList = new ArrayList<>();

        for (EjercicioImagenes imagen : imagenes) {
            String archivoS = "file:///android_asset/imagenes/" + imagen.getNombreImagen() + ".jpeg";
            try {
                Drawable d = Drawable.createFromStream(getAssets().open("imagenes/burpess1.jpeg"), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(archivoS);
            imageList.add(new SlideModel("file:///android_asset/imagenes/" + imagen.getNombreImagen() + ".jpeg", null));
        }

        imageSlider.setImageList(imageList);

        btnCompletar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    SharedPreferences prefEjercicio = getSharedPreferences("ejerciciosCompletado", MODE_PRIVATE);
                    SharedPreferences prefFecha = getSharedPreferences("fechaEjercicio", MODE_PRIVATE);
                    SharedPreferences.Editor editorEjercicio = prefEjercicio.edit();
                    SharedPreferences.Editor editorFecha = prefFecha.edit();

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();

                    RutinaPresentador rutina = new RutinaPresentador(TutorialActivity.this);

                    btnCompletar.setText("Completado");
                    btnCompletar.setBackgroundColor(Color.GREEN);
                    ejercicio.setTerminado(true);
                    ejercicioPresentador.guardarCambios(ejercicio);
                    ArrayList<Usuario> usuarios = usuarioPresentador.obtenerUsuario();
                    Usuario usuario = usuarios.get(0);
                    usuario.setExp(ejercicio.getPuntosEXP()+usuario.getExp());
                    usuario.setEjerciciosCompletados(usuario.getEjerciciosCompletados()+1);
                    usuarioPresentador.guardarCambios(usuario);
                    btnCompletar.setEnabled(false);

                    Rutina rutinaTemporal = rutina.obtenerRutina(ejercicio.getIdRutina());
                    editorFecha.putBoolean(formatter.format(date), true);
                    editorEjercicio.putString(formatter.format(date), rutinaTemporal.getParteCuerpo());

                    editorEjercicio.apply();
                    editorFecha.apply();

                    editorEjercicio.commit();
                    editorFecha.commit();
                    ExperienciaActual experienciaUsuario = new ExperienciaActual(usuario, TutorialActivity.this);

                    experienciaUsuario.setNivel();
                }
                return false;
            }

        });
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