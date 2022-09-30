package com.softcare.pockettrainer.rutinas;

public class Ejercicio {
    private int id_ejercicio;
    private String nombre;
    private String categoria;
    private String imagen1, imagen2, imagen3;
    private String parteCuerpo;
    private String descripcion;
    private int id_material;


    public Ejercicio(int idE, String nombreE, String categoriaE, String imagenE, String parteCuerpoE, String descripcionE, int id_material){
        id_ejercicio = idE;
        nombre = nombreE;
        categoria = categoriaE;
        parteCuerpo = parteCuerpoE;
        imagen1 = imagenE;
        descripcion = descripcionE;
        this.id_material = id_material;
    }

    public Ejercicio(int idE, String nombreE, String categoriaE, String imagen1E, String imagen2E, String parteCuerpoE, String descripcionE, int id_material){
        id_ejercicio = idE;
        nombre = nombreE;
        categoria = categoriaE;
        parteCuerpo = parteCuerpoE;
        imagen1 = imagen1E;
        imagen2 = imagen2E;
        descripcion = descripcionE;
        this.id_material = id_material;
    }

    public Ejercicio(int idE, String nombreE, String categoriaE, String imagen1E, String imagen2E, String imagen3E, String parteCuerpoE, String descripcionE, int id_material){
        id_ejercicio = idE;
        nombre = nombreE;
        categoria = categoriaE;
        parteCuerpo = parteCuerpoE;
        imagen1 = imagen1E;
        imagen2 = imagen2E;
        imagen3 = imagen3E;
        descripcion = descripcionE;
        this.id_material = id_material;
    }


    public Ejercicio(int idE, String nombreE, String categoriaE, String imagenE, String parteCuerpoE, String descripcionE){
        id_ejercicio = idE;
        nombre = nombreE;
        categoria = categoriaE;
        parteCuerpo = parteCuerpoE;
        imagen1 = imagenE;
        descripcion = descripcionE;
    }

    public Ejercicio(int idE, String nombreE, String categoriaE, String imagen1E, String imagen2E, String parteCuerpoE, String descripcionE){
        id_ejercicio = idE;
        nombre = nombreE;
        categoria = categoriaE;
        parteCuerpo = parteCuerpoE;
        imagen1 = imagen1E;
        imagen2 = imagen2E;
        descripcion = descripcionE;
    }

    public Ejercicio(int idE, String nombreE, String categoriaE, String imagen1E, String imagen2E, String imagen3E, String parteCuerpoE, String descripcionE){
        id_ejercicio = idE;
        nombre = nombreE;
        categoria = categoriaE;
        parteCuerpo = parteCuerpoE;
        imagen1 = imagen1E;
        imagen2 = imagen2E;
        imagen3 = imagen3E;
        descripcion = descripcionE;
    }



    public int getId_ejercicio() {
        return id_ejercicio;
    }

    public void setId_ejercicio(int id_ejercicio) {
        this.id_ejercicio = id_ejercicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public String getImagen3() {
        return imagen3;
    }

    public void setImagen3(String imagen3) {
        this.imagen3 = imagen3;
    }

    public String getParteCuerpo() {
        return parteCuerpo;
    }

    public void setParteCuerpo(String parteCuerpo) {
        this.parteCuerpo = parteCuerpo;
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
