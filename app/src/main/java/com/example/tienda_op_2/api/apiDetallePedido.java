package com.example.tienda_op_2.api;

import com.example.tienda_op_2.Services.DetalleService;

public class apiDetallePedido {

    public static final String BASE_URL = "http://10.0.2.2:8080/";

    public static DetalleService getDetalle() {
        return  servicioApi.getRetro(BASE_URL).create(DetalleService.class);
    }

}
