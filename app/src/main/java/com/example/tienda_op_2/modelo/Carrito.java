package com.example.tienda_op_2.modelo;

public class Carrito {

    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private int catidadProducto, stock;
    private String imgProducto;

    public Carrito(String nombreProducto, String descripcionProducto, double precioProducto, int catidadProducto, String imgProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.catidadProducto = catidadProducto;
        this.imgProducto = imgProducto;
    }

    public Carrito() {
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCatidadProducto() {
        return catidadProducto;
    }

    public void setCatidadProducto(int catidadProducto) {
        this.catidadProducto = catidadProducto;
    }

    public String getImgProducto() {
        return imgProducto;
    }

    public void setImgProducto(String imgProducto) {
        this.imgProducto = imgProducto;
    }
}
