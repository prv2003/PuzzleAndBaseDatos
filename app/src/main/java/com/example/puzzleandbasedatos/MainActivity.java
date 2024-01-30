package com.example.puzzleandbasedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editNombre;
    private MediaPlayer mediaPlayer;
    private boolean isMusicPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.musica_titanium);
        editNombre = findViewById(R.id.nombreET);
        Button btnEmpezar = findViewById(R.id.btnStart);
        Button btnPuntuaciones = findViewById(R.id.btnPuntos);

        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = (editNombre != null) ? editNombre.getText().toString() : "";

                if (!nombre.isEmpty()) {
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("UsuarioPreferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("nombreUsuario", nombre);
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, ElegirNivel.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, ingrese su nombre", Toast.LENGTH_SHORT).show();
                }

                toggleMusic();
            }
        });

        btnPuntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuPuntuaciones.class);
                startActivity(intent);

                toggleMusic();
            }
        });
    }

    private void toggleMusic() {
        if (isMusicPlaying) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }

        isMusicPlaying = !isMusicPlaying;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
