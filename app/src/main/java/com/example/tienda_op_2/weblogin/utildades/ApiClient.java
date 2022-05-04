package com.example.tienda_op_2.weblogin.utildades;

public class ApiClient {
    public static final String URL = "http://192.168.18.245:8080/";

    public static ClienteService getClienteService() {
        return Client.getClient(URL).create(ClienteService.class);
    }
}
