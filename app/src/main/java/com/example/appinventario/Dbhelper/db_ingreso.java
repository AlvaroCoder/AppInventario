package com.example.appinventario.Dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class db_ingreso extends ConexionSqLite {
    Context context;
    public db_ingreso(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long insertarIngreso(String movimiento, String insumo, int cantidad, String fecha){
        long id3=0;
        try{
            ConexionSqLite conexion=new ConexionSqLite(context);
            SQLiteDatabase db=conexion.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("movimiento",movimiento);
            values.put("insumo",insumo);
            values.put("cantidad",cantidad);
            values.put("fecha",fecha);

            id3= db.insert(TABLE_INGRESO,null,values);

        }catch(Exception ex){
            ex.toString();
        }
        return id3;

    }
}
