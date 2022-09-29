package com.softcare.pockettrainer.rutinas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.R;
import com.softcare.pockettrainer.SeleccionTiempoActivity;
import com.softcare.pockettrainer.TutorialActivity;

import java.util.ArrayList;

public class EjerciciosAdapter extends RecyclerView.Adapter<EjerciciosAdapter.ViewHolder>{
    private ArrayList<String> listaEjercicios;

    // RecyclerView recyclerView;
    public EjerciciosAdapter(ArrayList<String> ejercicios) {
        this.listaEjercicios = ejercicios;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rutina_ejercicios_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        System.out.println(position);
        String ejercicios = listaEjercicios.get(position);
        holder.textView.setText(listaEjercicios.get(position));
        holder.cbSelect.setChecked(false);
        holder.cbSelect.setOnCheckedChangeListener(null);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+ ejercicios,Toast.LENGTH_LONG).show();
                Intent i = new Intent(view.getContext(), TutorialActivity.class);
                view.getContext().startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaEjercicios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox cbSelect;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.cbSelect = (CheckBox) itemView.findViewById(R.id.ejercicioCompletado);
            this.textView = (TextView) itemView.findViewById(R.id.nombreEjercicio);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
