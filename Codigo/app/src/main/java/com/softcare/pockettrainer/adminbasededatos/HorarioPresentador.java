package com.softcare.pockettrainer.adminbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.softcare.pockettrainer.utilerias.DateToString;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;

import java.util.ArrayList;
import java.util.Date;

public class HorarioPresentador {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private final String NOMBRE_TABLA = "Material";

    public HorarioPresentador(Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevoMaterial(Horario horario){

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_horario", horario.getIdHorario());
        valoresParaInsertar.put("lunes", horario.getLunesHorarioDisponible().toString());
        valoresParaInsertar.put("martes", horario.getMartesHorarioDisponible().toString());
        valoresParaInsertar.put("miercoles", horario.getMiercolesHorarioDisponible().toString());
        valoresParaInsertar.put("jueves", horario.getJuevesHorarioDisponible().toString());
        valoresParaInsertar.put("viernes", horario.getViernesHorarioDisponible().toString());
        valoresParaInsertar.put("sadado", horario.getSabadoHorarioDisponible().toString());
        valoresParaInsertar.put("domingo", horario.getDomingoHorarioDisponible().toString());


        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<Horario> obtenerHorario(){
        ArrayList<Horario> horarios = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_horario", "lunes","martes","miercoles", "jueves", "viernes", "sabado", "domingo"};
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
            return horarios;
        }
        if(!cursor.moveToFirst()) return horarios;
        do{
            int idHorario = cursor.getInt(0);
            Date lunes = DateToString.cadenaAFecha(cursor.getString(1));
            Date martes = DateToString.cadenaAFecha(cursor.getString(2));
            Date miercoles = DateToString.cadenaAFecha(cursor.getString(3));
            Date jueves = DateToString.cadenaAFecha(cursor.getString(4));
            Date viernes = DateToString.cadenaAFecha(cursor.getString(5));
            Date sabado = DateToString.cadenaAFecha(cursor.getString(6));
            Date domingo = DateToString.cadenaAFecha(cursor.getString(7));

            Horario horario = new Horario(idHorario, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
            horarios.add(horario);
        }while (cursor.moveToNext());
        cursor.close();
        return horarios;
    }

    public int guardarCambios(Horario horario) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("lunes", horario.getLunesHorarioDisponible().toString());
        valoresParaActualizar.put("martes", horario.getMartesHorarioDisponible().toString());
        valoresParaActualizar.put("miercoles", horario.getMiercolesHorarioDisponible().toString());
        valoresParaActualizar.put("jueves", horario.getJuevesHorarioDisponible().toString());
        valoresParaActualizar.put("viernes", horario.getViernesHorarioDisponible().toString());
        valoresParaActualizar.put("sadado", horario.getSabadoHorarioDisponible().toString());
        valoresParaActualizar.put("domingo", horario.getDomingoHorarioDisponible().toString());
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(horario.getIdHorario())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarHorario(Horario horario) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(horario.getIdHorario())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }



}
