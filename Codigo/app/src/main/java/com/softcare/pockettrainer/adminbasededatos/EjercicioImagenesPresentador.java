package com.softcare.pockettrainer.adminbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;

import java.util.ArrayList;

public class EjercicioImagenesPresentador {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private final String NOMBRE_TABLA = "EjercicioImagenes";

    public EjercicioImagenesPresentador(Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevaImagen(EjercicioImagenes ejercicioImagenes){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_imagen", ejercicioImagenes.getIdImagen());
        valoresParaInsertar.put("id_ejercicio", ejercicioImagenes.getIdEjercicio());
        valoresParaInsertar.put("nombre_imagen", ejercicioImagenes.getNombreImagen());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<EjercicioImagenes> obtenerImagen(){
        ArrayList<EjercicioImagenes> imagenes = new ArrayList<EjercicioImagenes>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_imagen","id_ejercicio","nombre_imagen"};
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
            return imagenes;
        }
        if(!cursor.moveToFirst()) return imagenes;
        do{
            int id_imagen = cursor.getInt(0);
            int id_ejercicio = cursor.getInt(1);
            String nombre_imagen = cursor.getString(2);
            EjercicioImagenes ei = new EjercicioImagenes(id_imagen,id_ejercicio,nombre_imagen);
            imagenes.add(ei);
        }while (cursor.moveToNext());
        cursor.close();
        return imagenes;
    }

    public int eliminarImagen(EjercicioImagenes ejercicioImagenes) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(ejercicioImagenes.getIdImagen())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id_imagen = ?", argumentos);
    }
}
