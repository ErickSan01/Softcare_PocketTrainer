package com.softcare.pockettrainer.adminejercicios;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.sql.Blob;
public class Ejercicio {

    private int id_ejercicio;
    private String nombre;
    private String descripcion;
    private String set_imagenes;
    private int id_material;

    public Ejercicio(int id_ejercicio, String nombre, String descripcion, String set_imagenes, int id_material) {
        this.id_ejercicio = id_ejercicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.set_imagenes = set_imagenes;
        this.id_material = id_material;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }
    @Override
    public String toString(){
        return "id: "+id_ejercicio+" nombre: "+nombre;
    }

}
