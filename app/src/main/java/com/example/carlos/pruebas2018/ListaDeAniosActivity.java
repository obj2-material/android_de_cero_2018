package com.example.carlos.pruebas2018;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carlos.pruebas2018.databinding.ActivityListaDeAniosBinding;
import com.example.carlos.pruebas2018.databinding.ActivityMenuInicialBinding;
import com.example.carlos.pruebas2018.model.Anio;

public class ListaDeAniosActivity extends AppCompatActivity {
    private ListaDeAniosController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // obtengo la informacion que enviaron en el Intent
        int anioInicial = this.getIntent().getIntExtra("primerAnio", 2000);

        controller = new ListaDeAniosController(this, anioInicial);
        ActivityListaDeAniosBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_lista_de_anios);
        binding.setController(controller);

        this.agregarOnClick();
    }

    private void agregarOnClick() {
        ListView listaDeAnios = this.findViewById(R.id.listaDeAnios);

        // un poco más corto
        listaDeAnios.setOnItemClickListener((listView, viewFila, position, elIdDeNoSeQue) -> {
            this.controller.mostrarAnio(position);
        });
    }


    private void agregarOnClickConToast() {
        ListView listaDeAnios = this.findViewById(R.id.listaDeAnios);

        // un poco más corto
        listaDeAnios.setOnItemClickListener((listView, viewFila, position, elIdDeNoSeQue) -> {
            Anio anio = this.controller.getAnio(position);

            // El año 2018 no es bisiesto
            String mensaje = "El año "
                    + anio.getNumeroAsString()
                    + (anio.isBisiesto() ? " es " : " no es ")
                    + "bisiesto ";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
            toast.show();

        });

        // no está mal hacerlo así, es solamente más largo
//        listaDeAnios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
    }

}
