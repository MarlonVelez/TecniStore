package com.example.tienda_op_2.api;

import android.content.Context;
import com.example.tienda_op_2.Services.PedidoService;
import com.example.tienda_op_2.modelo.Cliente;
import com.example.tienda_op_2.modelo.Pedido;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class apiPedido {

    public static final String BASE_URL = "https://tecnistoreaapi.rj.r.appspot.com:443/";

    public static PedidoService getpedidoService(){
        return servicioApi.getRetro(BASE_URL).create(PedidoService.class);
    }

    static public ArrayList<Pedido> listaPedido= new ArrayList<>();
    Context context;

    public apiPedido(Context context) {
        this.context = context;
    }

    public apiPedido() {
    }

    public ArrayList<Pedido>  parseJSON(JSONArray myJSON) throws JSONException {
        listaPedido.clear();
        for (int i =0; i<myJSON.length(); i++){
            JSONObject jsonObject= null;
            Pedido p=new Pedido();
            jsonObject= myJSON.getJSONObject(i);
            p.setIdPedido(jsonObject.getInt("idPedido"));
            listaPedido.add(p);
        }
        System.out.println(listaPedido.size()+" ssssssssssssssssssssssssssssssssssssssssssssssss");
        return  listaPedido;
    }

}
