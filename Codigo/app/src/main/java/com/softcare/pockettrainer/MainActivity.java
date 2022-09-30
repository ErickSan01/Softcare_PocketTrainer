package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softcare.pockettrainer.rutinas.Rutinas;

public class MainActivity extends AppCompatActivity {
    private Button btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences datos = getSharedPreferences("primer_pantalla", MODE_PRIVATE);
        boolean tieneTodosLosDatos = datos.getBoolean("tiene_datos",false);
<<<<<<< HEAD

        if(!tieneTodosLosDatos) {
            Intent i = new Intent(this, PrimerPantallaActivity.class);
            startActivity(i);
        }

=======
>>>>>>> 6885a2c2ced94cc94d1bfb25fbe153ce21537377

        if(!tieneTodosLosDatos) {
            Intent i = new Intent(this, PrimerPantallaActivity.class);
            startActivity(i);
        }
        Button btn2 = (Button)findViewById(R.id.btnRutinas);
        Button btn3 = (Button)findViewById(R.id.btnAjustes);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Rutinas.class));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AjustesActivity.class));
            }
        });
    }
}