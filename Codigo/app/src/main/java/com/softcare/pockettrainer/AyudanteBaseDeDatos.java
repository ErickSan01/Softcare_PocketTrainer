package com.softcare.pockettrainer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;
import java.sql.Connection;


public class AyudanteBaseDeDatos extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "pocket_trainer_bd",
            NOMBRE_TABLA_EJERCICIOS = "Ejercicios",
            NOMBRE_TABLA_MATERIALES = "Materiales";
    public static final int VERSISON_BASE_DE_DATOS =  1;

    public AyudanteBaseDeDatos(Context context){
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSISON_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Database");

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id_material int primary key, nombre text, descripcion text, set_imagenes blob)", NOMBRE_TABLA_MATERIALES));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id_ejercicio int primary key, nombre text, descripcion text, set_imagenes blob, id_material int," +
                "FOREIGN KEY (id_material) REFERENCES Materiales(id_material))", NOMBRE_TABLA_EJERCICIOS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void agregarEjercicio(int id_ejercicio, String nombreEjercicio, String descripcion, byte[] imagen, int id_material){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("id_ejercicio", id_ejercicio);
        values.put("nombre", nombreEjercicio);
        values.put("descripcion", descripcion);
        values.put("set_imagenes", imagen);
        values.put("id_material", id_material);

        db.execSQL("INSERT INTO Ejercicios(id_ejercicio, nombre, descripcion, id_material)" +
                " VALUES (?, ?, ?, ?)");

    }

}
