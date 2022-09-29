package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class SeleccionHorarioActivity extends AppCompatActivity {

    CheckBox boxLunes;
    CheckBox boxMartes;
    CheckBox boxMiercoles;
    CheckBox boxJueves;
    CheckBox boxViernes;
    CheckBox boxSabado;
    CheckBox boxDomingo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_horario);

        //Carga la preferencias para marcar los check box de acuerdo a ellas
        SharedPreferences preferencias = getSharedPreferences("dias", MODE_PRIVATE);
        boolean prefLunes = preferencias.getBoolean("lunes", false);
        boolean prefMartes = preferencias.getBoolean("martes", false);
        boolean prefMiercoles = preferencias.getBoolean("miercoles", false);
        boolean prefJueves = preferencias.getBoolean("jueves", false);
        boolean prefViernes = preferencias.getBoolean("viernes", false);
        boolean prefSabado = preferencias.getBoolean("sabado", false);
        boolean prefDomingo = preferencias.getBoolean("domingo", false);

        //Encuentra cada check box y los marca
        boxLunes = (CheckBox) findViewById(R.id.checkBoxLunes);
        boxLunes.setChecked(prefLunes);
        boxMartes = (CheckBox) findViewById(R.id.checkBoxMartes);
        boxMartes.setChecked(prefMartes);
        boxMiercoles = (CheckBox) findViewById(R.id.checkBoxMiercoles);
        boxMiercoles.setChecked(prefMiercoles);
        boxJueves = (CheckBox) findViewById(R.id.checkBoxJueves);
        boxJueves.setChecked(prefJueves);
        boxViernes = (CheckBox) findViewById(R.id.checkBoxViernes);
        boxViernes.setChecked(prefViernes);
        boxSabado = (CheckBox) findViewById(R.id.checkBoxSabado);
        boxSabado.setChecked(prefSabado);
        boxDomingo = (CheckBox) findViewById(R.id.checkBoxDomingo);
        boxDomingo.setChecked(prefDomingo);

        Intent i = getIntent();
    }

    //Metodo para guardar las selecciones del usuario
    public void guardar(String campo, boolean valor){
        SharedPreferences preferencias = getSharedPreferences("dias", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean(campo,valor);

        editor.apply();
        finish();
    }

    //Pasa los check box elegido por el usuario a través del intent y a la vez guarda la preferencia
    //To do sucede al pulsar el botón "Siguiente"
    public void Siguiente(View v){

        if(verificarCasillas()) {
            Intent i = new Intent(this, SeleccionTiempoActivity.class);

            i.putExtra("Lunes", boxLunes.isChecked());
            guardar("lunes", boxLunes.isChecked());

            Log.d("CheckL", "" + boxLunes.isChecked());

            i.putExtra("Martes", boxMartes.isChecked());
            guardar("martes", boxMartes.isChecked());

            Log.d("CheckMa", "" + boxMartes.isChecked());

            i.putExtra("Miercoles", boxMiercoles.isChecked());
            guardar("miercoles", boxMiercoles.isChecked());

            Log.d("CheckMi", "" + boxMiercoles.isChecked());

            i.putExtra("Jueves", boxJueves.isChecked());
            guardar("jueves", boxJueves.isChecked());

            Log.d("CheckJ", "" + boxJueves.isChecked());

            i.putExtra("Viernes", boxViernes.isChecked());
            guardar("viernes", boxViernes.isChecked());

            Log.d("CheckV", "" + boxViernes.isChecked());

            i.putExtra("Sabado", boxSabado.isChecked());
            guardar("sabado", boxSabado.isChecked());

            Log.d("CheckS", "" + boxSabado.isChecked());

            i.putExtra("Domingo", boxDomingo.isChecked());
            guardar("domingo", boxDomingo.isChecked());

            Log.d("CheckD", "" + boxDomingo.isChecked());

            startActivity(i);
        }else{
            Toast.makeText(this, "Selecciona al menos un día a la semana para ejercitarte", Toast.LENGTH_LONG).show();
        }
    }

    //Verifica si al menos una casilla fue marcada por el usuario, si es así, regresa true
    private boolean verificarCasillas(){
        return boxLunes.isChecked() || boxMartes.isChecked() || boxMiercoles.isChecked()
                || boxJueves.isChecked() || boxViernes.isChecked()
                || boxSabado.isChecked() || boxDomingo.isChecked();
    }
}