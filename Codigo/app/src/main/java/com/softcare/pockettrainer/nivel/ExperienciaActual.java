package com.softcare.pockettrainer.nivel;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.softcare.pockettrainer.adminbasededatos.Usuario;
import com.softcare.pockettrainer.adminbasededatos.UsuarioPresentador;

public class ExperienciaActual extends AppCompatActivity {
    private Usuario usuario;
    private Context context;
    private final double NIVEL1 = 50;
    private final double NIVEL2 = NIVEL1 * (Math.pow(2, 2.6));
    private final double NIVEL3 = NIVEL1 * (Math.pow(3, 2.6));
    private final double NIVEL4 = NIVEL1 * (Math.pow(4, 2.6));
    private final double NIVEL5 = NIVEL1 * (Math.pow(5, 2.6));

    public ExperienciaActual(Usuario usuario, Context context){
        this.usuario = usuario;
        this.context = context;
    }

    public int obtenerExperiencia(){
        return usuario.getExp();
    }

    public int getLevel(){
        int exp = obtenerExperiencia();
        if(exp >= NIVEL1){
            return 1;
        }
        if(exp >= NIVEL2 && exp < NIVEL3){
            return 2;
        }
        if(exp >= NIVEL3 && exp < NIVEL4){
            return 3;
        }
        if(exp >= NIVEL4 && exp < NIVEL5){
            return 4;
        }
        if(exp >= NIVEL5){
            return 5;
        }
        return 0;
    }

    public double getExp(){
        int nivel = getLevel();
        if(nivel == 1){
            return NIVEL1;
        }
        if(nivel == 2){
            return NIVEL2;
        }
        if(nivel == 3){
            return NIVEL3;
        }
        if(nivel == 4){
            return NIVEL4;
        }
        if(nivel == 5){
            return NIVEL5;
        }

        return 0;
    }

    public void setNivel(){
        int exp = obtenerExperiencia();
        if(exp >= NIVEL1){
            usuario.setNivel(1);
        }
        if(exp >= NIVEL2 && exp < NIVEL3){
            usuario.setNivel(2);
        }
        if(exp >= NIVEL3 && exp < NIVEL4){
            usuario.setNivel(3);
        }
        if(exp >= NIVEL4 && exp < NIVEL5){
            usuario.setNivel(4);
        }
        if(exp >= NIVEL5){
            usuario.setNivel(0);
        }

        UsuarioPresentador usuarioPresentador = new UsuarioPresentador(context);

        usuarioPresentador.guardarCambios(usuario);
    }
}
