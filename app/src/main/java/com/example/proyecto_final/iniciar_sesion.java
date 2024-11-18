package com.example.proyecto_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                new String[]{DatabaseHelper.COLUMN_NOMBRE, DatabaseHelper.COLUMN_EMAIL, DatabaseHelper.COLUMN_FECHA_NACIMIENTO},
                DatabaseHelper.COLUMN_EMAIL + "=? AND " + DatabaseHelper.COLUMN_PASSWORD + "=?",
                new String[]{email, password},
                null, null, null
        );

        if (cursor.moveToFirst()) {

            String nombre = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE));
            String fechaNacimiento = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FECHA_NACIMIENTO));

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nombre", nombre);
            editor.putString("email", email);
            editor.putString("fechaNacimiento", fechaNacimiento);
            editor.putBoolean("isLoggedIn", true);
            editor.apply();

            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

}


