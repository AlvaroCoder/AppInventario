package com.example.appinventario.Dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class db_insumos extends ConexionSqLite {
    Context context;
    public db_insumos(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long insertarInsumo(String insumo, String medida, int stock, int stockmin){
        long id2=0;
        try{
            ConexionSqLite conexion=new ConexionSqLite(context);
            SQLiteDatabase db=conexion.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("insumo",insumo);
            values.put("medida",medida);
            values.put("stock",stock);
            values.put("stockmin",stockmin);

            id2= db.insert(TABLE_INSUMOS,null,values);

        }catch(Exception ex){
            ex.toString();
        }
        return id2;

    }
}

