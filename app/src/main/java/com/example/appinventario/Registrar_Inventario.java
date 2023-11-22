package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appinventario.Dbhelper.db_locales;

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
                db_locales local=new db_locales(Registrar_Inventario.this);
                long id=local.insertarLocales(reg1.getText().toString(),reg2.getText().toString(),reg3.getText().toString(),reg4.getText().toString());
                if(id>0){
                    Toast.makeText(Registrar_Inventario.this,"Registro Bueno",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Registrar_Inventario.this,"Error en el Registro",Toast.LENGTH_SHORT).show();
                }

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