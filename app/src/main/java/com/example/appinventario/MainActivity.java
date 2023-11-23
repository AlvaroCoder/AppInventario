package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appinventario.services.ConectarApi;
import com.example.appinventario.utilidades.utilidades;


import org.json.JSONException;
import org.json.JSONObject;

import java.sql.DriverManager;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    private EditText txtUsuario;
    private EditText txtContraseña;
    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConectarApi conexion = new ConectarApi(MainActivity.this);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnIniciar = findViewById(R.id.bntIniciar);


        // :)
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String usuario = txtUsuario.getText().toString();
               String contrasenna = txtContraseña.getText().toString();
                JSONObject jsonUser = new JSONObject();
                try {
                    jsonUser.put("usuario", usuario);
                    jsonUser.put("contrasenna",contrasenna);
                }catch (JSONException err){
                    err.printStackTrace();
                }

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                // Local
                String url = "https://apiappinventario.fly.dev/signin";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null ,
                        response -> {
                            try {
                                System.out.println("Response = "+response);
                                String error = response.getString("error");

                                boolean isError = Boolean.valueOf(error);
                                System.out.println("error = "+isError);
                                String message = response.getString("message");
                                // Se valida si hay algún error
                                if (isError){
                                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Toast.makeText(MainActivity.this, "Inicio correcto :)", Toast.LENGTH_SHORT).show();
                                System.out.println("idUsuario de Main = "+message);
                                Intent intent = new Intent(MainActivity.this, Locales.class);
                                intent.putExtra("idUsuario",message);
                                startActivity(intent);

                            }catch (JSONException err){
                                err.printStackTrace();
                            }
                        }, error -> {
                        error.printStackTrace();
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }

                    @Override
                    public byte[] getBody() {
                        return jsonUser.toString().getBytes();
                    }
                };
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}