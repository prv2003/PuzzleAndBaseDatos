package com.example.puzzleandbasedatos.PuntosBaseDD;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzleandbasedatos.BaseDatosHelper;
import com.example.puzzleandbasedatos.MainActivity;
import com.example.puzzleandbasedatos.R;

public class PuntosNvl1 extends AppCompatActivity {

    private ListView listViewPuntuaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_one_points);
        listViewPuntuaciones = findViewById(R.id.puntuacionesListaUno);
        BaseDatosHelper dbHelper = new BaseDatosHelper(this);

        Cursor cursor = dbHelper.getAllScoresNivelUno();

        String[] fromColumns = {"jugador", "menor_puntuacion"};
        int[] toViews = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                fromColumns,
                toViews,
                0
        );

        listViewPuntuaciones.setAdapter(adapter);
        Button btnVolverMenu = findViewById(R.id.menuButtonAtras);
        btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PuntosNvl1.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
