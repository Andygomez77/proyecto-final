package com.example.proyecto_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button accederButton;
    private TextView crearCuentaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {

            Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
            startActivity(intent);
            finish();
        } else {

            setContentView(R.layout.activity_main);


            accederButton = findViewById(R.id.acceder);
            crearCuentaText = findViewById(R.id.crear_cuenta);


            accederButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, iniciar_sesion.class);
                    startActivity(intent);
                }
            });


            crearCuentaText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, registro.class);
                    startActivity(intent);
                }
            });
        }
    }
}

