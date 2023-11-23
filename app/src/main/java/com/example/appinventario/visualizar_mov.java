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

public class visualizar_mov extends AppCompatActivity {

    List<Movimientos> listaMovimientos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_mov);

        RecyclerView contenedor_movimientos=findViewById(R.id.contenedor_mov);
        listaMovimientos = new LinkedList<>();
        String idLocal = getIntent().getStringExtra("idLocales");
        String url = "https://apiappinventario.fly.dev/movimiento/"+idLocal;
        RequestQueue requestQueue = Volley.newRequestQueue(visualizar_mov.this);
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
                                        JSONObject jsonMovimientos = message.getJSONObject(i);

                                        String fecha = jsonMovimientos.getString("fecha");
                                        String nombre = jsonMovimientos.getString("nombre");
                                        String cantidad = String.valueOf(jsonMovimientos.getInt("cantidad"));
                                        String idMovimiento = String.valueOf(jsonMovimientos.getInt("idMovimiento"));
                                        String tipo = jsonMovimientos.getString("tipo");

                                        Movimientos movimientos = new Movimientos(fecha, nombre, cantidad, tipo);
                                        listaMovimientos.add(movimientos);

                                        Adapter_Mov adapterMov = new Adapter_Mov(visualizar_mov.this, listaMovimientos);
                                        contenedor_movimientos.setHasFixedSize(true);
                                        contenedor_movimientos.setLayoutManager(new LinearLayoutManager(visualizar_mov.this));
                                        contenedor_movimientos.setAdapter(adapterMov);

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