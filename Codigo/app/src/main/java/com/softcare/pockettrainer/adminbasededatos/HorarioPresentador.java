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
        valoresParaInsertar.put("lunes", horario.getLunesHorarioDisponible());
        valoresParaInsertar.put("martes", horario.getMartesHorarioDisponible());
        valoresParaInsertar.put("miercoles", horario.getMiercolesHorarioDisponible());
        valoresParaInsertar.put("jueves", horario.getJuevesHorarioDisponible());
        valoresParaInsertar.put("viernes", horario.getViernesHorarioDisponible());
        valoresParaInsertar.put("sabado", horario.getSabadoHorarioDisponible());
        valoresParaInsertar.put("domingo", horario.getDomingoHorarioDisponible());


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
            String lunes = cursor.getString(1);
            String martes = cursor.getString(2);
            String miercoles = cursor.getString(3);
            String jueves = cursor.getString(4);
            String viernes = cursor.getString(5);
            String sabado = cursor.getString(6);
            String domingo = cursor.getString(7);

            String lunesFinal = "";
            String martesFinal = "";
            String miercolesFinal = "";
            String juevesFinal = "";
            String viernesFinal = "";
            String sabadoFinal = "";
            String domingoFinal = "";

            if(!lunes.isEmpty()){
                lunesFinal = lunes;
            } else {
                lunesFinal = null;
            }
            if(!martes.isEmpty()){
                martesFinal = martes;
            } else {
                martesFinal = null;
            }
            if(!miercoles.isEmpty()){
                miercolesFinal = miercoles;
            } else {
                miercolesFinal = null;
            }
            if(!jueves.isEmpty()){
                juevesFinal = jueves;
            } else {
                juevesFinal = null;
            }
            if(!viernes.isEmpty()){
                viernesFinal = viernes;
            } else {
                viernesFinal = null;
            }
            if(!sabado.isEmpty()){
                sabadoFinal = sabado;
            } else {
                sabadoFinal = null;
            }
            if(!domingo.isEmpty()){
                domingoFinal = domingo;
            } else {
                domingoFinal = null;
            }

            Horario horario = new Horario(idHorario, lunesFinal, martesFinal, miercolesFinal, juevesFinal, viernesFinal, sabadoFinal, domingoFinal);
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
