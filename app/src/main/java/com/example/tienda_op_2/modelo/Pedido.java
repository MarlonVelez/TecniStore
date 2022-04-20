package com.example.tienda_op_2.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Pedido {

    @SerializedName("idPedido")
    @Expose
    private Integer idPedido;

    @SerializedName("idCliente")
    @Expose
    private Integer idCliente;

    @SerializedName("totalGeneral")
    @Expose
    private double totalGeneral;

    @SerializedName("despachado")
    @Expose
    private String despachado;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    public Pedido(Integer idPedido, Integer idCliente, double totalGeneral, String despachado, String fecha) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.totalGeneral = totalGeneral;
        this.despachado = despachado;
        this.fecha = fecha;
    }

    public  Pedido(){

    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(double totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public String getDespachado() {
        return despachado;
    }

    public String isDespachado() {
        return despachado;
    }

    public void setDespachado(String despachado) {this.despachado = despachado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
