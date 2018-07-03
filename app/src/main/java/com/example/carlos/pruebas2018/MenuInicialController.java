package com.example.carlos.pruebas2018;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Year;
import org.threeten.bp.format.DateTimeFormatter;

public class MenuInicialController extends BaseObservable {
    private String titulo = "Hola a todos";
    private String texto = "Estamos en el año " + Year.now().getValue();
    private int tamanioLetra = 50;
    private AppCompatActivity activity;

    public MenuInicialController(AppCompatActivity _activity) {
        super();
        this.activity = _activity;
    }


    @Bindable
    public int getTamanioLetra() {
        return tamanioLetra;
    }

    @Bindable
    public String getTitulo() { return this.titulo; }

    @Bindable
    public String getTexto() { return this.texto; }

    public void setTexto(String texto) {
        this.texto = texto;
        this.notifyPropertyChanged(BR.texto);
    }

    public void setTamanioLetra(int tamanioLetra) {
        this.tamanioLetra = tamanioLetra;
        this.notifyPropertyChanged(BR.tamanioLetra);
    }

    public void mostrarFechaDeHoy() {
        this.setTexto("Hoy es " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public void aumentarLetra() {
        this.setTamanioLetra(this.tamanioLetra + 5);
    }

    public void mostrarListaDesde(int anioInicial) {
        Intent salto = new Intent(this.activity.getBaseContext(), ListaDeAniosActivity.class);
        // con el putExtra le paso informacion al activity que se va a abrir
        salto.putExtra("primerAnio", anioInicial);
        this.activity.getBaseContext().startActivity(salto);
    }

    public void mostrarListaDeLibros() {
        Intent salto = new Intent(this.activity.getBaseContext(), ListaDeLibrosActivity.class);
        this.activity.getBaseContext().startActivity(salto);
    }

    public void mostrarListaDeLibros2() {
        Intent salto = new Intent(this.activity.getBaseContext(), ListaDeLibrosActivity2.class);
        this.activity.getBaseContext().startActivity(salto);
    }

}
