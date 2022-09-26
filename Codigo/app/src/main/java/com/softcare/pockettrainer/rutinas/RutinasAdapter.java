package com.softcare.pockettrainer.rutinas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.R;

public class RutinasAdapter extends RecyclerView.Adapter<RutinasAdapter.ViewHolder>{
    private String[] dias;
    private Context context;

    // RecyclerView recyclerView;
    public RutinasAdapter(String[] listdata, Context context) {
        this.dias = listdata;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rutinas_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String diasSeleccionados = dias[position];
        holder.textView.setText(dias[position]);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent ejerciciosRutina = new Intent(context, EjerciciosRutina.class);
                context.startActivity(ejerciciosRutina);
            }
        });
    }


    @Override
    public int getItemCount(){
        return dias.length;
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

