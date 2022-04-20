package com.example.tienda_op_2.Services;

import com.example.tienda_op_2.modelo.Detalle_pedido;
import com.example.tienda_op_2.weblogin.modelo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DetalleService {

    @POST("detallepedido")
    Call<Detalle_pedido> addDetalle(@Body Detalle_pedido detalle);
}
