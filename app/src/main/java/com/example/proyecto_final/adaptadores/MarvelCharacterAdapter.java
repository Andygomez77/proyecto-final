package com.example.proyecto_final.adaptadores;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final.R;
import com.example.proyecto_final.clases.MarvelCharacter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MarvelCharacterAdapter extends RecyclerView.Adapter<MarvelCharacterAdapter.ViewHolder> {

    private final List<MarvelCharacter> characterList;

    public MarvelCharacterAdapter(List<MarvelCharacter> characterList) {
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MarvelCharacter character = characterList.get(position);
        holder.bind(character);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView characterImage;
        private final TextView characterName, characterYearOfBirth, characterDescription, characterPowers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            characterImage = itemView.findViewById(R.id.characterThumbnail);
            characterName = itemView.findViewById(R.id.characterName);
            characterYearOfBirth = itemView.findViewById(R.id.characterYearOfBirth);
            characterDescription = itemView.findViewById(R.id.characterDescription);
            characterPowers = itemView.findViewById(R.id.characterPowers);
        }

        public void bind(MarvelCharacter character) {
            characterName.setText(character.getName() != null ? character.getName() : "Nombre desconocido");
            characterYearOfBirth.setText("Año de Nacimiento: " +
                    (character.getYearOfBirth() != null && !character.getYearOfBirth().isEmpty() ? character.getYearOfBirth() : "Desconocido"));
            characterDescription.setText(character.getDescription() != null && !character.getDescription().isEmpty()
                    ? character.getDescription() : "Sin descripción");
            characterPowers.setText("Poderes: " +
                    (character.getPowers() != null && !character.getPowers().isEmpty() ? character.getPowers() : "No especificados"));

            // Log para verificar las URLs
            Log.d("PICASSO", "Cargando URL: " + character.getThumbnailUrl());

            if (character.getThumbnailUrl() != null && !character.getThumbnailUrl().isEmpty()) {
                Picasso.get()
                        .load(character.getThumbnailUrl())
                        .placeholder(R.drawable.placeholder_image) // Imagen mientras carga
                        .error(R.drawable.placeholder_image) // Imagen si ocurre error
                        .into(characterImage);
            } else {
                characterImage.setImageResource(R.drawable.placeholder_image);
            }
        }

    }
}