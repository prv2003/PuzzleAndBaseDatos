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

public class levelUno extends AppCompatActivity {

    private static final int FILAS = 2;
    private static final int COLUMNAS = 4;
    private ImageButton[][] imageButtons;
    private ImageButton selectedImageButton;
    private int intentos = 0;
    private TextView textViewIntentos;
    private BaseDatosHelper dbHelper;
    private Button buttonFinalizar;

    private List<Integer> listaRecursosImagenes = Arrays.asList(
            R.drawable.imagen1,
            R.drawable.imagen2,
            R.drawable.imagen3,
            R.drawable.imagen4,
            R.drawable.imagen5,
            R.drawable.imagen6,
            R.drawable.imagen7,
            R.drawable.imagen8
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_one);
        buttonFinalizar = findViewById(R.id.stopFinButton);
        buttonFinalizar.setOnClickListener(v -> onFinalizarClick());
        dbHelper = new BaseDatosHelper(this);
        GridLayout gridLayout = findViewById(R.id.gridLayoutLevelOne);
        textViewIntentos = findViewById(R.id.movimientosTV);
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
        Intent intent = new Intent(levelUno.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveScore(String nombreJugador) {

        if (dbHelper != null) {

            dbHelper.addScoreNivelUno(nombreJugador, intentos);
        }
    }

    private String getPlayerName() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("UsuarioPreferences", MODE_PRIVATE);
        return preferences.getString("nombreUsuario", "NombrePorDefecto");
    }


}
