package com.example.tienda_op_2.api;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.adapter.ProductoAdapter;
import com.example.tienda_op_2.modelo.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class apiProductos{

    ProductoAdapter productoAdapter;
    ArrayList<Producto> productos= new ArrayList<>();
    Context context;
    RecyclerView listProductos;

    public apiProductos(Context context, RecyclerView listProductos) {
        this.context = context;
        this.listProductos = listProductos;
        mostrarPriductos(productos);
    }

    public ArrayList<Producto>  parseJSON(JSONArray myJSON) throws JSONException {

        for (int i =0; i<myJSON.length(); i++){

            JSONObject jsonObject= null;
            //Users user= new Users();
            Producto producto= new Producto();
            jsonObject= myJSON.getJSONObject(i);
            producto.setIdProducto(jsonObject.getInt("idProducto"));
            producto.setNombre(jsonObject.getString("nombre"));
            producto.setDescripcion(jsonObject.getString("descripcion"));
            producto.setStock(jsonObject.getInt("stock"));
            producto.setPrecio("$"+String.valueOf(jsonObject.getDouble("precio")));
            producto.setFotoUrl(jsonObject.getString("fotoUrl"));
            productos.add(producto);

        }
        mostrarPriductos(productos);
        return  productos;

    }

    public void mostrarPriductos(List<Producto> dataList ) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        listProductos.setLayoutManager(layoutManager);
        productoAdapter = new ProductoAdapter(context,dataList);
        listProductos.setAdapter(productoAdapter);
    }
}
