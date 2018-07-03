package com.example.carlos.pruebas2018;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.carlos.pruebas2018.databinding.ActivityListaDeLibros2Binding;
import com.example.carlos.pruebas2018.databinding.ActivityListaDeLibrosBinding;

public class ListaDeLibrosActivity2 extends AppCompatActivity {
    private ListaDeLibrosController2 controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ListaDeLibrosController2(this);
        ActivityListaDeLibros2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_lista_de_libros2);
        binding.setController(controller);
    }
}
