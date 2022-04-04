package com.example.tienda_op_2.carga_de_datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.CarritoCompras;
import com.example.tienda_op_2.adapter.CarritoAdapter;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.modelo.Carrito;
import com.example.tienda_op_2.modelo.ItemLayout;

import java.util.ArrayList;



public class CargaProductos {

    Context context;
    RecyclerView listaCarrito;
    CarritoAdapter carritoAdapter;
    TextView txtTotal;
    ArrayList<Carrito> carritoArrayList= new ArrayList<>();


    public CargaProductos(Context context, RecyclerView listaCarrito, TextView txtTotal) {
        this.context = context;
        this.listaCarrito = listaCarrito;
        this.txtTotal = txtTotal;
    }

    public CargaProductos() {
    }

    public void listarProductosCarrito(String id){
        SQLiteOpenHelper base= new SQLiteOpenHelper(context);
        SQLiteDatabase open= base.getReadableDatabase();
        Cursor fila=null;
        if (id==null){
            fila = open.rawQuery("select * from carrito",null );
        }else {
            fila = open.rawQuery("select * from carrito where id="+id,null );
        }

        if (fila.moveToFirst()){
            do{
                Carrito carrito= new Carrito();


                carrito.setNombreProducto(fila.getString(1));
                carrito.setDescripcionProducto(fila.getString(2));
                carrito.setPrecioProducto(fila.getDouble(3));
                carrito.setCatidadProducto(fila.getInt(4));
                carrito.setImgProducto(fila.getString(6));
                carrito.setStock(fila.getInt(5));

                carritoArrayList.add(carrito);
            }while (fila.moveToNext());
            base.close();
            open.close();

            mostrarCarrito(carritoArrayList);
            carritoAdapter.notifyDataSetChanged();

        }else{
            Toast.makeText(context, "No hay datos registrados", Toast.LENGTH_LONG).show();
        }
    }



    public void mostrarCarrito(ArrayList<Carrito> array) {
        double total=0;

        for (int i = 0; i < array.size(); i++) {

            total=(array.get(i).getCatidadProducto()*array.get(i).getPrecioProducto())+total;
        }
        txtTotal.setText("$"+total);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        listaCarrito.setLayoutManager(layoutManager);
        carritoAdapter = new CarritoAdapter(context,array);
        listaCarrito.setAdapter(carritoAdapter);
    }
}
