package com.example.carlos.pruebas2018;

import android.databinding.BaseObservable;
import android.support.v7.app.AppCompatActivity;

import com.example.carlos.pruebas2018.model.Anio;

public class AnioActivityController extends BaseObservable {
    private AppCompatActivity activity;
    private Anio anioAMostrar;

    public AnioActivityController(AppCompatActivity _activity) {
        this.activity = _activity;
    }

    public String getNumeroAnio() {
        return this.anioAMostrar.getNumeroAsString();
    }

    public String getInfoMundial() {
        return "En este año " +
                (this.anioAMostrar.isConMundial() ? "hay" : "no hay") +
                " mundial de fútbol";
    }

    public String getGanadorDelMundial() {
        if (this.anioAMostrar.getNumero() == 2030) {
            return "Ganador: Argentina";
        } else {
            return this.anioAMostrar.isConMundial() ? "Ganador: Brasil" : "";
        }
    }

    public void setAnioAMostrar(Anio _anioAMostrar) {
        this.anioAMostrar = _anioAMostrar;
    }
}
