package com.example.proyecto_final;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_final.adaptadores.comicadaptador;
import com.example.proyecto_final.api.ApiClient;
import com.example.proyecto_final.api.ComicResponse;
import com.example.proyecto_final.api.MarvelApi;
import com.example.proyecto_final.clases.comics;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComicFragment extends Fragment implements comicadaptador.OnComicClickListener {

    private RecyclerView rcv_comics;
    private comicadaptador adaptador;

    private final String PUBLIC_KEY = "ad200d22dc07ffd7365f647cad3abebd";
    private final String PRIVATE_KEY = "af2c3744078efb314683abbbeaeb971474343937";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic, container, false);


        rcv_comics = view.findViewById(R.id.rcv_comics);
        rcv_comics.setLayoutManager(new GridLayoutManager(getContext(), 2));


        fetchComicsFromApi();

        return view;
    }

    private void fetchComicsFromApi() {
        MarvelApi api = ApiClient.getRetrofitInstance().create(MarvelApi.class);

        String ts = "1"; // Timestamp fijo
        String hash = generateHash(ts, PRIVATE_KEY, PUBLIC_KEY);

        Call<ComicResponse> call = api.getComics(PUBLIC_KEY, ts, hash);
        call.enqueue(new Callback<ComicResponse>() {
            @Override
            public void onResponse(Call<ComicResponse> call, Response<ComicResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    List<ComicResponse.Comic> apiComics = response.body().getData().getResults();
                    if (apiComics != null) {
                        List<comics> comicList = new ArrayList<>();
                        for (ComicResponse.Comic apiComic : apiComics) {
                            String year = "Año desconocido";
                            if (apiComic.getDates() != null && !apiComic.getDates().isEmpty()) {
                                for (ComicResponse.Comic.Date date : apiComic.getDates()) {
                                    if ("onsaleDate".equals(date.getType())) {
                                        year = date.getDate().split("-")[0];
                                        break;
                                    }
                                }
                            }

                            comics comic = new comics(
                                    apiComic.getTitle(),
                                    apiComic.getThumbnail().getUrl(),
                                    year,
                                    apiComic.getDescription() != null ? apiComic.getDescription() : "Sin sinopsis disponible"
                            );
                            comicList.add(comic);
                        }

                        adaptador = new comicadaptador(comicList, ComicFragment.this);
                        rcv_comics.setAdapter(adaptador);
                    } else {
                        Toast.makeText(getContext(), "No se encontraron cómics", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error al obtener los cómics: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ComicResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
        comicYearDialog.setText("Año de lanzamiento: " + (comic.getYear() != null && !comic.getYear().isEmpty() ? comic.getYear() : "Año desconocido"));
        comicDescriptionDialog.setText(comic.getDescripcion() != null && !comic.getDescripcion().isEmpty() ? comic.getDescripcion() : "Sin sinopsis disponible");
        Picasso.get().load(comic.getImagen()).into(comicImageDialog);


        dialog.show();
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
