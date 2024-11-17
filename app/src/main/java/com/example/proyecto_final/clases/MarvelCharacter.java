package com.example.proyecto_final.clases;

import java.util.List;

public class MarvelCharacter {
    private String name;
    private String description;
    private String thumbnailUrl;
    private List<String> comics;

    public MarvelCharacter(String name, String description, String thumbnailUrl, List<String> comics) {
        this.name = name;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.comics = comics;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<String> getComics() {
        return comics;
    }

    public void setComics(List<String> comics) {
        this.comics = comics;
    }
}


