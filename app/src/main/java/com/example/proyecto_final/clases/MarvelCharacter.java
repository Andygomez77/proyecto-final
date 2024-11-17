package com.example.proyecto_final.clases;

public class MarvelCharacter {
    private String name;
    private String description;
    private String thumbnailUrl;
    private int age;
    private String powers;
    private String yearOfBirth;


    public MarvelCharacter(String name, String description, String thumbnailUrl, int age, String powers, String yearOfBirth) {
        this.name = name;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.age = age;
        this.powers = powers;
        this.yearOfBirth = yearOfBirth;
    }

    // Getters y Setters
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
