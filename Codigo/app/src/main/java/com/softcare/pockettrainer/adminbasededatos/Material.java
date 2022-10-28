package com.softcare.pockettrainer.adminbasededatos;

import java.util.ArrayList;
import java.util.List;

public class Material{

    private int idMaterial;
    private String nombre;
    private String descripcion;

    public Material(int idMaterial, String nombre, String descripcion) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<MaterialImagenes> obtenerImagenesPropias(ArrayList<MaterialImagenes> imagenes){
        List<MaterialImagenes> imagenesP = new ArrayList<>();

        for (MaterialImagenes imagen : imagenes) {
            if(imagen.getIdMaterial() == idMaterial){
                boolean estaDentro = false;
                for (MaterialImagenes imagen2: imagenesP) {
                    if(imagen.getIdImagen() == imagen2.getIdImagen()){
                        estaDentro = true;
                    }
                }
                if(!estaDentro){
                    imagenesP.add(imagen);
                }
            }
        }

        return imagenesP;
    }
}

