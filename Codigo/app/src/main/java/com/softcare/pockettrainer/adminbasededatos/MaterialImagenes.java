package com.softcare.pockettrainer.adminbasededatos;

import java.util.ArrayList;
import java.util.List;

public class MaterialImagenes {
    private int idImagen;
    private int idMaterial;
    private String nombreImagen;

    public MaterialImagenes(int idImagen, int idMaterial, String nombreImagen) {
        this.idImagen = idImagen;
        this.idMaterial = idMaterial;
        this.nombreImagen = nombreImagen;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }
}
