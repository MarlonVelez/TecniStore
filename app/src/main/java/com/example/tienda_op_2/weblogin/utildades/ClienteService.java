package com.example.tienda_op_2.weblogin.utildades;

import com.example.tienda_op_2.weblogin.modelo.Cliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClienteService {
    @POST("cliente")
    Call<Cliente> addCliente(@Body Cliente cliente);
}
