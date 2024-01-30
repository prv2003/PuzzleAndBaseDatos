package com.example.puzzleandbasedatos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzleandbasedatos.losLevels.levelUno;
import com.example.puzzleandbasedatos.losLevels.levelDos;

public class ElegirNivel extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elegir_niveles);

        Button btnNivelUno = findViewById(R.id.levelOneButton);
        Button btnNivelDos = findViewById(R.id.levelTwoButton);

        btnNivelUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElegirNivel.this, levelUno.class);
                startActivity(intent);
            }
        });

        btnNivelDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElegirNivel.this, levelDos.class);
                startActivity(intent);
            }
        });
        Button btnVolverMenu = findViewById(R.id.menuButton);
        btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElegirNivel.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}