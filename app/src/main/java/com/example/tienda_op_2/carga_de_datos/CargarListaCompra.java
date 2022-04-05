package com.example.tienda_op_2.carga_de_datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.adapter.CarritoAdapter;
import com.example.tienda_op_2.adapter.ListaComprasAdapter;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.modelo.Carrito;

import java.util.ArrayList;

public class CargarListaCompra {

    Context context;
    RecyclerView recyclerView;
    ListaComprasAdapter listaComprasAdapter;
    TextView txtTotal;
    ArrayList<Carrito> arrayList= new ArrayList<>();

    public CargarListaCompra(Context context, RecyclerView recyclerView, ListaComprasAdapter listaComprasAdapter, TextView txtTotal, ArrayList<Carrito> arrayList) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.listaComprasAdapter = listaComprasAdapter;
        this.txtTotal = txtTotal;
        this.arrayList = arrayList;
    }

   /* public void listarProductosCarrito(String id, String layout){
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

                arrayList.add(carrito);

            }while (fila.moveToNext());
            base.close();
            open.close();
            mostrarListaCompras(arrayList);
            listaComprasAdapter.notifyDataSetChanged();

        }else{
            Toast.makeText(context, "No hay datos registrados", Toast.LENGTH_LONG).show();
        }
    }

    public void mostrarListaCompras(ArrayList<Carrito> array) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        listaComprasAdapter = new ListaComprasAdapter(context,array);
        recyclerView.setAdapter(carritoAdapter);
    }*/

}
