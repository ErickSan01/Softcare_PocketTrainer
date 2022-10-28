package com.softcare.pockettrainer.adminbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;

import java.util.ArrayList;

public class RutinaProgramadaPresentador {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private final String NOMBRE_TABLA = "RutinasProgramadas";

    public RutinaProgramadaPresentador(Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevaRutinaProgramada(RutinaProgramada rutinaProgramada){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_rutina_programada", rutinaProgramada.getId_rutina_programada());
        valoresParaInsertar.put("id_rutina_lunes", rutinaProgramada.getId_rutina_lunes());
        valoresParaInsertar.put("id_rutina_martes",rutinaProgramada.getId_rutina_martes());
        valoresParaInsertar.put("id_rutina_miercoles",rutinaProgramada.getId_rutina_miercoles());
        valoresParaInsertar.put("id_rutina_jueves",rutinaProgramada.getId_rutina_jueves());
        valoresParaInsertar.put("id_rutina_viernes",rutinaProgramada.getId_rutina_viernes());
        valoresParaInsertar.put("id_rutina_sabado",rutinaProgramada.getId_rutina_sabado());
        valoresParaInsertar.put("id_rutina_domingo",rutinaProgramada.getId_rutina_domingo());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<RutinaProgramada> obtenerRutinaProgramada(){
        ArrayList<RutinaProgramada> rutinaProgramadas = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_rutina_programada","id_rutina_lunes", "id_rutina_martes", "id_rutina_miercoles", "id_rutina_jueves", "id_rutina_viernes", "id_rutina_sabado", "id_rutina_domingo"};
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
            return rutinaProgramadas;
        }
        if(!cursor.moveToFirst()) return rutinaProgramadas;
        do{
            int id_rutina_programado = cursor.getInt(0);
            int id_rutina_lunes = cursor.getInt(1);
            int id_rutina_martes = cursor.getInt(2);
            int id_rutina_miercoles = cursor.getInt(3);
            int id_rutina_jueves = cursor.getInt(4);
            int id_rutina_viernes = cursor.getInt(5);
            int id_rutina_sabado = cursor.getInt(6);
            int id_rutina_domingo = cursor.getInt(7);
            RutinaProgramada rutinaProgramada = new RutinaProgramada(id_rutina_programado, id_rutina_lunes, id_rutina_martes, id_rutina_miercoles, id_rutina_jueves, id_rutina_viernes, id_rutina_sabado, id_rutina_domingo);
            rutinaProgramadas.add(rutinaProgramada);
        }while (cursor.moveToNext());
        cursor.close();
        return rutinaProgramadas;
    }

    public int guardarCambios(RutinaProgramada rutinaProgramada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("id_rutina_lunes", rutinaProgramada.getId_rutina_lunes());
        valoresParaActualizar.put("id_rutina_martes",rutinaProgramada.getId_rutina_martes());
        valoresParaActualizar.put("id_rutina_miercoles",rutinaProgramada.getId_rutina_miercoles());
        valoresParaActualizar.put("id_rutina_jueves",rutinaProgramada.getId_rutina_jueves());
        valoresParaActualizar.put("id_rutina_viernes",rutinaProgramada.getId_rutina_viernes());
        valoresParaActualizar.put("id_rutina_sabado",rutinaProgramada.getId_rutina_sabado());
        valoresParaActualizar.put("id_rutina_domingo",rutinaProgramada.getId_rutina_domingo());
        String campoParaActualizar = "id_rutina_programada = ?";
        String[] argumentosParaActualizar = {String.valueOf(rutinaProgramada.getId_rutina_domingo())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarRutinaProgramada(RutinaProgramada rutinaProgramada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(rutinaProgramada.getId_rutina_programada())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id_rutina_programada = ?", argumentos);
    }

}
