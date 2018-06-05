package com.example.carlos.pruebas2018;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class InfoSarmientoController extends BaseObservable {
    private AppCompatActivity activity;

    private List<String> textosRotativos = new ArrayList<>();
    private int indiceTextosRotativos = 0;

    public InfoSarmientoController(AppCompatActivity theActivity) {
        super();
        this.activity = theActivity;
        this.initTextosRotativos();
    }

    public void initTextosRotativos() {
        this.textosRotativos.add("Capital Provincial de la Bandera");
        this.textosRotativos.add("Población: 15000 hab.");
        this.textosRotativos.add("Ciudades cercanas: Arrecifes, San Antonio de Areco");
        this.indiceTextosRotativos = -1;
    }

    public AppCompatActivity getActivity() {
        return this.activity;
    }

    @Bindable
    public String getTextoActual() {
        return this.indiceTextosRotativos == -1
                ? ""
                : this.textosRotativos.get(indiceTextosRotativos);
    }

    public void cambiarTexto() {
        this.indiceTextosRotativos++;
        if (this.indiceTextosRotativos == this.textosRotativos.size()) {
            this.indiceTextosRotativos = 0;
        }
        this.notifyPropertyChanged(BR.textoActual);
    }

}
