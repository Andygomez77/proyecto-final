package com.example.proyecto_final.api;

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

    @GET("v1/public/comics")
    Call<ComicResponse> getComics(
            @Query("apikey") String apiKey,
            @Query("ts") String timestamp,
            @Query("hash") String hash
    );

    @GET("v1/public/characters")
    Call<MarvelResponse> getCharacterByName(
            @Query("apikey") String apikey,
            @Query("ts") String ts,
            @Query("hash") String hash,
            @Query("nameStartsWith") String name
    );
}

