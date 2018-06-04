package com.example.carlos.pruebas2018;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carlos.pruebas2018.databinding.ActivityMenuInicialBinding;

public class MenuInicial extends AppCompatActivity {

    MenuInicialController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new MenuInicialController(this);
        ActivityMenuInicialBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_inicial);
        binding.setController(this.controller);
    }
}
