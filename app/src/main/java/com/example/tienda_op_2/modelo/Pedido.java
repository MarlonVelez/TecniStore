package com.example.tienda_op_2.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Pedido {

    @SerializedName("idCliente")
    @Expose
    private Integer idCliente;

    @SerializedName("totalGeneral")
    @Expose
    private Integer totalGeneral;

    @SerializedName("despachado")
    @Expose
    private String despachado;

    @SerializedName("fecha")
    @Expose
    private Date fecha;


    public Pedido(Integer idCliente, Integer totalGeneral, String despachado, Date fecha) {
        this.idCliente = idCliente;
        this.totalGeneral = totalGeneral;
        this.despachado = despachado;
        this.fecha = fecha;
    }

    public  Pedido(){

    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(Integer totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public String isDespachado() {
        return despachado;
    }

    public void setDespachado(String despachado) {this.despachado = despachado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
