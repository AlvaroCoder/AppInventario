package com.example.appinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.appinventario.Dbhelper.db_ingreso;
import com.example.appinventario.Dbhelper.db_locales;

import java.util.Calendar;

public class Ingreso_Retiro extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup rdgrupo;

    private Button btnfecha,btningresar;
    private EditText txtfecha,txtinsumo,txtcantidad;
    private int dia,mes,ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_retiro);

        btnfecha=(Button)findViewById(R.id.btnfecha);
        txtfecha=(EditText)findViewById(R.id.txtfecha);
        txtinsumo=(EditText)findViewById(R.id.txtinsumo);
        txtcantidad=(EditText)findViewById(R.id.txtcantidad);
        btningresar=(Button)findViewById(R.id.btningresar);

      /*  btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db_ingreso ingreso=new db_ingreso(Ingreso_Retiro.this);
                long id=ingreso.insertarIngreso(reg2.getText().toString(),reg3.getText().toString(),reg4.getText().toString());
                if(id>0){
                    Toast.makeText(Registrar_Inventario.this,"Registro Bueno",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Registrar_Inventario.this,"Error en el Registro",Toast.LENGTH_SHORT).show();
                }
            }
        });

*/




        btnfecha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtfecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        }
        ,dia,mes,ano);
        datePickerDialog.show();
    }
    public void retro_selec_acc(View view) {
        Intent retro = new Intent(getApplicationContext(), menu_seleccionar_accion.class);
        startActivity(retro);}

    public void limpiarCajas(){
        txtfecha.setText("");
        txtcantidad.setText("");
        txtinsumo.setText("");
    }
}