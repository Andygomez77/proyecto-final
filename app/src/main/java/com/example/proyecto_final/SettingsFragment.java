package com.example.proyecto_final;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.proyecto_final.clases.CircleTransform;
import com.squareup.picasso.Picasso;


public class SettingsFragment extends Fragment {

    private TextView nombreTextView, correoTextView, fechaNacimientoTextView;
    private Button cerrarSesionButton ;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        cerrarSesionButton = view.findViewById(R.id.btncerrar);
        nombreTextView = view.findViewById(R.id.nombrec);
        correoTextView = view.findViewById(R.id.correoc);
        fechaNacimientoTextView = view.findViewById(R.id.fechanacimientoc);

       imageView = view.findViewById(R.id.imageview);

        cargarDatosUsuario();

        cerrarSesionButton.setOnClickListener(v -> cerrarSesion());

        Picasso.get()
                .load("https://media.istockphoto.com/id/2151669146/es/vector/ilustraci%C3%B3n-plana-vectorial-en-escala-de-grises-avatar-perfil-de-usuario-icono-de-persona.jpg?s=612x612&w=0&k=20&c=VZrDLt0v73Moa9Oexnx7_i9kmti9KEioResHUXzauys=")
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .transform(new CircleTransform())
                .into(imageView);


        return view;
    }

    private void cargarDatosUsuario() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        String nombre = sharedPreferences.getString("nombre", "N/A");
        String email = sharedPreferences.getString("email", "N/A");
        String fechaNacimiento = sharedPreferences.getString("fechaNacimiento", "N/A");

        nombreTextView.setText(nombre);
        correoTextView.setText(email);
        fechaNacimientoTextView.setText(fechaNacimiento);
    }

    private void cerrarSesion() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(getActivity(), iniciar_sesion.class);
        startActivity(intent);
        requireActivity().finish();
    }

}