package com.softcare.pockettrainer.adminmateriales;

public class Material {

    private int idMaterial;
    private String nombre;
    private String descripcion;
    private String imagen1;
    private String imagen2;

    public Material(int idMaterial, String nombre, String descripcion, String imagen) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.descripcion = descripcion;
        imagen1 = imagen;
    }

    public Material(int idMaterial, String nombre, String descripcion, String imagen1, String imagen2) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
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

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    @Override
    public String toString(){
        return "id: "+idMaterial+" nombre: "+nombre;
    }

}

