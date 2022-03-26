package com.example.tienda_op_2.modelo;

public class Categoria {
    int id;
    String imgId;
    String nombre;

    public Categoria(int id, String imgId, String nombre) {
        this.id = id;
        this.imgId = imgId;
        this.nombre=nombre;
    }

    public Categoria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
