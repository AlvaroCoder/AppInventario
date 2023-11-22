package com.example.appinventario.Dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class db_locales extends ConexionSqLite {
    Context context;
    public db_locales(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long insertarLocales(String local, String direccion, String distrito, String ciudad){
        long id1=0;
        try{
            ConexionSqLite conexion=new ConexionSqLite(context);
            SQLiteDatabase db=conexion.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("local",local);
            values.put("direccion",direccion);
            values.put("distrito",distrito);
            values.put("ciudad",ciudad);

            id1= db.insert(TABLE_LOCALES,null,values);

        }catch(Exception ex){
            ex.toString();
        }
        return id1;

    }
}
