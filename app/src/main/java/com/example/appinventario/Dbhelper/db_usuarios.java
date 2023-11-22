package com.example.appinventario.Dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class db_usuarios extends ConexionSqLite {
    Context context;
    public db_usuarios(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long insertarUsuarios(String usuario, String contraseña){
        long id=0;
        try{
            ConexionSqLite conexion=new ConexionSqLite(context);
            SQLiteDatabase db=conexion.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("usuario",usuario);
            values.put("contraseña",contraseña);

            id= db.insert(TABLE_USUARIOS,null,values);

        }catch(Exception ex){
            ex.toString();
        }
        return id;

    }
}
