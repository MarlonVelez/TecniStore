package com.example.tienda_op_2.modelo;

public class ItemLayout {
    private int idItem;
    private String nombreLayout;

    public ItemLayout(int idItem, String nombreLayout) {
        this.idItem = idItem;
        this.nombreLayout = nombreLayout;
    }

    public ItemLayout() {
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNombreLayout() {
        return nombreLayout;
    }

    public void setNombreLayout(String nombreLayout) {
        this.nombreLayout = nombreLayout;
    }
}
