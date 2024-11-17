package com.example.proyecto_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registro extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        dbHelper = new DatabaseHelper(this);

        EditText emailEditText = findViewById(R.id.email);
        EditText passwordEditText = findViewById(R.id.password);
        EditText nameEditText = findViewById(R.id.nombre);
        EditText fechaNacimientoEditText = findViewById(R.id.FechaN);
        CheckBox subscribeCheckbox = findViewById(R.id.subscribe_checkbox);
        Button crearCuentaButton = findViewById(R.id.crear_cuenta);
        TextView accederText = findViewById(R.id.acceder2);

        crearCuentaButton.setEnabled(false);

        subscribeCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> crearCuentaButton.setEnabled(isChecked));

        accederText.setOnClickListener(v -> {
            Intent intent = new Intent(registro.this, iniciar_sesion.class);
            startActivity(intent);
        });

        crearCuentaButton.setOnClickListener(v -> {
            String nombre = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String fechaNacimiento = fechaNacimientoEditText.getText().toString().trim();

            if (!nombre.isEmpty() && !email.isEmpty() && !password.isEmpty() && !fechaNacimiento.isEmpty()) {
                dbHelper.addUser(nombre, email, password, fechaNacimiento);
                Toast.makeText(registro.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(registro.this, iniciar_sesion.class);
                startActivity(intent);
            } else {
                Toast.makeText(registro.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
