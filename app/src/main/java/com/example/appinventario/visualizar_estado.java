package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class visualizar_estado extends AppCompatActivity {
    List<Estados> listaestados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_estado);

        String idLocal = getIntent().getStringExtra("idLocales");
        String url = "https://apiappinventario.fly.dev/insumos/"+idLocal;

        listaestados = new LinkedList<>();

        RecyclerView contenedor_estado = findViewById(R.id.contenedor_estado);

        RequestQueue requestQueue = Volley.newRequestQueue(visualizar_estado.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("Respuesta="+response);
                            JSONArray message = response.getJSONArray("message");
                            if (message.length()>0){
                                for (int i=0; i<message.length(); i++){
                                    try {
                                        JSONObject jsonLocal = message.getJSONObject(i);

                                        String nombre = jsonLocal.getString("nombre");
                                        String medida = jsonLocal.getString("medida");
                                        int stock = jsonLocal.getInt("Stock");
                                        int stockMin = jsonLocal.getInt("Stockmin");

                                        Estados estados = new Estados(nombre, medida, stock, stockMin);
                                        listaestados.add(estados);

                                        Adapter_Estados adapterLocales = new Adapter_Estados(visualizar_estado.this, listaestados);
                                        contenedor_estado.setHasFixedSize(true);
                                        contenedor_estado.setLayoutManager(new LinearLayoutManager(visualizar_estado.this));
                                        contenedor_estado.setAdapter(adapterLocales);

                                    }catch (JSONException err){
                                        err.printStackTrace();
                                    }
                                }
                            }

                        }catch (JSONException err){
                            err.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }
}