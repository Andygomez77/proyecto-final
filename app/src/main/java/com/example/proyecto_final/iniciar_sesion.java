package com.example.proyecto_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class iniciar_sesion extends AppCompatActivity {

    private EditText email, password;
    private Button acceder;
    private TextView crearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        acceder = findViewById(R.id.acceder);
        crearCuenta = findViewById(R.id.crear_cuenta);

        acceder.setOnClickListener(view -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            if (validarCredenciales(userEmail, userPassword)) {

                SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();


                Intent intent = new Intent(iniciar_sesion.this, FragmentActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });


        crearCuenta.setOnClickListener(view -> {
            Intent intent = new Intent(iniciar_sesion.this, registro.class);
            startActivity(intent);
        });
    }

    private boolean validarCredenciales(String email, String password) {

        return true;
    }
}



