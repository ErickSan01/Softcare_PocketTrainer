package com.softcare.pockettrainer.rutinas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.R;
import com.softcare.pockettrainer.adminbasededatos.Rutina;

import java.util.ArrayList;

public class RutinasAdapter extends RecyclerView.Adapter<RutinasAdapter.ViewHolder>{
    private final ArrayList<String> dias;
    private final Context context;
    private final ArrayList<Rutina> rutinas;

    // RecyclerView recyclerView;
    public RutinasAdapter(ArrayList<String> listdata, ArrayList<Rutina> rutinas, Context context) {
        this.dias = listdata;
        this.context = context;
        this.rutinas = rutinas;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rutinas_list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String diasSeleccionados = dias.get(position);
        holder.textView.setText(dias.get(position) + "\t" + rutinas.get(position));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent ejerciciosRutina = new Intent(context, EjerciciosRutina.class);
                ejerciciosRutina.putExtra("dia", diasSeleccionados);
                context.startActivity(ejerciciosRutina);
            }
        });
    }


    @Override
    public int getItemCount(){
        return dias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}

