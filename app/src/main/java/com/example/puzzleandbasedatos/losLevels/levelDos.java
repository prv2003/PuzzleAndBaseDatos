package com.example.puzzleandbasedatos.losLevels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzleandbasedatos.BaseDatosHelper;
import com.example.puzzleandbasedatos.MainActivity;
import com.example.puzzleandbasedatos.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class levelDos extends AppCompatActivity {

    private static final int FILAS = 4;
    private static final int COLUMNAS = 4;
    private ImageButton[][] imageButtons;
    private ImageButton selectedImageButton;
    private int intentos = 0;
    private TextView textViewIntentos;
    private BaseDatosHelper dbHelper;
    private Button buttonFinalizar;

    private List<Integer> listaRecursosImagenes = Arrays.asList(
            R.drawable.jeepeta1, R.drawable.jeepeta2, R.drawable.jeepeta3, R.drawable.jeepeta4,
            R.drawable.jeepeta5, R.drawable.jeepeta6, R.drawable.jeepeta7, R.drawable.jeepeta8,
            R.drawable.jeepeta9, R.drawable.jeepeta10, R.drawable.jeepeta11, R.drawable.jeepeta12,
            R.drawable.jeepeta13, R.drawable.jeepeta14, R.drawable.jeepeta15, R.drawable.jeepeta16
    );

    private void shuffleImagesInGridLayout() {
        List<Integer> imagenesDesordenadas = new ArrayList<>(listaRecursosImagenes);
        Collections.shuffle(imagenesDesordenadas);

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                imageButtons[i][j].setImageResource(imagenesDesordenadas.get(i * COLUMNAS + j));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_two);
        buttonFinalizar = findViewById(R.id.endFinButton);
        buttonFinalizar.setOnClickListener(v -> onFinalizarClick());
        dbHelper = new BaseDatosHelper(this);
        GridLayout gridLayout = findViewById(R.id.gridLayoutLevelTwo);
        textViewIntentos = findViewById(R.id.intentosTV);
        imageButtons = new ImageButton[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                String buttonID = "imageButton" + ((i * COLUMNAS) + j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                imageButtons[i][j] = findViewById(resID);
                imageButtons[i][j].setOnClickListener(v -> onImageButtonClick((ImageButton) v));
            }
        }
        shuffleImagesInGridLayout();
        Intent intent = getIntent();
        String nombrePersona = intent.getStringExtra("nombrePersona");

        TextView textViewOrdena = findViewById(R.id.ordenarTV);
        String nombreJugador = getPlayerName();
        textViewOrdena.setText("Ordena el Puzzle " + nombreJugador);
    }

    private void onImageButtonClick(ImageButton clickedImageButton) {
        if (selectedImageButton == null) {
            selectedImageButton = clickedImageButton;
        } else {
            Drawable tempDrawable = selectedImageButton.getDrawable();
            selectedImageButton.setImageDrawable(clickedImageButton.getDrawable());
            clickedImageButton.setImageDrawable(tempDrawable);
            intentos++;

            selectedImageButton = null;
            updateIntentosCounter();
        }
    }

    private void updateIntentosCounter() {
        textViewIntentos.setText("Intentos: " + intentos);
    }

    private void onFinalizarClick() {
        String nombreJugador = getPlayerName();
        saveScore(nombreJugador);
        Toast.makeText(this, "Tu puntuación se ha guardado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(levelDos.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveScore(String nombreJugador) {
        if (dbHelper != null) {
            dbHelper.addScoreNivelDos(nombreJugador, intentos);
        }
    }

    private String getPlayerName() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("UsuarioPreferences", MODE_PRIVATE);
        return preferences.getString("nombreUsuario", "NombrePorDefecto");
    }
}
