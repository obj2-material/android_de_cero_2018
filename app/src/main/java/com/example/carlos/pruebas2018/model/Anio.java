package com.example.carlos.pruebas2018.model;

import java.io.Serializable;

public class Anio implements Serializable {
    private int numeroAnio;

    public Anio(int numero) { this.numeroAnio = numero; }

    public int getNumero() { return this.numeroAnio; }
    public String getNumeroAsString() { return String.valueOf(this.numeroAnio); }

    public boolean isBisiesto() {
        return (this.numeroAnio % 400 == 0) ||
                ((this.numeroAnio % 4 == 0) && (this.numeroAnio % 100 != 0));
    }

    public boolean isConMundial() { return this.numeroAnio % 4 == 2; }

    /*
     * Esto es lo que el ArrayAdapter va a mostrar de cada año.
     * El método se *tiene* que llamar toString().
     * Viene heredado de la clase Object.
     */
    @Override
    public String toString() { return this.getNumeroAsString(); }
}
