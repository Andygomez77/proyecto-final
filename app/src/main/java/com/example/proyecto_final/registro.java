package com.example.proyecto_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        TextView accederText = findViewById(R.id.acceder2);
        Button crearCuentaButton = findViewById(R.id.crear_cuenta);
        CheckBox subscribeCheckbox = findViewById(R.id.subscribe_checkbox);


        crearCuentaButton.setEnabled(false);


        subscribeCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            crearCuentaButton.setEnabled(isChecked);
        });


        accederText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registro.this, iniciar_sesion.class);
                startActivity(intent);
            }
        });
    }
}
