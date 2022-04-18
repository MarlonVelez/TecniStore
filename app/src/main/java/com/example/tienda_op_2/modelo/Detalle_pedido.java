package com.example.tienda_op_2.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detalle_pedido {

    @SerializedName("idDetalle")
    @Expose
    private Integer idDetalle;

    @SerializedName("idPedido")
    @Expose
    private Integer idPedido;

    @SerializedName("idProducto")
    @Expose
    private Integer idProducto;

    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;

    @SerializedName("precioUnitario")
    @Expose
    private double precioUnitario;


    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
