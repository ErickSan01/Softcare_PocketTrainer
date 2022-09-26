package com.softcare.pockettrainer.adminejercicios;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.softcare.pockettrainer.AyudanteBaseDeDatos;
import java.sql.Blob;
import java.util.ArrayList;


public class EjerciciosControlador {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "Ejercicios";

    public EjerciciosControlador (Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevoEjercicio(Ejercicio ejercicio){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", ejercicio.getNombre());
        valoresParaInsertar.put("descripcion", ejercicio.getDescripcion());
        valoresParaInsertar.put("set_imagenes", ejercicio.getIdMaterial());
        valoresParaInsertar.put("id_material",ejercicio.getIdMaterial());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<Ejercicio> obtenerEjercicio(){
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"nombre","descripcion","set_imagenes","id_material"};
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
            String descripcion = cursor.getString(2);
            String setImagenes = cursor.getString(3);

            int id_material = cursor.getInt(4);
            Ejercicio ejericio = new Ejercicio(idEjercicio, nombre,descripcion,setImagenes,id_material);
            ejercicios.add(ejericio);
        }while (cursor.moveToNext());
            cursor.close();
            return ejercicios;
        }
    public int guardarCambios(Ejercicio ejercicio) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", ejercicio.getNombre());
        valoresParaActualizar.put("descripcion", ejercicio.getDescripcion());
        valoresParaActualizar.put("set_imagenes", ejercicio.getIdMaterial());
        valoresParaActualizar.put("id_material",ejercicio.getIdMaterial());
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(ejercicio.getIdEjercicio())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarEjercicio(Ejercicio ejercicio) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(ejercicio.getIdEjercicio())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }
}



