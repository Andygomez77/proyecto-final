package com.example.proyecto_final;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final.adaptadores.comicadaptador;
import com.example.proyecto_final.clases.comics;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adaptador extends AppCompatActivity implements comicadaptador.OnComicClickListener {

    RecyclerView rcv_comics;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaptador);

        spinner = findViewById(R.id.spinner);
        rcv_comics = findViewById(R.id.rcv_comics);

        List<comics> comicList = new ArrayList<>();
        comics lis1 = new comics("Comic 1", "https://www.cuartomundo.cl/wp-content/uploads/2019/03/Portadas-marvel-70.jpg", "2021", "Descripción del cómic 1");
        comics lis2 = new comics("Comic 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeUCPyR97PVReLNFFxzB2b6IHVIde3lS4dhw&s", "2020", "Descripción del cómic 2");
        comics lis3 = new comics("Comic 3", "https://hablandodecomics.wordpress.com/wp-content/uploads/2012/01/portada-95-x-men.jpg?w=723", "2019", "Descripción del cómic 3");
        comics lis4 = new comics("Comic 4", "https://www.cuartomundo.cl/wp-content/uploads/2019/03/ms.-marvel-1.jpg", "2018", "Descripción del cómic 4");
        comicList.add(lis1);
        comicList.add(lis2);
        comicList.add(lis3);
        comicList.add(lis4);


        rcv_comics.setLayoutManager(new GridLayoutManager(this, 2));
        rcv_comics.setAdapter(new comicadaptador(comicList, this));

        //
        String[] opciones = {"Settings","Cerrar2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String operador = spinner.getSelectedItem().toString();

        switch (operador) {
            case "Settings":

                break;
            case "Cerrar2":

                break;

        }
    }



    @Override
    public void onComicClick(comics comic) {
        showComicDialog(comic);
    }

    private void showComicDialog(comics comic) {
        Dialog dialog = new Dialog(this);
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

