package com.softcare.pockettrainer.adminbasededatos;

public class EjercicioImagenes {

    private int idImagen;
    private int idEjercicio;
    private String nombreImagen;

    public EjercicioImagenes(int idImagen, int idEjercicio, String nombreImagen) {
        this.idImagen = idImagen;
        this.idEjercicio = idEjercicio;
        this.nombreImagen = nombreImagen;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }
}
