package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Ingreso_Retiro extends AppCompatActivity {
    private RadioGroup rdgrupo;

    private Button btnfecha,btningresar;
    private EditText txtfecha,txtinsumo,txtcantidad;
    private int dia,mes,ano;
    private RadioButton radioButtonIngreso, radioButtonRetiro;
    private String tipo = "Ingreso", fechaMovimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_retiro);

        btnfecha= findViewById(R.id.btnfecha);
        txtfecha= findViewById(R.id.txtfecha);
        txtinsumo= findViewById(R.id.txtinsumo);
        txtcantidad= findViewById(R.id.txtcantidad);
        btningresar= findViewById(R.id.btningresar);
        radioButtonIngreso = findViewById(R.id.radioButtonIngreso);
        radioButtonRetiro = findViewById(R.id.radioButtonRetiro);

        radioButtonRetiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bool = ((RadioButton) view).isChecked();
                if (bool){
                    radioButtonRetiro.setChecked(true);
                    tipo="Retiro";
                }
            }
        });
        radioButtonIngreso.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bool = ((RadioButton) view).isChecked();
                if (bool){
                    radioButtonIngreso.setChecked(true);
                    tipo="Ingreso";
                }
            }
        });
        // :)

        btningresar.setOnClickListener(this::ingresarMovimiento);
        btnfecha.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String formatFecha = year+"-"+(month+1)+"-"+dayOfMonth;
                txtfecha.setText(formatFecha);
                fechaMovimiento=formatFecha;
            }
        }
        ,dia,mes,ano);
        datePickerDialog.show();
    }
    public void retro_selec_acc(View view) {
        Intent retro = new Intent(getApplicationContext(), menu_seleccionar_accion.class);
        startActivity(retro);}
    public void ingresarMovimiento(View view){
        String url = "https://apiappinventario.fly.dev/insumos";
        RequestQueue requestQueue = Volley.newRequestQueue(Ingreso_Retiro.this);
        String idLocales = getIntent().getStringExtra("idLocales");
        System.out.println("idLocales = "+idLocales);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("RESPUESTA SERVIDOR = "+response);
                       try {
                           boolean error = response.getBoolean("error");
                           if (error){
                               Toast.makeText(Ingreso_Retiro.this, "No existe el producto :(", Toast.LENGTH_SHORT).show();
                               return;
                               // :)
                           }
                           Intent intent = new Intent(Ingreso_Retiro.this, menu_seleccionar_accion.class);
                           intent.putExtra("idLocales", idLocales);
                           startActivity(intent);
                       }catch (JSONException err){
                           err.printStackTrace();
                       }
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
                String insumo = txtinsumo.getText().toString();
                String cantidad = txtcantidad.getText().toString();
                String tipoMovimiento = tipo;

                JSONObject jsonMovimiento = new JSONObject();
                try {
                    jsonMovimiento.put("idLocal", Integer.parseInt(idLocales));
                    jsonMovimiento.put("insumo",insumo);
                    jsonMovimiento.put("cantidad", Integer.parseInt(cantidad));
                    jsonMovimiento.put("tipoMovimiento", tipoMovimiento);
                    jsonMovimiento.put("fecha", fechaMovimiento);
                    System.out.println("Json Mov ="+jsonMovimiento );
                }catch (JSONException err){
                    err.printStackTrace();
                }
                return  jsonMovimiento.toString().getBytes();
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void limpiarCajas(){
        txtfecha.setText("");
        txtcantidad.setText("");
        txtinsumo.setText("");
    }
}