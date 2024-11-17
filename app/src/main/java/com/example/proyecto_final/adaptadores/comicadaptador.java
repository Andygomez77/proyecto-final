package com.example.proyecto_final.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final.R;
import com.example.proyecto_final.clases.comics;
import com.squareup.picasso.Picasso;

import java.util.List;

public class comicadaptador extends RecyclerView.Adapter<comicadaptador.ViewHolder> {

    private List<comics> comicList;
    private OnComicClickListener listener;

    // Constructor actualizado para recibir el listener
    public comicadaptador(List<comics> comicList, OnComicClickListener listener) {
        this.comicList = comicList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public comicadaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull comicadaptador.ViewHolder holder, int position) {
        comics comic = comicList.get(position);
        holder.bind(comic);


        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onComicClick(comic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView comicName;
        ImageView comicImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comicName = itemView.findViewById(R.id.comic_name);
            comicImage = itemView.findViewById(R.id.comic_image);
        }

        public void bind(comics comic) {
            comicName.setText(comic.getNombre() != null ? comic.getNombre() : "Título desconocido");
            Picasso.get().load(comic.getImagen()).into(comicImage);
        }
    }


    public interface OnComicClickListener {
        void onComicClick(comics comic);
    }
}


