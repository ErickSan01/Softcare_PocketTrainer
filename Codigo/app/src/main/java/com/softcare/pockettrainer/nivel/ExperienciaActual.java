package com.softcare.pockettrainer.nivel;

import androidx.appcompat.app.AppCompatActivity;

import com.softcare.pockettrainer.adminbasededatos.Usuario;

public class ExperienciaActual extends AppCompatActivity {
    private Usuario usuario;
    private final double NIVEL1 = 50;
    private final double NIVEL2 = NIVEL1 * (Math.pow(2, 2.6));
    private final double NIVEL3 = NIVEL1 * (Math.pow(3, 2.6));
    private final double NIVEL4 = NIVEL1 * (Math.pow(4, 2.6));
    private final double NIVEL5 = NIVEL1 * (Math.pow(5, 2.6));

    public ExperienciaActual(Usuario usuario){
        this.usuario = usuario;
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
}
