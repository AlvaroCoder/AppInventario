package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class visualizar_mov extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_mov);

        RecyclerView contenedor_movimientos=findViewById(R.id.contenedor_mov);

        List<Movimientos> listamovimientos = new LinkedList<>();
        listamovimientos.add(new Movimientos("10/08/2023","Harina",15,1));
        listamovimientos.add(new Movimientos("12/08/2023","Huevo",5,0));
        listamovimientos.add(new Movimientos("14/08/2023","Levadura",3,0));
        listamovimientos.add(new Movimientos("20/08/2023","Aceite",11,1));


        Adapter_Mov adapterMov = new Adapter_Mov(this, listamovimientos);
        contenedor_movimientos.setHasFixedSize(true);
        contenedor_movimientos.setLayoutManager(new LinearLayoutManager(this));
        contenedor_movimientos.setAdapter(adapterMov);
    }
}