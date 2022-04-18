package com.example.tienda_op_2.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Cliente {


    @SerializedName("apellido")
    @Expose
    private String apellido;
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("estadoCliente")
    @Expose
    private String estadoCliente;
    @SerializedName("fechaNacimiento")
    @Expose
    private Date fechaNacimiento;
    @SerializedName("idCliente")
    @Expose
    private Integer idCliente;
    @SerializedName("idUsuario")
    @Expose
    private Integer idUsuario;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("telefono")
    @Expose
    private String telefono;



    /**
     * No args constructor for use in serialization
     *
     */
    public Cliente() {
    }

    /**
     *
     * @param estadoCliente
     * @param idCliente
     * @param fechaNacimiento
     * @param cedula
     * @param apellido
     * @param correo
     * @param idUsuario
     * @param direccion
     * @param telefono
     * @param nombre
     */
    public Cliente(String apellido, String cedula, String correo, String direccion, String estadoCliente, Date fechaNacimiento, Integer idCliente, Integer idUsuario, String nombre, String telefono) {
        super();
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.direccion = direccion;
        this.estadoCliente = estadoCliente;
        this.fechaNacimiento = fechaNacimiento;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
