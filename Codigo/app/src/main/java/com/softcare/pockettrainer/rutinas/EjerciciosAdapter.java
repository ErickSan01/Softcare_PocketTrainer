package com.softcare.pockettrainer.rutinas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softcare.pockettrainer.R;
import com.softcare.pockettrainer.TutorialActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EjerciciosAdapter extends RecyclerView.Adapter<EjerciciosAdapter.ViewHolder>{
    private final ArrayList<Ejercicio> listaEjercicios;
    Context context;

    public EjerciciosAdapter(ArrayList<Ejercicio> ejercicios, Context context) {
        this.listaEjercicios = ejercicios;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rutina_ejercicios_list, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        System.out.println(position);
        Ejercicio ejercicios = listaEjercicios.get(position);
        holder.textView.setText(ejercicios.getNombre());
        holder.cbSelect.setChecked(false);
        holder.cbSelect.setOnCheckedChangeListener(null);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), TutorialActivity.class);
                i.putExtra("ejercicio", ejercicios);
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
