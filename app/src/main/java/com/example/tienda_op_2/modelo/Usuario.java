package com.example.tienda_op_2.modelo;

public class Usuario {
    private String  clave, usuario, tipoUsuario;
    private int idUsuario, idCliente;

    public Usuario(String clave, String usuario, String tipoUsuario, int idUsuario, int idCliente) {
        this.clave = clave;
        this.usuario = usuario;
        this.tipoUsuario = tipoUsuario;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
    }

    public Usuario() {
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
