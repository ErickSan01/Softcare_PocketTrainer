package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NivelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
        Button backBtn = (Button) findViewById(R.id.backBtnNivel);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void verNivel(View v){
        Intent img = new Intent(this, RecompensasActivity.class);
        startActivity(img);
    }
}