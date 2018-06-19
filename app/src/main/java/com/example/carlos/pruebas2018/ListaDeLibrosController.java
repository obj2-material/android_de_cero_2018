package com.example.carlos.pruebas2018;

import android.databinding.BaseObservable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.carlos.pruebas2018.model.Anio;
import com.example.carlos.pruebas2018.model.Libro;
import com.example.carlos.pruebas2018.tools.ApplicationToolset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaDeLibrosController extends BaseObservable {

    private AppCompatActivity activity;
    private List<Libro> libros = new ArrayList<>();
    private ArrayAdapter<Libro> librosAdapter;

    public ListaDeLibrosController(AppCompatActivity _activity) {
        super();
        this.activity = _activity;
        this.librosAdapter = this.createLibrosAdapter();
        this.fetchLibros();
    }

    public ListAdapter getLibrosAdapter() { return this.librosAdapter; }

    public ArrayAdapter<Libro> createLibrosAdapter() {
        ArrayAdapter<Libro> adapter = new ArrayAdapter<>(
                this.activity, android.R.layout.simple_list_item_1
        );
        return adapter;
    }

    public void llenarLibrosAdapter() { this.librosAdapter.addAll(this.libros); }

//    public List<XXXX> getLibros() {}
    public void fetchLibros() {
//        String url =  "https://restcountries.eu/rest/v2/region/americas";
        String url = "https://bibliography-card-app-zohafdyrxn.now.sh/books";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
            (
                Request.Method.GET,
                url,
                null,
                (JSONArray response) -> {
                    try {
                        for (int indice = 0; indice < response.length(); indice++) {
                            JSONObject libro = response.getJSONObject(indice);;
                            // ... hago cosas con el libro ...
                            String titulo = libro.getString("title");
                            int anio = libro.getInt("year");
                            this.libros.add(new Libro(titulo, anio));
                        }
                        this.llenarLibrosAdapter();
                    } catch (JSONException error) {
                        error.printStackTrace();
                        throw new RuntimeException("Error en el procesamiento del JSON", error);
                    }
                },
                (VolleyError error) -> {
                    error.printStackTrace();
                    throw new RuntimeException("Error en el request REST", error);
                }
            );

        // OJO ver el onCreate de MenuInicial
        ApplicationToolset.toolset().addToRequestQueue(jsonArrayRequest);
    }
}
