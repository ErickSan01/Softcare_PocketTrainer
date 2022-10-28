package com.softcare.pockettrainer.adminbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RutinaPresentador {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private final String NOMBRE_TABLA = "Rutina";

    public RutinaPresentador(Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevaRutina(Rutina rutina){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_rutina", rutina.getIdRutina());
        valoresParaInsertar.put("parte_cuerpo", rutina.getParteCuerpo());
        valoresParaInsertar.put("completada", rutina.isCompletada());
        valoresParaInsertar.put("puntosEXP", rutina.getPuntosEXP());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<Rutina> obtenerRutina(){
        ArrayList<Rutina> rutinas = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_rutina","parte_cuerpo","completada", "puntosEXP"};
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
            return rutinas;
        }
        if(!cursor.moveToFirst()) return rutinas;
        do{
            int id_rutina= cursor.getInt(0);
            String parte_cuerpo = cursor.getString(1);
            boolean completada = Boolean.parseBoolean(cursor.getString(2));
            int puntosEXP = cursor.getInt(3);
            Rutina rutina = new Rutina(id_rutina, parte_cuerpo, completada, puntosEXP);
            rutinas.add(rutina);
        }while (cursor.moveToNext());
        cursor.close();
        return rutinas;
    }

    public Rutina obtenerRutina(int idRutina){
        ArrayList<Rutina> rutinas = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_rutina","parte_cuerpo","completada", "puntosEXP"};
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
            return null;
        }
        if(!cursor.moveToFirst()) return null;
        do{
            int id_rutina= cursor.getInt(0);
            String parte_cuerpo = cursor.getString(1);
            boolean completada = Boolean.parseBoolean(cursor.getString(2));
            int puntosEXP = cursor.getInt(3);
            Rutina rutina = new Rutina(id_rutina, parte_cuerpo, completada, puntosEXP);
            rutinas.add(rutina);
        }while (cursor.moveToNext());
        cursor.close();

        for (Rutina rutina : rutinas) {
            if(rutina.getIdRutina() == idRutina){
                return rutina;
            }
        }

        return null;

    }

    public int guardarCambios(Rutina rutina) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("parte_cuerpo",rutina.getParteCuerpo());
        valoresParaActualizar.put("completada", rutina.getPuntosEXP());
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(rutina.getIdRutina())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarMaterial(Rutina rutina) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(rutina.getIdRutina())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }
}
