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

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener, ArrayList<Object> todosEjerciciosCompletados) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.todosEjerciciosCompletados = todosEjerciciosCompletados;
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
        String fecha = daysOfMonth.get(position);
        String dia = obtenerNumeroDia(fecha);
        holder.dayOfMonth.setText(dia);

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
