package com.softcare.pockettrainer.rutinas;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class Rutina {
    private ArrayList<Integer> listaEjercicios;


    public Rutina(ArrayList<Integer> listaEjercicios){
        this.listaEjercicios = listaEjercicios;
    }

    public void calcular(){

    }

    public void setListaEjercicios(ArrayList<Integer> listaEjercicios){
        this.listaEjercicios = listaEjercicios;
    }

    public void agregarEjercicio(Integer id_ejercicio){
        listaEjercicios.add(id_ejercicio);
    }

    public ArrayList<Integer> getListaEjercicios() {
        return listaEjercicios;
    }

    @NonNull
    @Override
    public String toString() {
        return "Rutina: " + listaEjercicios;
    }
}
