package com.example.carlos.pruebas2018;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.carlos.pruebas2018.databinding.ActivityInfoSarmientoBinding;

public class InfoSarmiento extends AppCompatActivity {

    InfoSarmientoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new InfoSarmientoController(this);
        ActivityInfoSarmientoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_info_sarmiento);
        binding.setController(this.controller);
    }
}
