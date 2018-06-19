package com.example.carlos.pruebas2018.model;

/*
 * Data object para la API de Libros
 */
public class Libro {
    private String titulo;
    private int anio;

    public Libro(String _titulo, int _anio) {
        this.titulo = _titulo;
        this.anio = _anio;
    }

    public String getTitulo() { return this.titulo; }
    public int getAnio() { return this.anio; }

    public String toString() { return this.getTitulo() + ", del año " + this.getAnio(); };
}
