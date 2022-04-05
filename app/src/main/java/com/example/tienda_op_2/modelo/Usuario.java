package com.example.tienda_op_2.modelo;

public class Usuario {
    private String  nombreUsuario, tipoUsuario, claveUsuario, estadoUsuario;

    public Usuario(String nombreUsuario, String tipoUsuario, String claveUsuario, String estadoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.tipoUsuario = tipoUsuario;
        this.claveUsuario = claveUsuario;
        this.estadoUsuario = estadoUsuario;
    }

    public Usuario() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }
}
