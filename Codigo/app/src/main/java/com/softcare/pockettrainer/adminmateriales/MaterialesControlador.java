package com.softcare.pockettrainer.adminmateriales;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;

import java.util.ArrayList;

public class MaterialesControlador {
    private  AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private final String NOMBRE_TABLA = "Materiales";

    public MaterialesControlador (Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevoEjercicio(Material material){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", material.getNombre());
        valoresParaInsertar.put("descripcion", material.getDescripcion());
        valoresParaInsertar.put("set_imagenes", material.getIdMaterial());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<Material> obtenermaterial(){
        ArrayList<Material> Materiales = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"nombre","descripcion","set_imagenes"};
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
            return Materiales;
        }
        if(!cursor.moveToFirst()) return Materiales;
        do{
            int id_material = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String descripcion = cursor.getString(2);
            String setImagenes = cursor.getString(3);
            Material material = new Material(id_material, nombre,descripcion,setImagenes);
            Materiales.add(material);
        }while (cursor.moveToNext());
        cursor.close();
        return Materiales;
    }
    public int guardarCambios(Material material) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", material.getNombre());
        valoresParaActualizar.put("descripcion", material.getDescripcion());
        valoresParaActualizar.put("set_imagenes", material.getIdMaterial());
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(material.getIdMaterial())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarmaterial(Material material) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(material.getIdMaterial())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }
}
