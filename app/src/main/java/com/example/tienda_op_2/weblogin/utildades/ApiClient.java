package com.example.tienda_op_2.weblogin.utildades;

public class ApiClient {
    public static final String URL = "https://tecnistoreaapi.rj.r.appspot.com:443/";

    public static ClienteService getClienteService() {
        return Client.getClient(URL).create(ClienteService.class);
    }
}
