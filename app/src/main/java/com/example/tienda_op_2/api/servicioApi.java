package com.example.tienda_op_2.api;

import android.content.Context;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tienda_op_2.adapter.ProductoAdapter;
import com.example.tienda_op_2.modelo.Cliente;
import com.example.tienda_op_2.modelo.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;

public class servicioApi {

    public static Context context;
    String endPoint;
    ArrayList<Producto> productos = new ArrayList<>();

    ArrayList<Cliente> clientes = new ArrayList<>();

    public servicioApi() {

    }

    public servicioApi(Context context, String endPoint) {
        this.context = context;
        this.endPoint = endPoint;
    }

    public servicioApi(Context context) {
        this.context = context;
    }

    public void datosList(RecyclerView recyclerView) {
        String URL = "https://tecnistoreaapi.rj.r.appspot.com/" + endPoint;

        JsonArrayRequest usersJSON = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                /*Swicht para identificar el end point y sacar los datos*/

                try {
                    switch (endPoint) {
                        case "producto":
                            apiProductos serviProducto = new apiProductos(context, recyclerView);
                            serviProducto.parseJSON(response);
                            break;
                        case "categoria":
                            apiCategorias serviCategoria = new apiCategorias(context, recyclerView);
                            serviCategoria.parseJSON(response);
                            break;
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                    Toast.makeText(null, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(context).add(usersJSON);
    }

    public ArrayList<Producto> listarCategorias(RecyclerView recyclerView, int id) {

        String URL = "https://tecnistoreaapi.rj.r.appspot.com/producto/categoria/" +id;

        JsonArrayRequest usersJSON = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    apiProductos serviProducto = new apiProductos(context, recyclerView);
                    productos=serviProducto.parseJSON(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(null, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(context).add(usersJSON);

        return productos;
    }



   public void listarClientes() {

        System.out.println("liatar clienmtessssssssssssss");
        String URL = "https://tecnistoreaapi.rj.r.appspot.com/cliente";

        JsonArrayRequest usersJSON = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    apiClientes apiClientes = new apiClientes(context);
                    clientes = apiClientes.parseJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(null, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(context).add(usersJSON);
    }



    public static Retrofit getRetro(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

}
