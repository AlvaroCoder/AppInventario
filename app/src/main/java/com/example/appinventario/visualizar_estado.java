package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class visualizar_estado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_estado);


        List<Estados> listaestados = new LinkedList<>();
        listaestados.add(new Estados("Harina","Sacos",15,10));
        listaestados.add(new Estados("Huevo","java",20,8));
        listaestados.add(new Estados("Levadura","sacos",20,10));
        listaestados.add(new Estados("Aceite","Unidades",12,5));

        RecyclerView contenedor_estado = findViewById(R.id.contenedor_estado);
        Adapter_Estados adapterEstado = new Adapter_Estados(this, listaestados);
        contenedor_estado.setHasFixedSize(true);
        contenedor_estado.setLayoutManager(new LinearLayoutManager(this));
        contenedor_estado.setAdapter(adapterEstado);
    }
}