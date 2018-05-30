package com.example.carlos.pruebas2018;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class PruebasApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }
}
