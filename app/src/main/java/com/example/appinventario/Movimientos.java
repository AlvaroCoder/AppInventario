package com.example.appinventario;

public class Movimientos {
    private String fecha,insumo;
    private int cantidad;
    private int tipo;
    private String tipoMovimiento="";
    public Movimientos(String fecha, String insumo, int  cantidad, int tipo) {
        this.fecha = fecha;
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }
    public Movimientos(String fecha, String insumo, String cantidad, String tipo) {
        this.fecha = fecha;
        this.insumo = insumo;
        this.cantidad = Integer.parseInt(cantidad);
        this.tipoMovimiento = tipo;
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

    public String getTipoMovimiento(){return  this.tipoMovimiento;}
    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipoMovimiento;
    }


    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
