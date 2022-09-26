package com.softcare.pockettrainer.adminmateriales;

public class Material {

    private int idMaterial;
    private String nombre;
    private String descripcion;
    private String setImagenes;

    public Material(int idMaterial, String nombre, String descripcion, String setImagenes) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.setImagenes = setImagenes;
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

    public String getSetImagenes() {
        return setImagenes;
    }

    public void setSetImagenes(String setImagenes) {
        this.setImagenes = setImagenes;
    }

    @Override
    public String toString(){
        return "id: "+idMaterial+" nombre: "+nombre;
    }

}

