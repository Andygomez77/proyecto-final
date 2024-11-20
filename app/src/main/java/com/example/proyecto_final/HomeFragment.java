package com.example.proyecto_final;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final.adaptadores.MarvelCharacterAdapter;
import com.example.proyecto_final.api.ApiClient;
import com.example.proyecto_final.api.MarvelApi;
import com.example.proyecto_final.api.MarvelResponse;
import com.example.proyecto_final.clases.MarvelCharacter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.TextWatcher;
import android.text.Editable;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MarvelCharacterAdapter adapter;
    private EditText editText;
    private View loadingLayout, contentLayout;

    private final String PUBLIC_KEY = "ad200d22dc07ffd7365f647cad3abebd";
    private final String PRIVATE_KEY = "af2c3744078efb314683abbbeaeb971474343937";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        editText = view.findViewById(R.id.searchBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadingLayout = view.findViewById(R.id.loading_layout);
        contentLayout = view.findViewById(R.id.content_layout);

        showLoading(true);

        fetchMarvelCharacters();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().trim();
                if (!query.isEmpty()) {
                    searchCharacterByName(query);
                } else {
                    fetchMarvelCharacters();
                }
            }
        });

        return view;
    }

    private void fetchMarvelCharacters() {
        showLoading(true);

        MarvelApi api = ApiClient.getRetrofitInstance().create(MarvelApi.class);

        String ts = "1";
        String hash = generateHash(ts, PRIVATE_KEY, PUBLIC_KEY);

        Call<MarvelResponse> call = api.getCharacters(PUBLIC_KEY, ts, hash);
        call.enqueue(new Callback<MarvelResponse>() {
            @Override
            public void onResponse(Call<MarvelResponse> call, Response<MarvelResponse> response) {
                handleApiResponse(response);
                showLoading(false);
            }

            @Override
            public void onFailure(Call<MarvelResponse> call, Throwable t) {
                Log.e("API_FAILURE", "Error: " + t.getMessage());
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                showLoading(false);
            }
        });
    }

    private void searchCharacterByName(String name) {
        showLoading(true);

        MarvelApi api = ApiClient.getRetrofitInstance().create(MarvelApi.class);

        String ts = "1";
        String hash = generateHash(ts, PRIVATE_KEY, PUBLIC_KEY);

        Call<MarvelResponse> call = api.getCharacterByName(PUBLIC_KEY, ts, hash, name);
        call.enqueue(new Callback<MarvelResponse>() {
            @Override
            public void onResponse(Call<MarvelResponse> call, Response<MarvelResponse> response) {
                handleApiResponse(response);
                showLoading(false);
            }

            @Override
            public void onFailure(Call<MarvelResponse> call, Throwable t) {
                Log.e("API_FAILURE", "Error: " + t.getMessage());
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                showLoading(false);
            }
        });
    }

    private void handleApiResponse(Response<MarvelResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
            List<MarvelResponse.Character> apiCharacters = response.body().getData().getResults();
            List<MarvelCharacter> characters = new ArrayList<>();

            for (MarvelResponse.Character apiCharacter : apiCharacters) {
                List<String> comics = new ArrayList<>();
                if (apiCharacter.getComics() != null && apiCharacter.getComics().getItems() != null) {
                    for (MarvelResponse.Character.ComicSummary comic : apiCharacter.getComics().getItems()) {
                        comics.add(comic.getName());
                    }
                }

                MarvelCharacter character = new MarvelCharacter(
                        apiCharacter.getName(),
                        apiCharacter.getDescription() != null ? apiCharacter.getDescription() : "Sin descripción",
                        apiCharacter.getThumbnail().getUrl(),
                        comics
                );

                characters.add(character);
            }

            adapter = new MarvelCharacterAdapter(characters);
            recyclerView.setAdapter(adapter);
        } else {
            Log.e("API_ERROR", "Código de estado: " + response.code());
            Toast.makeText(getContext(), "Error al obtener los datos: Código " + response.code(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            loadingLayout.setVisibility(View.VISIBLE);
            contentLayout.setVisibility(View.GONE);
        } else {
            loadingLayout.setVisibility(View.GONE);
            contentLayout.setVisibility(View.VISIBLE);
        }
    }

    private String generateHash(String ts, String privateKey, String publicKey) {
        try {
            String value = ts + privateKey + publicKey;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(value.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
