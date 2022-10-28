package com.softcare.pockettrainer.adminbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.softcare.pockettrainer.AyudanteBaseDeDatos;

import java.util.ArrayList;

public class UsuarioPresentador {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private final String NOMBRE_TABLA = "Usuario";

    public UsuarioPresentador(Context contexto){
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public long nuevoUsuario(Usuario usuario){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("id_usuario", usuario.getIdUsuario());
        valoresParaInsertar.put("meta", usuario.getMeta());
        valoresParaInsertar.put("tipo_cuerpo", usuario.getTipoCuerpo());
        valoresParaInsertar.put("id_rutinas_programadas", usuario.getIdRutinasProgramadas());
        valoresParaInsertar.put("id_horario", usuario.getIdHorario());
        valoresParaInsertar.put("exp", usuario.getExp());
        valoresParaInsertar.put("nivel", usuario.getNivel());
        valoresParaInsertar.put("ejercicios_completados",usuario.getEjerciciosCompletados());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public ArrayList<Usuario> obtenerUsuario(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnas = {"id_usuario", "meta","tipo_cuerpo","id_rutinas_programadas","id_horario","exp","nivel","ejercicios_completados"};
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
            return usuarios;
        }
        if(!cursor.moveToFirst()) return usuarios;
        do{
            int id_usuario = cursor.getInt(0);
            String meta = cursor.getString(1);
            String tipo_cuerpo = cursor.getString(2);
            int id_rutins_programadas = cursor.getInt(3);
            int id_horario = cursor.getInt(4);
            int exp = cursor.getInt(5);
            int nivel = cursor.getInt(6);
            int ejerciciosCompletados = cursor.getInt(7);
            Usuario usuario = new Usuario(id_usuario, meta, tipo_cuerpo,id_rutins_programadas,id_horario,exp,nivel,ejerciciosCompletados);
            usuarios.add(usuario);
        }while (cursor.moveToNext());
        cursor.close();
        return usuarios;
    }

    public int guardarCambios(Usuario usuario) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("meta", usuario.getMeta());
        valoresParaActualizar.put("tipo_cuerpo", usuario.getTipoCuerpo());
        valoresParaActualizar.put("id_rutinas_programadas", usuario.getIdRutinasProgramadas());
        valoresParaActualizar.put("id_horario", usuario.getIdHorario());
        valoresParaActualizar.put("exp", usuario.getExp());
        valoresParaActualizar.put("nivel", usuario.getNivel());
        valoresParaActualizar.put("ejercicios_completados", usuario.getEjerciciosCompletados());
        String campoParaActualizar = "id_usuario = ?";
        String[] argumentosParaActualizar = {String.valueOf(usuario.getIdUsuario())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

}
