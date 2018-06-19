package com.example.carlos.pruebas2018;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.carlos.pruebas2018.databinding.ActivityListaDeLibrosBinding;

public class ListaDeLibrosActivity extends AppCompatActivity {
    private ListaDeLibrosController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ListaDeLibrosController(this);
        ActivityListaDeLibrosBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_lista_de_libros);
        binding.setController(controller);
    }
}
