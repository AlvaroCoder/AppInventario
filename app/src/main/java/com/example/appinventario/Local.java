package com.example.appinventario;

public class Local {
    private String nombreLocal, direccion, distrito, ciudad;
    public Local(String nombreLocal, String direccion, String distrito, String ciudad){
        this.nombreLocal = nombreLocal;
        this.direccion  = direccion;
        this.distrito = distrito;
        this.ciudad = ciudad;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
