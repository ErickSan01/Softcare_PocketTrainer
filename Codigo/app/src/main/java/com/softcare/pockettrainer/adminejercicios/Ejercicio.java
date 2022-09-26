package com.softcare.pockettrainer.adminejercicios;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.sql.Blob;
public class Ejercicio {

    private int idEjercicio;
    private String nombre;
    private String descripcion;
    private String setImagenes;
    private int idMaterial;

    public Ejercicio(int idEjercicio, String nombre, String descripcion, String setImagenes, int idMaterial) {
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.setImagenes = setImagenes;
        this.idMaterial = idMaterial;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
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

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String toString(){
        return "id: "+idEjercicio+" nombre: "+nombre;
    }

}
