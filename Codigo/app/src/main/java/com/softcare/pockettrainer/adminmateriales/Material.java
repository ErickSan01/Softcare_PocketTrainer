package com.softcare.pockettrainer.adminmateriales;

public class Material {

    private int id_material;
    private String nombre;
    private String descripcion;
    private String set_imagenes;

    public Material(int id_material, String nombre, String descripcion, String set_imagenes) {
        this.id_material = id_material;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.set_imagenes = set_imagenes;
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
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

    @Override
    public String toString(){
        return "id: "+id_material+" nombre: "+nombre;
    }

}

