package com.example.carlos.pruebas2018;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Year;
import org.threeten.bp.format.DateTimeFormatter;

public class MenuInicialController extends BaseObservable {
    private String titulo = "Hola a todos";
    private String texto = "Estamos en el año " + Year.now().getValue();

    @Bindable
    public String getTitulo() { return this.titulo; }

    @Bindable
    public String getTexto() { return this.texto; }

    public void setTexto(String texto) {
        this.texto = texto;
        this.notifyPropertyChanged(BR.texto);
    }

    public void mostrarFechaDeHoy() {
        this.setTexto("Hoy es " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
