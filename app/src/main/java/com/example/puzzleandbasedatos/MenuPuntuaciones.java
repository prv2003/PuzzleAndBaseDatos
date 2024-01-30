package com.example.puzzleandbasedatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzleandbasedatos.PuntosBaseDD.PuntosNvl1;
import com.example.puzzleandbasedatos.PuntosBaseDD.PuntosNvl2;

public class MenuPuntuaciones extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntos_menu);

        Button btnPuntuacionesNivelUno = findViewById(R.id.puntosNvl1Button);
        Button btnPuntuacionesNivelDos = findViewById(R.id.puntosNvl2Button);

        btnPuntuacionesNivelUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPuntuaciones.this, PuntosNvl1.class);
                startActivity(intent);
            }
        });

        btnPuntuacionesNivelDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPuntuaciones.this, PuntosNvl2.class);
                startActivity(intent);
            }
        });
        Button btnVolverMenu = findViewById(R.id.menuButton);
        btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPuntuaciones.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
