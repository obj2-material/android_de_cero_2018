package com.example.carlos.pruebas2018.model;

/*
 * Data object para la API de Libros
 */
public class Libro {
    private String titulo;
    private int anio;
    private String autor;

    public Libro(String _titulo, int _anio, String _autor) {
        this.titulo = _titulo;
        this.anio = _anio;
        this.autor = _autor;
    }

    public String getTitulo() { return this.titulo; }
    public int getAnio() { return this.anio; }

    public String toString() {
        return this.getTitulo() + " de " + this.autor + ", año " + this.getAnio();
    };
}
