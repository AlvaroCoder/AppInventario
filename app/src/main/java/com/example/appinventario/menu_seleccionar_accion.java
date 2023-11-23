package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class menu_seleccionar_accion extends AppCompatActivity {
    private RelativeLayout ly1;
    private RelativeLayout ly2;
    private RelativeLayout ly3;
    private RelativeLayout ly4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_seleccionar_accion);

        ly1=findViewById(R.id.ly1);
        ly2=findViewById(R.id.ly2);
        ly3=findViewById(R.id.ly3);
        ly4=findViewById(R.id.ly4);

    }

    public void onClick(View view) {

        Intent intent = new Intent(getApplicationContext(), Registrar_Inventario.class);
        String idLocal = getIntent().getStringExtra("idLocales");
        intent.putExtra("idLocales", idLocal);
        startActivity(intent);
    }

    public void ingreso(View view) {

        Intent ingresa = new Intent(getApplicationContext(), Ingreso_Retiro.class);
        String idLocal = getIntent().getStringExtra("idLocales");
        ingresa.putExtra("idLocales",idLocal);
        startActivity(ingresa);
    }
    public void visest(View view){

        Intent estado=new Intent(getApplicationContext(), visualizar_estado.class);
        String idLocal = getIntent().getStringExtra("idLocales");
        estado.putExtra("idLocales", idLocal);
        startActivity(estado);
    }
    public void vismov(View view){

        Intent movimiento=new Intent(getApplicationContext(), visualizar_mov.class);
        String idLocal = getIntent().getStringExtra("idLocales");
        movimiento.putExtra("idLocales",idLocal);
        startActivity(movimiento);
    }














}