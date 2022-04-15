package com.example.tienda_op_2.weblogin.utildades;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User {

    public static Retrofit getUser(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
