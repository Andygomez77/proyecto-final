package com.example.proyecto_final;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyecto_final.adaptadores.comicadaptador;
import com.example.proyecto_final.clases.comics;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ComicFragment extends Fragment implements comicadaptador.OnComicClickListener {

    private RecyclerView rcv_comics;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic, container, false);

        // Inicializar elementos del layout

        rcv_comics = view.findViewById(R.id.rcv_comics);

        // Lista de cómics
        List<comics> comicList = new ArrayList<>();
        comics lis1 = new comics("Comic 1", "https://www.cuartomundo.cl/wp-content/uploads/2019/03/Portadas-marvel-70.jpg", "2021", "Descripción del cómic 1");
        comics lis2 = new comics("Comic 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeUCPyR97PVReLNFFxzB2b6IHVIde3lS4dhw&s", "2020", "Descripción del cómic 2");
        comics lis3 = new comics("Comic 3", "https://hablandodecomics.wordpress.com/wp-content/uploads/2012/01/portada-95-x-men.jpg?w=723", "2019", "Descripción del cómic 3");
        comics lis4 = new comics("Comic 4", "https://www.cuartomundo.cl/wp-content/uploads/2019/03/ms.-marvel-1.jpg", "2018", "Descripción del cómic 4");
        comicList.add(lis1);
        comicList.add(lis2);
        comicList.add(lis3);
        comicList.add(lis4);

        // Configurar RecyclerView
        rcv_comics.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcv_comics.setAdapter(new comicadaptador(comicList, this));

        return view;
    }

    private void cerrarSesion() {
        SharedPreferences preferences = getActivity().getSharedPreferences("MiAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear(); // Elimina todos los datos almacenados
        editor.apply();
    }

    @Override
    public void onComicClick(comics comic) {
        showComicDialog(comic);
    }

    private void showComicDialog(comics comic) {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_comic_details);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setDimAmount(0.8f);

        ImageView comicImageDialog = dialog.findViewById(R.id.comic_image_dialog);
        TextView comicNameDialog = dialog.findViewById(R.id.comic_name_dialog);
        TextView comicYearDialog = dialog.findViewById(R.id.comic_year_dialog);
        TextView comicDescriptionDialog = dialog.findViewById(R.id.comic_description_dialog);

        comicNameDialog.setText(comic.getNombre());
        comicYearDialog.setText("Año: " + comic.getYear());
        comicDescriptionDialog.setText(comic.getDescripcion());
        Picasso.get().load(comic.getImagen()).into(comicImageDialog);

        dialog.show();
    }
}