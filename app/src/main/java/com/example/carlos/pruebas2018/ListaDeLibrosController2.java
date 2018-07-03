package com.example.carlos.pruebas2018;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.carlos.pruebas2018.model.Libro;
import com.example.carlos.pruebas2018.tools.ApplicationToolset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaDeLibrosController2 extends BaseObservable {

    private AppCompatActivity activity;
    // tener la lista de libros nos va a servir para responder al evento de clickear en uno,
    // si se necesita obtener un libro a partir de la posicion
    private List<Libro> libros = new ArrayList<>();
    // hay que recordar el adapter, para pasarle las filas a posteriori
    private ArrayAdapter<Object> librosAdapterInicial;
    private SimpleAdapter segundoAdapter;
//    private BaseAdapter adapter;

    public ListaDeLibrosController2(AppCompatActivity _activity) {
        super();
        this.activity = _activity;
        this.createLibrosAdapterInicial();
        this.segundoAdapter = null;
        // pongo una fila provisoria hasta que llegue la info del servidor
        // una alternativa más elaborada es tener dos views, un cartel que diga
        // "esperando información" y la lista, los dos en la misma posición,
        // que se vea uno o el otro.
        // en el llenarLibrosAdapter se esconde el cartel provisorio y se muestra la lista.
        // para esto hay que saber cómo poner visible, o no, un view.
        // imagino que para esto está el atributo android:visibility, aunque nunca lo usé.
        this.librosAdapterInicial.add("... obteniendo información ...");
        this.fetchLibros();  // no devuelve, los va a buscar

        // antes haciamos
//        this.librosAdapterInicial.addAll(this.getLibros());
        // donde getLibros usa p.ej. un Store
    }

    @Bindable
    public BaseAdapter getLibrosAdapter() {
        if (this.segundoAdapter == null) {
            return this.librosAdapterInicial;
        } else {
            return this.segundoAdapter;
        }
    }
//    public BaseAdapter getLibrosAdapter() { return this.adapter; }

    public void createLibrosAdapterInicial() {
        this.librosAdapterInicial = new ArrayAdapter<>(
                this.activity, android.R.layout.simple_list_item_1
        );
//        this.adapter = this.librosAdapterInicial;
    }

    public void createSegundoAdapter() {
        this.segundoAdapter = new SimpleAdapter(
                this.activity,
                this.crearMapasAMostrar(),
                android.R.layout.simple_list_item_2,
                new String[]{"titulo", "autor"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        this.notifyPropertyChanged(BR.librosAdapter);
    }

    public List<Map<String, String>> crearMapasAMostrar() {
        ArrayList<Map<String, String>> losMapas = new ArrayList<>();
        for (Libro libro: libros) {
            Map<String, String> infoLibro = new HashMap<>();
            infoLibro.put("titulo", libro.getTitulo());
            infoLibro.put("autor", libro.getAutor());
            losMapas.add(infoLibro);
        }
        return losMapas;
    }

    public void llenarLibrosAdapter() {
        this.librosAdapterInicial.clear();
        this.createSegundoAdapter();
    }

//    public List<XXXX> getLibros() {}  -- esto no lo podemos hacer asi
    public void fetchLibros() {
//        String url =  "https://restcountries.eu/rest/v2/region/americas";
        String url = "https://bibliography-card-app-zohafdyrxn.now.sh/books";

        // request: pedido, response: respuesta

        // va JsonArrayRequest porque la URL me devuelve una lista de objetos
        // seria JsonObjectRequest si la URL me devolviera un objeto
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
            // el constructor lleva cinco parametros
            (
                // primer parametro: siempre GET
                Request.Method.GET,

                // segundo parametro: la URL
                url,

                // tercer parametro: por ahora null
                null,

                // cuarto parametro (muy importante)
                // la funcion que Volley va a ejecutar cuando llegue la info
                // tecnicamente, cuando llegue la *respuesta* al pedido
                (JSONArray response) -> {
                    // paso 1: de la response a la lista que mantengo en el controller
                    this.procesarRespuestaDelServer(response);
                    // paso 2: del controller al adapter
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

                // los autores son una lista de String
                JSONArray autores = libro.getJSONArray("authors");
                String primerAutor = autores.getString(0);

                // hasta aca obtuve la info a partir de la respuesta,
                // a partir de aca hago lo que decido con esa info
                this.libros.add(new Libro(titulo, anio, primerAutor));
            }
        } catch (JSONException error) {
            error.printStackTrace();
            throw new RuntimeException("Error en el procesamiento del JSON", error);
        }
    }
}
