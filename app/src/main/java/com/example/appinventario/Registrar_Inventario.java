package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registrar_Inventario extends AppCompatActivity {
    private EditText reg1;
    private EditText reg2;
    private EditText reg3;
    private EditText reg4;
    private Button btnregistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_inventario);

        reg1 = findViewById(R.id.reg1);
        reg2  = findViewById(R.id.reg2);
        reg3 = findViewById(R.id.reg3);
        reg4 = findViewById(R.id.reg4);
        btnregistro=findViewById(R.id.btnregistro);

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(Registrar_Inventario.this);
                String idLocal = getIntent().getStringExtra("idLocales");
                String url = "https://apiappinventario.fly.dev/insumos";
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
                        String strNombre = reg1.getText().toString();
                        String strMedida = reg2.getText().toString();
                        int strStock = Integer.parseInt(reg3.getText().toString());
                        int strStockMin = Integer.parseInt(reg4.getText().toString());

                        JSONObject jsonLocal = new JSONObject();
                        try {
                            jsonLocal.put("idLocal", Integer.parseInt(idLocal));
                            jsonLocal.put("Nombre",strNombre);
                            jsonLocal.put("Medida", strMedida);
                            jsonLocal.put("Stock", strStock);
                            jsonLocal.put("StockMin", strStockMin);

                        }catch (JSONException err){
                            err.printStackTrace();
                        }
                        return  jsonLocal.toString().getBytes();
                    }
                };
                requestQueue.add(jsonObjectRequest);

                Intent intent = new Intent(Registrar_Inventario.this, menu_seleccionar_accion.class);
                intent.putExtra("idLocales", idLocal);
                startActivity(intent);

            }
        });
        this.limpiarCajas();

    }


    public void regresar(View view){
        Intent regresa=new Intent(Registrar_Inventario.this,menu_seleccionar_accion.class);
        startActivity(regresa);
    }


    public void limpiarCajas(){
        reg1.setText("");
        reg2.setText("");
        reg3.setText("");
        reg4.setText("");
    }
}