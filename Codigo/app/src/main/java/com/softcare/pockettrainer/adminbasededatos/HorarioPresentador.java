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
    private final String NOMBRE_TABLA = "Horario";

    public HorarioPresentador(Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevoHorario(Horario horario){

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_horario", horario.getIdHorario());
        valoresParaInsertar.put("lunes", String.valueOf(horario.getLunesHorarioDisponible()));
        valoresParaInsertar.put("martes", String.valueOf(horario.getMartesHorarioDisponible()));
        valoresParaInsertar.put("miercoles", String.valueOf(horario.getMiercolesHorarioDisponible()));
        valoresParaInsertar.put("jueves", String.valueOf(horario.getJuevesHorarioDisponible()));
        valoresParaInsertar.put("viernes", String.valueOf(horario.getViernesHorarioDisponible()));
        valoresParaInsertar.put("sabado", String.valueOf(horario.getSabadoHorarioDisponible()));
        valoresParaInsertar.put("domingo", String.valueOf(horario.getDomingoHorarioDisponible()));


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
            Date lunes = DateToString.cadenaAFecha(cursor.getString(1), "EEE MMM dd hh:mm:ss zzz yyyy");
            Date martes = DateToString.cadenaAFecha(cursor.getString(2), "EEE MMM dd hh:mm:ss zzz yyyy");
            Date miercoles = DateToString.cadenaAFecha(cursor.getString(3), "EEE MMM dd hh:mm:ss zzz yyyy");
            Date jueves = DateToString.cadenaAFecha(cursor.getString(4), "EEE MMM dd hh:mm:ss zzz yyyy");
            Date viernes = DateToString.cadenaAFecha(cursor.getString(5), "EEE MMM dd hh:mm:ss zzz yyyy");
            Date sabado = DateToString.cadenaAFecha(cursor.getString(6), "EEE MMM dd hh:mm:ss zzz yyyy");
            Date domingo = DateToString.cadenaAFecha(cursor.getString(7), "EEE MMM dd hh:mm:ss zzz yyyy");

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
        String campoParaActualizar = "id_horario = ?";
        String[] argumentosParaActualizar = {String.valueOf(horario.getIdHorario())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminarHorario(Horario horario) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(horario.getIdHorario())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id_horario = ?", argumentos);
    }



}
