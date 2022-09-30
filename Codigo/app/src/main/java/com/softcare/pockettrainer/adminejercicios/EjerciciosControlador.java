package com.softcare.pockettrainer.adminejercicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.softcare.pockettrainer.AyudanteBaseDeDatos;
import java.sql.Blob;
import java.util.ArrayList;
import com.softcare.pockettrainer.rutinas.Ejercicio;


public class EjerciciosControlador {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "Ejercicios";

    public EjerciciosControlador (Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevoEjercicio(Ejercicio ejercicio){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_ejercicio", ejercicio.getId_ejercicio());
        valoresParaInsertar.put("nombre", ejercicio.getNombre());
        valoresParaInsertar.put("categoria", ejercicio.getCategoria());
        valoresParaInsertar.put("imagen1", ejercicio.getImagen1());
        valoresParaInsertar.put("imagen2", ejercicio.getImagen2());
        valoresParaInsertar.put("imagen3", ejercicio.getImagen3());
        valoresParaInsertar.put("parteCuerpo", ejercicio.getParteCuerpo());
        valoresParaInsertar.put("descripcion", ejercicio.getDescripcion());
        valoresParaInsertar.put("id_material",ejercicio.getId_material());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<Ejercicio> obtenerEjercicio(){
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_ejercicio","nombre","categoria","imagen1", "imagen2", "imagen3", "parteCuerpo", "descripcion", "id_material"};
        Cursor cursor = sqLiteDatabase.query(
                NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
                );
        if(cursor == null){
            return ejercicios;
        }
        if(!cursor.moveToFirst()) return ejercicios;
        do{
            int idEjercicio = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String categoria = cursor.getString(2);
            String imagen1 = cursor.getString(4);
            String imagen2 = cursor.getString(5);
            String imagen3 = cursor.getString(6);
            String parteCuerpo = cursor.getString(7);
            String descripcion = cursor.getString(3);
            int id_material = cursor.getInt(8);

            Ejercicio nuevoEjercicio = null;

            if(imagen3 == null){
                if (imagen2 == null){
                    nuevoEjercicio = new Ejercicio(idEjercicio, nombre, categoria, descripcion, imagen1, parteCuerpo, id_material);
                } else {
                    nuevoEjercicio = new Ejercicio(idEjercicio, nombre, categoria, descripcion, imagen1, imagen2, parteCuerpo, id_material);
                }
            } else {
                nuevoEjercicio = new Ejercicio(idEjercicio, nombre, categoria, descripcion, imagen1, imagen2, imagen3, parteCuerpo, id_material);
            }

            ejercicios.add(nuevoEjercicio);
        }while (cursor.moveToNext());
        cursor.close();
        return ejercicios;
    }
    public int guardarCambios(Ejercicio ejercicio) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("id_ejercicio", ejercicio.getId_ejercicio());
        valoresParaActualizar.put("nombre", ejercicio.getNombre());
        valoresParaActualizar.put("categoria", ejercicio.getCategoria());
        valoresParaActualizar.put("imagen1", ejercicio.getImagen1());
        valoresParaActualizar.put("imagen2", ejercicio.getImagen2());
        valoresParaActualizar.put("imagen3", ejercicio.getImagen3());
        valoresParaActualizar.put("parteCuerpo", ejercicio.getParteCuerpo());
        valoresParaActualizar.put("descripcion", ejercicio.getDescripcion());
        valoresParaActualizar.put("id_material",ejercicio.getId_material());
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(ejercicio.getId_ejercicio())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarEjercicio(Ejercicio ejercicio) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(ejercicio.getId_ejercicio())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }
}



