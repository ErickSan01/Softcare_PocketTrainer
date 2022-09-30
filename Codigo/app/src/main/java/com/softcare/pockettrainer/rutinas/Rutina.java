package com.softcare.pockettrainer.rutinas;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class Rutina {
    private ArrayList<Ejercicio> listaEjercicios;


    public Rutina(ArrayList<Ejercicio> listaEjercicios){
        this.listaEjercicios = listaEjercicios;
    }

    public void calcular(){

    }

    public void setListaEjercicios(ArrayList<Ejercicio> listaEjercicios){
        this.listaEjercicios = listaEjercicios;
    }

    public void agregarEjercicio(Ejercicio ejercicio){
        listaEjercicios.add(ejercicio);
    }

    public ArrayList<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    @NonNull
    @Override
    public String toString() {
        return "Rutina: " + listaEjercicios;
    }
}
