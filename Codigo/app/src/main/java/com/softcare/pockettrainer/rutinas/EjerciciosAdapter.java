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
import com.softcare.pockettrainer.adminbasededatos.Ejercicio;
import com.softcare.pockettrainer.adminbasededatos.EjercicioPresentador;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramada;
import com.softcare.pockettrainer.adminbasededatos.RutinaProgramadaPresentador;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EjerciciosAdapter extends RecyclerView.Adapter<EjerciciosAdapter.ViewHolder>{
    Context context;
    ArrayList<Ejercicio> listaEjercicios;
    String dia;

    public EjerciciosAdapter(Context context, String dia) {
        this.context = context;
        this.dia = dia;
        this.listaEjercicios = getRutina();
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
        EjercicioPresentador ejercicios = new EjercicioPresentador(context);
        ArrayList<Ejercicio> ejerciciosBD = ejercicios.obtenerEjercicio(context);
        Ejercicio ejercicio = null;
        for (Ejercicio ejercicioBd: ejerciciosBD) {
            if(ejercicioBd.getIdEjercicio() == listaEjercicios.get(position).getIdEjercicio()){
                ejercicio = ejercicioBd;
            }
        }
        holder.textView.setText(ejercicio.getNombre());
        if(ejercicio.isTerminado()){
            holder.cbSelect.setChecked(true);
        }
        Ejercicio finalEjercicio = ejercicio;
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent i = new Intent(view.getContext(), TutorialActivity.class);
                i.putExtra("ejercicio", finalEjercicio);
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

    public ArrayList<Ejercicio> getRutina(){
        RutinaProgramadaPresentador rutinaProgramadaPresentador = new RutinaProgramadaPresentador(context);
        RutinaProgramada rutinaProgramada = rutinaProgramadaPresentador.obtenerRutinaProgramada().get(0);
        int idRutinaSeleccionada = 0;

        switch(dia){
            case("Lunes"):
                idRutinaSeleccionada = rutinaProgramada.getId_rutina_lunes();
                break;
            case("Martes"):
                idRutinaSeleccionada = rutinaProgramada.getId_rutina_martes();
                break;
            case("Miercoles"):
                idRutinaSeleccionada = rutinaProgramada.getId_rutina_miercoles();
                break;
            case("Jueves"):
                idRutinaSeleccionada = rutinaProgramada.getId_rutina_jueves();
                break;
            case("Viernes"):
                idRutinaSeleccionada = rutinaProgramada.getId_rutina_viernes();
                break;
            case("Sabado"):
                idRutinaSeleccionada = rutinaProgramada.getId_rutina_sabado();
                break;
            case("Domingo"):
                idRutinaSeleccionada = rutinaProgramada.getId_rutina_domingo();
                break;
        }

        EjercicioPresentador ejercicioPresentador = new EjercicioPresentador(context);

        System.out.println(ejercicioPresentador.obtenerEjercicio(context, idRutinaSeleccionada).get(0).getNombre());

        return ejercicioPresentador.obtenerEjercicio(context, idRutinaSeleccionada);
    }
}
