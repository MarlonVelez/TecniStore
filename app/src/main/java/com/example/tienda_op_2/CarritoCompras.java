package com.example.tienda_op_2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.adapter.CarritoAdapter;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.modelo.Carrito;

import java.util.ArrayList;


public class CarritoCompras extends AppCompatActivity{

    Toolbar toolbar;
    RecyclerView listaCarrito;
    CarritoAdapter carritoAdapter;
    ArrayList<Carrito> carritoArrayList= new ArrayList<>();

    TextView txtTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compras);
        setTitle("Mi Carrito");

        //Referencias UI
        toolbar= findViewById(R.id.toolBar);
        listaCarrito= findViewById(R.id.listaCarrito);
        txtTotal= findViewById(R.id.txtTotalCompra);

        //Configuracion de ToolBar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_btn_back_view));
        Intent home= new Intent(this, MainActivity.class);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                startActivity(home);
                finish();
            }
        });
        listarProductosCarrito();

    }
    
    public void listarProductosCarrito(){
        SQLiteOpenHelper base= new SQLiteOpenHelper(this);
        SQLiteDatabase open= base.getReadableDatabase();

        Cursor fila = open.rawQuery("select * from carrito",null );

        if (fila.moveToFirst()){
            do{
                Carrito carrito= new Carrito();

                /*carrito.setId(fila.getInt(0));
                carrito.setCedula(fila.getString(1));
                carrito.setNombre(fila.getString(2));
                carrito.setApellido(fila.getString(3));
                carrito.setEmail(fila.getString(4));
                carrito.setTelefono(fila.getString(5));*/
                
                carrito.setNombreProducto(fila.getString(1));
                carrito.setDescripcionProducto(fila.getString(2));
                carrito.setPrecioProducto(fila.getDouble(3));
                carrito.setCatidadProducto(fila.getInt(4));
                carrito.setImgProducto(fila.getString(5));

                carritoArrayList.add(carrito);
            }while (fila.moveToNext());
            base.close();
            open.close();

            mostrarCarrito(carritoArrayList);
            carritoAdapter.notifyDataSetChanged();

        }else{
            Toast.makeText(this, "No hay datos registrados", Toast.LENGTH_LONG).show();
        }
    }

    public void mostrarCarrito(ArrayList<Carrito> array) {
        double total=0;

        for (int i = 0; i < array.size(); i++) {

            total=(array.get(i).getCatidadProducto()*array.get(i).getPrecioProducto())+total;
        }
        txtTotal.setText("$"+total);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listaCarrito.setLayoutManager(layoutManager);
        carritoAdapter = new CarritoAdapter(this,array);
        listaCarrito.setAdapter(carritoAdapter);
    }

}