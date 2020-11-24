package com.bolapa.listadelacompra;

public class Producto {

    String producto;
    int cantidad;
    Double preciounitario;

    public Producto(String producto, int cantidad, double preciounitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.preciounitario = preciounitario;
    }

    public Producto() {
        this.producto = "";
        this.cantidad = 0;
        this.preciounitario = 0.0;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(Double preciounitario) {
        this.preciounitario = preciounitario;
    }


}