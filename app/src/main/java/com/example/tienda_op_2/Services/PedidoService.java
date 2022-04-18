package com.example.tienda_op_2.Services;

import com.example.tienda_op_2.modelo.Pedido;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PedidoService {
    @POST("pedido")
    Call<Pedido> addPedido(@Body Pedido pedido);
}
