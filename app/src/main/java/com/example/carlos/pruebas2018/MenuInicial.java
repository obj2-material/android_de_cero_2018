package com.example.carlos.pruebas2018;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carlos.pruebas2018.databinding.ActivityMenuInicialBinding;
import com.example.carlos.pruebas2018.tools.ApplicationToolset;

public class MenuInicial extends AppCompatActivity {

    MenuInicialController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // esto hay que hacerlo en el onCreate de la Activity principal
        // tambien podria hacerse en la subclase de Application
        // como tienen en el proyecto de-cero-2018
        ApplicationToolset.setContext(this.getApplicationContext());

        super.onCreate(savedInstanceState);
        controller = new MenuInicialController(this);
        ActivityMenuInicialBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_inicial);
        binding.setController(this.controller);
    }
}
