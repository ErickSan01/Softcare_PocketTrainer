package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenes;
import com.softcare.pockettrainer.adminbasededatos.EjercicioImagenesPresentador;
import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.Rutina;
import com.softcare.pockettrainer.adminbasededatos.RutinaPresentador;
import com.softcare.pockettrainer.adminbasededatos.Usuario;
import com.softcare.pockettrainer.adminbasededatos.UsuarioPresentador;

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
        Intent intent = getIntent();
        btnCompletar = (Button) findViewById(R.id.btnCompletar);

        Button backBtn = (Button) findViewById(R.id.backBtnEj);
        ejercicio = (Ejercicio) intent.getSerializableExtra("ejercicio");
        EjercicioPresentador ejercicioPresentador = new EjercicioPresentador(this);
        ImageView imagenEj = (ImageView) findViewById(R.id.imageView2);
        EjercicioImagenesPresentador eip = new EjercicioImagenesPresentador(this);
        List<EjercicioImagenes> imagenes = ejercicio.obtenerImagenes(eip.obtenerImagen());
        EjercicioImagenes imagen1 = imagenes.get(0);

        String archivo = "imagenes/" + imagen1.getNombreImagen() + ".jpeg";
        if(ejercicio.isTerminado()==true){
            btnCompletar.setEnabled(false);
        }

        try {
            InputStream is = getAssets().open(archivo);

            Drawable d = Drawable.createFromStream(is, null);

            imagenEj.setImageDrawable(d);
            is.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        System.out.println(ejercicio.getNombre());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
}