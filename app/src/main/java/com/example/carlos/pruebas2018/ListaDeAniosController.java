package com.example.carlos.pruebas2018;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.carlos.pruebas2018.model.Anio;

import java.util.ArrayList;
import java.util.List;

public class ListaDeAniosController extends BaseObservable {
    private AppCompatActivity activity;
    private List<Anio> anios;

    public ListaDeAniosController(AppCompatActivity _activity, int anioInicial) {
        super();
        this.activity = _activity;
        this.anios = this.crearAnios(anioInicial);
    }

    @Bindable
    public ListAdapter getAniosAdapter() {
        ArrayAdapter<Anio> adapter = new ArrayAdapter<>(
                this.activity, android.R.layout.simple_list_item_1
        );
        adapter.addAll(this.anios);
        return adapter;
    }

    private List<Anio> crearAnios(int anioInicial) {
        List<Anio> anios = new ArrayList<>();
        for (int anio = anioInicial; anio <= anioInicial + 50; anio++) {
            anios.add(new Anio(anio));
        }
        return anios;
    }

    public Anio getAnio(int position)  { return this.anios.get(position); }
}
