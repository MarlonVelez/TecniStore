package com.example.tienda_op_2;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.adapter.CarritoAdapter;
import com.example.tienda_op_2.modelo.Carrito;

import java.util.ArrayList;


public class CarritoCompras extends AppCompatActivity{

    Toolbar toolbar;
    RecyclerView listaCarrito;
    CarritoAdapter carritoAdapter;
    ArrayList<Carrito> carritoArrayList= new ArrayList<>();

    String nombre, precio, descripcion, cantidadCompra;
    String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compras);
        setTitle("Carrito");

        //Referencias UI
        toolbar= findViewById(R.id.toolBar);
        listaCarrito= findViewById(R.id.listaCarrito);

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

        //AQUI RECIVO LOS DATOS ENVIADOS DESDE EL DETALLE DE PRODCUTO AL PRECIONAR EN EL BOTON AÑADIR AL CARRITO///////
        Intent i = getIntent();

        nombre = i.getStringExtra("nombreProducto");
        image = i.getStringExtra("imagenProducto");
        precio = i.getStringExtra("precioProducto");
        descripcion = i.getStringExtra("descripcionProducto");
        cantidadCompra = i.getStringExtra("cantidadCompraProducto");
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //PRUEBA PARA COMPROBAR QUE ESTEN INGRESANDO DATOS NOTA: NO ESTAN INGRESANDO DATOS Y SALTA UN EXEPCION POR ESO //
        System.out.println("NOMBRE: "+nombre);
        System.out.println("DESCRIPCION: "+descripcion);
        System.out.println("PRECIO: "+precio);
        System.out.println("CANTIDAD: "+cantidadCompra);
        System.out.println("IMAGEN: "+image);

        Carrito carrito= new Carrito();
        carrito.setNombreProducto(nombre);
        carrito.setDescripcionProducto(descripcion);
        carrito.setPrecioProducto(Double.parseDouble(precio));
        carrito.setCatidadProducto(Integer.parseInt(cantidadCompra));
        carrito.setImgProducto(image);

        carritoArrayList.add(carrito);

        for (int j = 0; j < carritoArrayList.size(); j++) {
            Toast.makeText(this, "NOMBRE: "+carritoArrayList.get(j).getNombreProducto(), Toast.LENGTH_LONG).show();
        }

    }

}