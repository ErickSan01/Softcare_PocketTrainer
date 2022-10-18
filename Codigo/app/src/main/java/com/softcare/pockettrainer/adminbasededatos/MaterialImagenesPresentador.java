package com.softcare.pockettrainer.adminbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;

import java.util.ArrayList;

public class MaterialImagenesPresentador {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private final String NOMBRE_TABLA = "MaterialImagenes";

    public MaterialImagenesPresentador(Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevaImagen(MaterialImagenes materialImagenes){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_imagen", materialImagenes.getIdImagen());
        valoresParaInsertar.put("id_material", materialImagenes.getIdMaterial());
        valoresParaInsertar.put("nombre_imagen", materialImagenes.getNombreImagen());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<MaterialImagenes> obtenerImagen(){
        ArrayList<MaterialImagenes> imagenes = new ArrayList<MaterialImagenes>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_imagen","id_material","nombre_imagen"};
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
            int id_material = cursor.getInt(1);
            String nombre_imagen = cursor.getString(2);
            MaterialImagenes ei = new MaterialImagenes(id_imagen,id_material,nombre_imagen);
            imagenes.add(ei);
        }while (cursor.moveToNext());
        cursor.close();
        return imagenes;
    }

    public int eliminarImagen(MaterialImagenes materialImagenes) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(materialImagenes.getIdImagen())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }
}
