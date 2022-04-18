
package com.example.tienda_op_2.api;
import android.content.Context;
import com.example.tienda_op_2.modelo.Cliente;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class apiClientes {

    static public ArrayList<Cliente>  listacliente= new ArrayList<>();
    Context context;

    public apiClientes(Context context) {
        this.context = context;
    }

    public ArrayList<Cliente>  parseJSON(JSONArray myJSON) throws JSONException {
        listacliente.clear();
        for (int i =0; i<myJSON.length(); i++){

            JSONObject jsonObject= null;
            //Users user= new Users();
            Cliente cl=new Cliente();
            jsonObject= myJSON.getJSONObject(i);
            cl.setCedula(jsonObject.getString("cedula"));
            cl.setIdCliente(jsonObject.getInt("idCliente"));
            listacliente.add(cl);
        }

        return  listacliente;
    }



}
