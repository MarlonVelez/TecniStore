package com.example.tienda_op_2.weblogin.utildades;

public class Apis {

    public static final String BASE_URL = "http://10.0.2.2:8080/usuario/";


    public static UsuarioService getUsuarioService() {
        return User.getUser(BASE_URL).create(UsuarioService.class);
    }
}
