package com.example.tienda_op_2.validaciones;

import java.util.List;

import com.example.tienda_op_2.modelo.Producto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceRetro {
    @GET("producto")
    Call<List<Producto>> getPosts();
}
