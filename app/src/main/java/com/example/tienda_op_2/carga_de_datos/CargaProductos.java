package com.example.tienda_op_2.carga_de_datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.tienda_op_2.CarritoCompras;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.adapter.CarritoAdapter;
import com.example.tienda_op_2.adapter.ListaComprasAdapter;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.modelo.Carrito;
import com.example.tienda_op_2.modelo.ItemLayout;

import java.util.ArrayList;



public class CargaProductos {

    Context context;
    RecyclerView recyclerView;
    CarritoAdapter carritoAdapter;
    ListaComprasAdapter listaComprasAdapter;
    TextView txtTotal;
    CardView cardCarritoVacio;
    LottieAnimationView imgCarritoVacio;
    ArrayList<Carrito> arrayList= new ArrayList<>();


    public CargaProductos(Context context, RecyclerView recyclerView, TextView txtTotal, CardView cardCarritoVacio, LottieAnimationView imgCarritoVacio) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.txtTotal = txtTotal;
        this.cardCarritoVacio = cardCarritoVacio;
        this.imgCarritoVacio = imgCarritoVacio;
    }

    public CargaProductos() {
    }

    public void listarProductosCarrito(String id, String layout){
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
            if (layout.equalsIgnoreCase("Carrito")){
                if (arrayList.size()>0){
                    cardCarritoVacio.setVisibility(View.GONE);
                    mostrarCarrito(arrayList);
                    carritoAdapter.notifyDataSetChanged();
                }else{
                    cardCarritoVacio.setVisibility(View.VISIBLE);
                }

            }

            if (layout.equalsIgnoreCase("Lista Compras")){
                mostrarListaCompras(arrayList);
                listaComprasAdapter.notifyDataSetChanged();
            }



        }else{
            //Toast.makeText(context, "No hay datos registrados", Toast.LENGTH_LONG).show();
            mostrarListaCompras(arrayList);
            listaComprasAdapter.notifyDataSetChanged();
        }
    }



    public void mostrarCarrito(ArrayList<Carrito> array) {
        double total=0;

        for (int i = 0; i < array.size(); i++) {

            total=(array.get(i).getCatidadProducto()*array.get(i).getPrecioProducto())+total;
        }
        txtTotal.setText("$"+total);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        carritoAdapter = new CarritoAdapter(context,array);
        recyclerView.setAdapter(carritoAdapter);
    }

    public void mostrarListaCompras(ArrayList<Carrito> array) {
        double total=0;

        for (int i = 0; i < array.size(); i++) {

            total=(array.get(i).getCatidadProducto()*array.get(i).getPrecioProducto())+total;
        }
        txtTotal.setText("Total:"+"        $"+total);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        listaComprasAdapter = new ListaComprasAdapter(context,array);
        recyclerView.setAdapter(listaComprasAdapter);
    }
}
