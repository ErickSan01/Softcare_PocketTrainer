package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class SeleccionHorarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_horario);
        Intent i = getIntent();
    }

    public void Siguiente(View v){
        Intent i = new Intent(this, SeleccionTiempoActivity.class);

        View view = findViewById(R.id.checkBoxLunes);
        CheckBox box = (CheckBox) view;
        i.putExtra("Lunes", box.isChecked());

        Log.d("CheckL", ""+box.isChecked());

        view = findViewById(R.id.checkBoxMartes);
        box = (CheckBox) view;
        i.putExtra("Martes", box.isChecked());

        Log.d("CheckMa", ""+box.isChecked());

        view = findViewById(R.id.checkBoxMiercoles);
        box = (CheckBox) view;
        i.putExtra("Miercoles", box.isChecked());

        Log.d("CheckMi", ""+box.isChecked());

        view = findViewById(R.id.checkBoxJueves);
        box = (CheckBox) view;
        i.putExtra("Jueves", box.isChecked());

        Log.d("CheckJ", ""+box.isChecked());

        view = findViewById(R.id.checkBoxViernes);
        box = (CheckBox) view;
        i.putExtra("Viernes", box.isChecked());

        Log.d("CheckV", ""+box.isChecked());

        view = findViewById(R.id.checkBoxSabado);
        box = (CheckBox) view;
        i.putExtra("Sabado", box.isChecked());

        Log.d("CheckS", ""+box.isChecked());

        view = findViewById(R.id.checkBoxDomingo);
        box = (CheckBox) view;
        i.putExtra("Domingo", box.isChecked());

        Log.d("CheckD", ""+box.isChecked());


        startActivity(i);
    }
}