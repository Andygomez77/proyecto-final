package com.example.proyecto_final.clases;

public class comics {
    private String imagen;
    private String nombre;
    private String year;
    private String descripcion;

    public comics(String nombre, String imagen, String year, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.year = year;
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
