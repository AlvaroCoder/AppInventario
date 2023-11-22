package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appinventario.Dbhelper.ConexionSqLite;
import com.example.appinventario.Dbhelper.db_locales;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Registrar_Locales extends AppCompatActivity {
    private EditText txtlocal;
    private EditText txtdireccion;
    private EditText txtciudad;
    private EditText txtdistrito;
    private Button btnfinalizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_locales);

        txtlocal = findViewById(R.id.txtlocal);
        txtdireccion  = findViewById(R.id.txtfecha);
        txtciudad = findViewById(R.id.txtciudad);
        txtdistrito = findViewById(R.id.txtdistrito);
        btnfinalizar=findViewById(R.id.btnfecha);

        btnfinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(Registrar_Locales.this);
                String idUsuario = getIntent().getStringExtra("idUsuario");
                String url = "https://apiappinventario.fly.dev/local";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("RESPUESTA SERVIDOR = "+response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }

                    @Override
                    public byte[] getBody() {


                        String strLocal = txtlocal.getText().toString();
                        String strDireccion = txtdireccion.getText().toString();
                        String strDistrito = txtdistrito.getText().toString();
                        String strCiudad = txtciudad.getText().toString();

                        JSONObject jsonLocal = new JSONObject();
                        try {
                            jsonLocal.put("idUsuario", idUsuario);
                            jsonLocal.put("nombreLocal",strLocal);
                            jsonLocal.put("direccion", strDireccion);
                            jsonLocal.put("distrito", strDistrito);
                            jsonLocal.put("ciudad", strCiudad);

                        }catch (JSONException err){
                            err.printStackTrace();
                        }
                        return  jsonLocal.toString().getBytes();
                    }
                };
                requestQueue.add(jsonObjectRequest);

                Intent intent = new Intent(Registrar_Locales.this, Locales.class);
                intent.putExtra("idUsuario", idUsuario);
                startActivity(intent);
            }

        });

        this.limpiarCajas();
    }










    public void retro_loc(View view) {
        Intent retro = new Intent(getApplicationContext(), Locales.class);
        startActivity(retro);}

    public void limpiarCajas(){
        txtlocal.setText("");
        txtdireccion.setText("");
        txtciudad.setText("");
        txtdistrito.setText("");
    }
}