package com.softcare.pockettrainer.adminbasededatos;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.sql.Blob;
public class Ejercicio {

    private int idEjercicio;
    private String nombre;
    private boolean terminado;
    private int precio;
    private String nivel;
    private String descripcion;
    private String parteCuerpo;
    private String meta;
    private int puntosEXP;
    private int idRutina;
    private int idMaterial;

    public Ejercicio(int idEjercicio, String nombre, boolean terminado, int precio, String nivel, String descripcion, String parteCuerpo, String meta, int puntosEXP, int idRutina, int idMaterial) {
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.terminado = terminado;
        this.precio = precio;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.parteCuerpo = parteCuerpo;
        this.meta = meta;
        this.puntosEXP = puntosEXP;
        this.idRutina = idRutina;
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

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getParteCuerpo() {
        return parteCuerpo;
    }

    public void setParteCuerpo(String parteCuerpo) {
        this.parteCuerpo = parteCuerpo;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public int getPuntosEXP() {
        return puntosEXP;
    }

    public void setPuntosEXP(int puntosEXP) {
        this.puntosEXP = puntosEXP;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }
}