package com.softcare.pockettrainer.adminbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.softcare.pockettrainer.AyudanteBaseDeDatos;

import java.util.ArrayList;


public class EjercicioPresentador {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "Ejercicio";

    public EjercicioPresentador(Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevoEjercicio(Ejercicio ejercicio){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_ejercicio", ejercicio.getIdEjercicio());
        valoresParaInsertar.put("nombre", ejercicio.getNombre());
        valoresParaInsertar.put("terminado",ejercicio.isTerminado());
        valoresParaInsertar.put("precio",ejercicio.getPrecio());
        valoresParaInsertar.put("nivel",ejercicio.getNivel());
        valoresParaInsertar.put("parteCuerpo", ejercicio.getParteCuerpo());
        valoresParaInsertar.put("descripcion", ejercicio.getDescripcion());
        valoresParaInsertar.put("meta", ejercicio.getMeta());
        valoresParaInsertar.put("puntosEXP",ejercicio.getPuntosEXP());
        valoresParaInsertar.put("id_rutina",ejercicio.getIdRutina());
        valoresParaInsertar.put("id_material",ejercicio.getIdMaterial());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<Ejercicio> obtenerEjercicio(Context context){
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        AyudanteBaseDeDatos ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_ejercicio", "nombre", "terminado", "precio", "nivel", "descripcion", "parteCuerpo", "meta", "puntosEXP", "id_rutina", "id_material"};
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
            Boolean terminado = Integer.parseInt(cursor.getString(2)) == 1;
            int precio = cursor.getInt(3);
            String nivel = cursor.getString(4);
            String descripcion = cursor.getString(5);
            String parteCuerpo = cursor.getString(6);
            String meta = cursor.getString(7);
            int puntosExp = cursor.getInt(8);
            int id_rutina = cursor.getInt(9);
            int id_material = cursor.getInt(10);

            Ejercicio nuevoEjercicio = new Ejercicio(idEjercicio, nombre, terminado, precio, nivel, descripcion, parteCuerpo, meta, puntosExp, id_rutina, id_material);

            ejercicios.add(nuevoEjercicio);
        }while (cursor.moveToNext());
        cursor.close();
        return ejercicios;
    }

    public ArrayList<Ejercicio> obtenerEjercicio(Context context, int idRutina){
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        AyudanteBaseDeDatos ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_ejercicio", "nombre", "terminado", "precio", "nivel", "descripcion", "parteCuerpo", "meta", "puntosEXP", "id_rutina", "id_material"};
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
            int idEjercicio = cursor.getInt(0);
            String nombre = cursor.getString(1);
            Boolean terminado = Boolean.parseBoolean(cursor.getString(2));
            int precio = cursor.getInt(3);
            String nivel = cursor.getString(4);
            String descripcion = cursor.getString(5);
            String parteCuerpo = cursor.getString(6);
            String meta = cursor.getString(7);
            int puntosExp = cursor.getInt(8);
            int id_rutina = cursor.getInt(9);
            int id_material = cursor.getInt(10);

            Ejercicio nuevoEjercicio = new Ejercicio(idEjercicio, nombre, terminado, precio, nivel, descripcion, parteCuerpo, meta, puntosExp, id_rutina, id_material);

            ejercicios.add(nuevoEjercicio);
        }while (cursor.moveToNext());
        cursor.close();

        ArrayList<Ejercicio> ejerciciosReturn = new ArrayList<>();

        for (Ejercicio ejercicio : ejercicios) {
            if(ejercicio.getIdRutina() == idRutina){
                ejerciciosReturn.add(ejercicio);
            }
        }
        
        return ejerciciosReturn;
    }

    public int guardarCambios(Ejercicio ejercicio) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", ejercicio.getNombre());
        valoresParaActualizar.put("terminado",ejercicio.isTerminado());
        valoresParaActualizar.put("precio",ejercicio.getPrecio());
        valoresParaActualizar.put("nivel",ejercicio.getNivel());
        valoresParaActualizar.put("parteCuerpo", ejercicio.getParteCuerpo());
        valoresParaActualizar.put("descripcion", ejercicio.getDescripcion());
        valoresParaActualizar.put("meta", ejercicio.getMeta());
        valoresParaActualizar.put("puntosEXP",ejercicio.getPuntosEXP());
        valoresParaActualizar.put("id_rutina",ejercicio.getIdRutina());
        valoresParaActualizar.put("id_material",ejercicio.getIdMaterial());
        String campoParaActualizar = "id_ejercicio = ?";
        String[] argumentosParaActualizar = {String.valueOf(ejercicio.getIdEjercicio())};
        int result = baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
        baseDeDatos.close();
        return result;
    }


    public int eliminarEjercicio(Ejercicio ejercicio) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(ejercicio.getIdEjercicio())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }
}



