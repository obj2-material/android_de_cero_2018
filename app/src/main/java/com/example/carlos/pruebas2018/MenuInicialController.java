package com.example.carlos.pruebas2018;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class MenuInicialController extends BaseObservable {
    private String titulo = "Hola a todos";
    private String texto = "Estamos en el año 2018";

    @Bindable
    public String getTitulo() { return this.titulo; }

    @Bindable
    public String getTexto() { return this.texto; }
}
