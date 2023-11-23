package com.example.appinventario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Locales extends AppCompatActivity {
    private Button agregar;
    private List<Local> listaLocales;
    private String idLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locales);
        agregar=findViewById(R.id.agregar);
        RecyclerView contenedorLocales = findViewById(R.id.contenedor_locales);

        String idUsuario = getIntent().getStringExtra("idUsuario");
        String url = "https://apiappinventario.fly.dev/local/"+idUsuario;

        listaLocales = new LinkedList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(Locales.this);
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
                                        int idLocal = jsonLocal.getInt("idLocales");
                                        String nombreLocal= jsonLocal.getString("nombreLocal");
                                        String direccion = jsonLocal.getString("direccion");
                                        String distrito = jsonLocal.getString("distrito");
                                        String ciudad = jsonLocal.getString("ciudad");

                                        Local nuevoLocal = new Local(idLocal,nombreLocal, direccion, distrito, ciudad);
                                        listaLocales.add(nuevoLocal);
                                        AdapterLocales adapterLocales = new AdapterLocales(Locales.this, listaLocales);

                                        contenedorLocales.setHasFixedSize(true);
                                        contenedorLocales.setLayoutManager(new LinearLayoutManager(Locales.this));
                                        contenedorLocales.setAdapter(adapterLocales);



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

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Locales.this, Registrar_Locales.class);
                intent.putExtra("idUsuario", idUsuario);
                startActivity(intent);
            }
        });
    }

}