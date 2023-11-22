package com.example.appinventario.Dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appinventario.utilidades.utilidades;

public class ConexionSqLite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=3;
    private static final String DATABASE_NOMBRE="base.db";
    public static final String TABLE_USUARIOS="t_usuarios";
    public static final String TABLE_LOCALES="t_locales";
    public static final String TABLE_INSUMOS="t_insumos";
    public static final String TABLE_INGRESO="t_ingreso";

    public ConexionSqLite(@Nullable Context context) {
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_USUARIOS+"(" +
                "idusuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario TEXT NOT NULL," +
                "contraseña TEXT NOT NULL)");
        db.execSQL("CREATE TABLE "+TABLE_LOCALES+"(" +
                "idlocales INTEGER PRIMARY KEY AUTOINCREMENT," +
                "local TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "distrito TEXT NOT NULL," +
                "ciudad TEXT NOT NULL)");
        db.execSQL("CREATE TABLE "+TABLE_INSUMOS+"(" +
                "idinsumo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "insumo TEXT NOT NULL," +
                "medida TEXT NOT NULL," +
                "stock INTEGER NOT NULL," +
                "stockmin INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE "+TABLE_INGRESO+"(" +
                "idmovimiento INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipo_mov TEXT NOT NULL," +
                "insumo TEXT NOT NULL," +
                "cantidad INTEGER NOT NULL," +
                "fecha TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+ TABLE_USUARIOS);
        db.execSQL("DROP TABLE " +TABLE_LOCALES);
        db.execSQL("DROP TABLE " +TABLE_INSUMOS);
        onCreate(db);
    }


}
