package com.softcare.pockettrainer.adminbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;

import java.util.ArrayList;

public class MaterialesPresentador {
    private  AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private final String NOMBRE_TABLA = "Material";

    public MaterialesPresentador(Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevoMaterial(Material material){

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_material", material.getIdMaterial());
        valoresParaInsertar.put("nombre", material.getNombre());
        valoresParaInsertar.put("descripcion", material.getDescripcion());

        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<Material> obtenerMaterial(){
        ArrayList<Material> materiales = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_material","nombre","descripcion"};
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
            return materiales;
        }
        if(!cursor.moveToFirst()) return materiales;
        do{
            int id_material = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String descripcion = cursor.getString(2);
            Material material = new Material(id_material, nombre,descripcion);
            materiales.add(material);
        }while (cursor.moveToNext());
        cursor.close();
        return materiales;
    }
    public int guardarCambios(Material material) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", material.getNombre());
        valoresParaActualizar.put("descripcion", material.getDescripcion());
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(material.getIdMaterial())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarMaterial(Material material) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(material.getIdMaterial())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id_material = ?", argumentos);
    }
}
