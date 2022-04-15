package com.example.tienda_op_2.weblogin.utildades;

import com.example.tienda_op_2.weblogin.modelo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioService {

    @POST("create")
    Call<Usuario> addUsuario(@Body Usuario usuario);
}
