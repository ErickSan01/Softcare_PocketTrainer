package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class SeleccionHorarioActivity extends AppCompatActivity {

    boolean prefLunes;
    boolean prefMartes;
    boolean prefMiercoles;
    boolean prefJueves;
    boolean prefViernes;
    boolean prefSabado;
    boolean prefDomingo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_horario);

        SharedPreferences preferencias = getSharedPreferences("dias", MODE_PRIVATE);
        prefLunes = preferencias.getBoolean("lunes", false);
        prefMartes = preferencias.getBoolean("martes", false);
        prefMiercoles = preferencias.getBoolean("miercoles", false);
        prefJueves = preferencias.getBoolean("jueves", false);
        prefViernes = preferencias.getBoolean("viernes", false);
        prefSabado = preferencias.getBoolean("sabado", false);
        prefDomingo = preferencias.getBoolean("domingo", false);

        View view = findViewById(R.id.checkBoxLunes);
        CheckBox box = (CheckBox) view;
        box.setChecked(prefLunes);
        View view1 = findViewById(R.id.checkBoxMartes);
        CheckBox box1 = (CheckBox) view1;
        box1.setChecked(prefMartes);
        View view2 = findViewById(R.id.checkBoxMiercoles);
        CheckBox box2 = (CheckBox) view2;
        box2.setChecked(prefMiercoles);
        View view3 = findViewById(R.id.checkBoxJueves);
        CheckBox box3 = (CheckBox) view3;
        box3.setChecked(prefJueves);
        View view4 = findViewById(R.id.checkBoxViernes);
        CheckBox box4 = (CheckBox) view4;
        box4.setChecked(prefViernes);
        View view5 = findViewById(R.id.checkBoxSabado);
        CheckBox box5 = (CheckBox) view5;
        box5.setChecked(prefSabado);
        View view6 = findViewById(R.id.checkBoxDomingo);
        CheckBox box6 = (CheckBox) view6;
        box6.setChecked(prefDomingo);

        Intent i = getIntent();
    }

    public void guardar(String campo, boolean valor){
        SharedPreferences preferencias = getSharedPreferences("dias", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean(campo,valor);

        editor.apply();
        finish();
    }

    public void Siguiente(View v){
        Intent i = new Intent(this, SeleccionTiempoActivity.class);

        View view = findViewById(R.id.checkBoxLunes);
        CheckBox box = (CheckBox) view;
        i.putExtra("Lunes", box.isChecked());
        guardar("lunes", box.isChecked());

        Log.d("CheckL", ""+box.isChecked());

        view = findViewById(R.id.checkBoxMartes);
        box = (CheckBox) view;
        i.putExtra("Martes", box.isChecked());
        guardar("martes", box.isChecked());

        Log.d("CheckMa", ""+box.isChecked());

        view = findViewById(R.id.checkBoxMiercoles);
        box = (CheckBox) view;
        i.putExtra("Miercoles", box.isChecked());
        guardar("miercoles", box.isChecked());

        Log.d("CheckMi", ""+box.isChecked());

        view = findViewById(R.id.checkBoxJueves);
        box = (CheckBox) view;
        i.putExtra("Jueves", box.isChecked());
        guardar("jueves", box.isChecked());

        Log.d("CheckJ", ""+box.isChecked());

        view = findViewById(R.id.checkBoxViernes);
        box = (CheckBox) view;
        i.putExtra("Viernes", box.isChecked());
        guardar("viernes", box.isChecked());

        Log.d("CheckV", ""+box.isChecked());

        view = findViewById(R.id.checkBoxSabado);
        box = (CheckBox) view;
        i.putExtra("Sabado", box.isChecked());
        guardar("sabado", box.isChecked());

        Log.d("CheckS", ""+box.isChecked());

        view = findViewById(R.id.checkBoxDomingo);
        box = (CheckBox) view;
        i.putExtra("Domingo", box.isChecked());
        guardar("domingo", box.isChecked());

        Log.d("CheckD", ""+box.isChecked());

        startActivity(i);

    }
}