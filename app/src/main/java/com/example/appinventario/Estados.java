package com.example.appinventario;

public class Estados {
    private String insumo,medida;
    private int stock_actual,stock_min;

    public Estados(String insumo, String medida, int stock_actual, int stock_min) {
        this.insumo = insumo;
        this.medida = medida;
        this.stock_actual = stock_actual;
        this.stock_min = stock_min;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public int getStock_min() {
        return stock_min;
    }

    public void setStock_min(int stock_min) {
        this.stock_min = stock_min;
    }
}
