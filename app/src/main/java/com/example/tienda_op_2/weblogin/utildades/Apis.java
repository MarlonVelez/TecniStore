package com.example.tienda_op_2.weblogin.utildades;

public class Apis {

    public static final String BASE_URL = "https://tecnistoreaapi.rj.r.appspot.com:443/usuario/";


    public static UsuarioService getUsuarioService() {
        return User.getUser(BASE_URL).create(UsuarioService.class);
    }
}
