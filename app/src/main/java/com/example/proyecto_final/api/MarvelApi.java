package com.example.proyecto_final.api;



import com.example.proyecto_final.api.MarvelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApi {
    @GET("v1/public/characters")
    Call<MarvelResponse> getCharacters(
            @Query("apikey") String apiKey,
            @Query("ts") String timestamp,
            @Query("hash") String hash
    );
}