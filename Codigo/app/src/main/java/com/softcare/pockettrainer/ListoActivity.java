package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softcare.pockettrainer.rutinas.Rutinas;

public class ListoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listo);


        Button buttonComenzar = (Button) findViewById(R.id.buttonComenzar);

        buttonComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rutina = new Intent(ListoActivity.this, Rutinas.class);
                startActivity(rutina);
            }
        });
    }
}