package com.example.carlos.pruebas2018;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.carlos.pruebas2018.databinding.ActivityAnioBinding;
import com.example.carlos.pruebas2018.databinding.ActivityMenuInicialBinding;
import com.example.carlos.pruebas2018.model.Anio;
import com.example.carlos.pruebas2018.tools.ApplicationToolset;

public class AnioActivity extends AppCompatActivity {
    private AnioActivityController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new AnioActivityController(this);
        ActivityAnioBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_anio);
        binding.setController(this.controller);

        Intent paseRecibido = this.getIntent();
        Anio anioAMostrar = (Anio) paseRecibido.getSerializableExtra("anio");
        controller.setAnioAMostrar(anioAMostrar);
    }



}
