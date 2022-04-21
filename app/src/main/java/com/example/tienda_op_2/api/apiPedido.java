package com.example.tienda_op_2.api;

import android.content.Context;
import android.widget.Toast;
import com.example.tienda_op_2.Services.DetalleService;
import com.example.tienda_op_2.Services.PedidoService;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.modelo.Carrito;
import com.example.tienda_op_2.modelo.Detalle_pedido;
import com.example.tienda_op_2.modelo.Pedido;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.ArrayList;
import java.util.List;


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

    public void parseJSON(JSONArray myJSON) throws JSONException {
        listaPedido.clear();
        for (int i =0; i<myJSON.length(); i++){
            JSONObject jsonObject= null;
            Pedido p=new Pedido();
            jsonObject= myJSON.getJSONObject(i);
            p.setIdPedido(jsonObject.getInt("idPedido"));
            listaPedido.add(p);
        }
        System.out.println(listaPedido.size()+" pedidooooooooooooooooooooooo ssssssssssssssssssssssssssssssssssssssssssssssss");
        cargarDatosDetalle();
    }



    public void cargarDatosDetalle(){
        int id_pedido;
        List<Carrito> lista_car=new ArrayList<>();

        /*for(int i=0; i<listaPedido.size();i++){

        }*/

        SQLiteOpenHelper base=new SQLiteOpenHelper(context);
        lista_car=base.listar();
        id_pedido=listaPedido.get(listaPedido.size()-1).getIdPedido();

        for(int i=0;i<lista_car.size();i++){
            Detalle_pedido det=new Detalle_pedido();
            det.setIdProducto(lista_car.get(i).getId_producto());
            System.out.println(lista_car.get(i).getId_producto()+" id producto");
            det.setCantidad(lista_car.get(i).getCatidadProducto());
            System.out.println(lista_car.get(i).getCatidadProducto()+" cantidad");
            det.setIdPedido(id_pedido);
            det.setPrecioUnitario(lista_car.get(i).getPrecioProducto());
            System.out.println(lista_car.get(i).getPrecioProducto()+" precio");
            det.setDespachado("false");
            agregarDetalle(det);
        }

    }

    DetalleService detalleService;
    public void agregarDetalle(Detalle_pedido detalle){

        detalleService= apiDetallePedido.getDetalle();
        Call<Detalle_pedido> call = detalleService.addDetalle(detalle);
        call.enqueue(new Callback<Detalle_pedido>() {
            @Override
            public void onResponse(Call<Detalle_pedido> call, retrofit2.Response<Detalle_pedido> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Detalle agregado automaticamente", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Detalle_pedido> call, Throwable t) {
                Toast.makeText(context, "Error al agregar detalle", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
