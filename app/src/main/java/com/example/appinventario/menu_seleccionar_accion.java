package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.appinventario.Dbhelper.ConexionSqLite;

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
        // Navega a la primera pantalla cuando se hace clic en layout1
        ConexionSqLite conexion=new ConexionSqLite(menu_seleccionar_accion.this);
        SQLiteDatabase db=conexion.getWritableDatabase();
        Intent intent = new Intent(getApplicationContext(), Registrar_Inventario.class);
        startActivity(intent);
    }

    public void ingreso(View view) {
        // Navega a la primera pantalla cuando se hace clic en layout2
        ConexionSqLite conexion=new ConexionSqLite(menu_seleccionar_accion.this);
        SQLiteDatabase db=conexion.getWritableDatabase();
        Intent ingresa = new Intent(getApplicationContext(), Ingreso_Retiro.class);
        startActivity(ingresa);
    }
    public void visest(View view){

        Intent estado=new Intent(getApplicationContext(), visualizar_estado.class);
        startActivity(estado);
    }
    public void vismov(View view){

        Intent movimiento=new Intent(getApplicationContext(), visualizar_mov.class);
        startActivity(movimiento);
    }














}