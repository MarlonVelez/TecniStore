package com.example.tienda_op_2.weblogin.utildades;

public class ApiClient {
    public static final String URL = "http://10.0.2.2:8080/";

    public static ClienteService getClienteService() {
        return Client.getClient(URL).create(ClienteService.class);
    }
}
