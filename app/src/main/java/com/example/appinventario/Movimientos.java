package com.example.appinventario;

public class Movimientos {
    private String fecha,insumo;
    private float cantidad;
    private int tipo;

    public Movimientos(String fecha, String insumo, float cantidad, int tipo) {
        this.fecha = fecha;
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
