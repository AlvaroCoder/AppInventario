package com.example.appinventario.utilidades;

public class utilidades {
    public static String TABLA_USUARIO="usuarios";
    public static String CAMPO_USUARIO="usuario";
    public static String CAMPO_CONTRASEÑA="contraseña";

    public static final String TABLA_USUARIOS="CREATE TABLE"+TABLA_USUARIO+" (id INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_USUARIO+" TEXT NOT NULL, "+CAMPO_CONTRASEÑA+" TEXT NOT NULL)";
}
