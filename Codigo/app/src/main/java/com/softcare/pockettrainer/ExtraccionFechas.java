package com.softcare.pockettrainer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ExtraccionFechas extends AppCompatActivity {

    public ExtraccionFechas(CalendarViewHolder holder, int position, ArrayList<String> daysOfMonth){
        String fecha = daysOfMonth.get(position);
        String dia = obtenerNumeroDia(fecha);
        holder.dayOfMonth.setText(dia);
        if(!fecha.equalsIgnoreCase("")){
            if(ejercicioCompletado(fecha, getApplicationContext())){
                holder.dayOfMonth.setTextColor(Color.GREEN);
            }
        }
    }

    public boolean ejercicioCompletado(String fecha, Context context){
        SharedPreferences preferenciasFechas = context.getSharedPreferences("fechaEjercicio", MODE_PRIVATE);
        return preferenciasFechas.getBoolean(fecha, false);
    }

    public String obtenerNumeroDia(String fecha){
        if(fecha.isEmpty()){
            return "";
        }
        if(fecha.charAt(0) == '0'){
            return "" + fecha.charAt(1);
        }else{
            return fecha.substring(0,2);
        }
    }
}
