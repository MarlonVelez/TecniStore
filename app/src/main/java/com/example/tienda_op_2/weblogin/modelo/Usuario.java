package com.example.tienda_op_2.weblogin.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("idUsuario")
    @Expose
    private Integer idUsuario;

    @SerializedName("tipoUsuario")
    @Expose
    private String tipoUsuario;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("clave")
    @Expose
    private String clave;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String tipoUsuario, String usuario, String clave) {
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}

