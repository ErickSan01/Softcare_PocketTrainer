package com.softcare.pockettrainer;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private final ArrayList<Object> todosEjerciciosCompletados;
    private final ArrayList<Object> listaDiasSeleccionados;
    private final String fechaActual;
    private String fecha;

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener, ArrayList<Object> todosEjerciciosCompletados, ArrayList<Object> listaDiasSeleccionados, String fechaActual) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.todosEjerciciosCompletados = todosEjerciciosCompletados;
        this.listaDiasSeleccionados = listaDiasSeleccionados;
        this.fechaActual = fechaActual;
        this.fecha = "";
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        System.out.println(listaDiasSeleccionados);
        fecha = daysOfMonth.get(position);
        System.out.println("fechaactual "+fechaActual);
        System.out.println("fecharandom "+fecha);
        String dia = obtenerNumeroDia(fecha);

        holder.dayOfMonth.setText(dia);

        if(esFechaFutura(fecha)){
            if(esPosicionCorrecta(position)){
                holder.dayOfMonth.setTextColor(Color.YELLOW);
            }
        }
        if(!fecha.equalsIgnoreCase("")){
            if(todosEjerciciosCompletados.contains(fecha)){
                holder.dayOfMonth.setTextColor(Color.GREEN);
            }
        }
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }

    private String obtenerNumeroDia(String fecha){
        if(fecha.isEmpty()){
            return "";
        }
        if(fecha.charAt(0) == '0'){
            return "" + fecha.charAt(1);
        }else{
            return fecha.substring(0,2);
        }
    }

    private String obtenerNumeroMes(String fecha){
        if(fecha.isEmpty()){
            return "";
        }
        if(fecha.charAt(3) == '0'){
            return "" + fecha.charAt(4);
        }else{
            return fecha.substring(3,5);
        }
    }

    private String obtenerNumeroAno(String fecha){
        if(fecha.isEmpty()){
            return "";
        }else{
            return fecha.substring(6);
        }
    }

    //Si la fecha dada es mayor o exactamente igual a la fecha actual del dispositivo regresa true
    //En cualquier otro caso regresa false
    private boolean esFechaFutura(String fecha){
        if(!fecha.equalsIgnoreCase("") && !fechaActual.equalsIgnoreCase("")) {
            int diaActual = Integer.parseInt(obtenerNumeroDia(fechaActual));
            int mesActual = Integer.parseInt(obtenerNumeroMes(fechaActual));
            int anoActual = Integer.parseInt(obtenerNumeroAno(fechaActual));

            int diaFecha = Integer.parseInt(obtenerNumeroDia(fecha));
            int mesFecha = Integer.parseInt(obtenerNumeroMes(fecha));
            int anoFecha = Integer.parseInt(obtenerNumeroAno(fecha));

            if(anoActual == anoFecha && mesActual == mesFecha && diaActual == diaFecha){
                return true;
            }
            if(anoActual < anoFecha){
                return true;
            }
            if(anoActual == anoFecha && mesActual < mesFecha && diaActual < diaFecha) {
                return true;
            }
            if(anoActual == anoFecha && mesActual == mesFecha && diaActual < diaFecha) {
                return true;
            }
            if(anoActual == anoFecha && mesActual < mesFecha && diaActual > diaFecha) {
                return true;
            }
            return false;
        }else{
            return false;
        }
    }

    private boolean esPosicionCorrecta(int position){
        if(listaDiasSeleccionados.contains("domingo")){
            if(position == 0 || position == 7 || position == 14 || position == 21 || position == 28 || position == 35){
                return true;
            }
        }
        if(listaDiasSeleccionados.contains("lunes")){
            if(position == 1 || position == 8 || position == 15 || position == 22 || position == 29 || position == 36){
                return true;
            }
        }
        if(listaDiasSeleccionados.contains("martes")){
            if(position == 2 || position == 9 || position == 16 || position == 23 || position == 30 || position == 37){
                return true;
            }
        }
        if(listaDiasSeleccionados.contains("miercoles")){
            if(position == 3 || position == 10 || position == 17 || position == 24 || position == 31 || position == 38){
                return true;
            }
        }
        if(listaDiasSeleccionados.contains("jueves")){
            if(position == 4 || position == 11 || position == 18 || position == 25 || position == 32 || position == 39){
                return true;
            }
        }
        if(listaDiasSeleccionados.contains("viernes")){
            if(position == 5 || position == 12 || position == 19 || position == 26 || position == 33 || position == 40){
                return true;
            }
        }
        if(listaDiasSeleccionados.contains("sabado")){
            if(position == 6 || position == 13 || position == 20 || position == 27 || position == 33 || position == 41){
                return true;
            }
        }
        return false;
    }
}
