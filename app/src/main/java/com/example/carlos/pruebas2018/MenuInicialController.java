package com.example.carlos.pruebas2018;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Year;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class MenuInicialController extends BaseObservable {
    private AppCompatActivity activity;

    private String titulo = "Hola a todos";
    private String texto = "Estamos en el año " + Year.now().getValue();
    private int colorTexto = R.color.colorPrimary;
    private int tamanioLetra = 50;

    public MenuInicialController(AppCompatActivity theActivity) {
        super();
        this.activity = theActivity;
    }

    public AppCompatActivity getActivity() {
        return this.activity;
    }

    @Bindable
    public int getTamanioLetra() {
        return tamanioLetra;
    }

    @Bindable
    public String getTitulo() { return this.titulo; }

    @Bindable
    public String getTexto() { return this.texto; }

    @Bindable
    public int getColorTexto() {
        return ContextCompat.getColor(this.getActivity().getBaseContext(), this.colorTexto);
    }

    public void setTexto(String texto) {
        this.texto = texto;
        this.notifyPropertyChanged(BR.texto);
    }

    public void setTamanioLetra(int tamanioLetra) {
        this.tamanioLetra = tamanioLetra;
        this.notifyPropertyChanged(BR.tamanioLetra);
    }

    public void setColorTexto(int color) {
        this.colorTexto = color;
        this.notifyPropertyChanged(BR.colorTexto);
    }

    public void mostrarFechaDeHoy() {
        this.setTexto("Hoy es " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public void pintarDeRojo() {
        this.setColorTexto(R.color.colorAccent);
    }

    public void aumentarLetra() {
        this.setTamanioLetra(this.tamanioLetra + 5);
    }

    public void mostrarInfoSarmiento() {
        Intent intent = new Intent(this.getActivity().getBaseContext(), InfoSarmiento.class );
        this.getActivity().getBaseContext().startActivity(intent);
    }
}
