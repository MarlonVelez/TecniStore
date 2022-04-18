package com.example.tienda_op_2.api;

import com.example.tienda_op_2.Services.PedidoService;


public class apiPedido {

    public static final String BASE_URL = "https://tecnistoreaapi.rj.r.appspot.com:443/";

    public static PedidoService getpedidoService(){
        return servicioApi.getRetro(BASE_URL).create(PedidoService.class);
    }
}
