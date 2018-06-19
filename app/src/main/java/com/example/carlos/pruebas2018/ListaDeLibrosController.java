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
    // tener la lista de libros nos va a servir para responder al evento de clickear en uno,
    // si se necesita obtener un libro a partir de la posicion
    private List<Libro> libros = new ArrayList<>();
    // hay que recordar el adapter, para pasarle las filas a posteriori
    private ArrayAdapter<Object> librosAdapter;

    public ListaDeLibrosController(AppCompatActivity _activity) {
        super();
        this.activity = _activity;
        this.librosAdapter = this.createLibrosAdapter();
        // pongo una fila provisoria hasta que llegue la info del servidor
        // una alternativa más elaborada es tener dos views, un cartel que diga
        // "esperando información" y la lista, los dos en la misma posición,
        // que se vea uno o el otro.
        // en el llenarLibrosAdapter se esconde el cartel provisorio y se muestra la lista.
        // para esto hay que saber cómo poner visible, o no, un view.
        // imagino que para esto está el atributo android:visibility, aunque nunca lo usé.
        this.librosAdapter.add("... obteniendo información ...");
        this.fetchLibros();
    }

    public ListAdapter getLibrosAdapter() { return this.librosAdapter; }

    public ArrayAdapter<Object> createLibrosAdapter() {
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(
                this.activity, android.R.layout.simple_list_item_1
        );
        return adapter;
    }

    public void llenarLibrosAdapter() {
        this.librosAdapter.clear();
        this.librosAdapter.addAll(this.libros);
    }

//    public List<XXXX> getLibros() {}  -- esto no lo podemos hacer asi
    public void fetchLibros() {
//        String url =  "https://restcountries.eu/rest/v2/region/americas";
        String url = "https://bibliography-card-app-zohafdyrxn.now.sh/books";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
            (
                Request.Method.GET,
                url,
                null,
                (JSONArray response) -> {
                    this.procesarRespuestaDelServer(response);
                    this.llenarLibrosAdapter();
                },
                (VolleyError error) -> {
                    error.printStackTrace();
                    throw new RuntimeException("Error en el request REST", error);
                }
            );

        // OJO ver el onCreate de MenuInicial
        ApplicationToolset.toolset().addToRequestQueue(jsonArrayRequest);
    }

    /*
     * Se separa el analisis del JSON en un metodo aparte
     */
    public void procesarRespuestaDelServer(JSONArray respuesta) {
        try {
            for (int indice = 0; indice < respuesta.length(); indice++) {
                JSONObject libro = respuesta.getJSONObject(indice);;
                // ... hago cosas con el libro ...
                String titulo = libro.getString("title");
                int anio = libro.getInt("year");
                this.libros.add(new Libro(titulo, anio));
            }
        } catch (JSONException error) {
            error.printStackTrace();
            throw new RuntimeException("Error en el procesamiento del JSON", error);
        }
    }
}
