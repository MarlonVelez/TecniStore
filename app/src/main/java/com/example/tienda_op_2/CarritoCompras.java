package com.example.tienda_op_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.carga_de_datos.CargaProductos;


public class CarritoCompras extends AppCompatActivity{

    Toolbar toolbar;
    TextView txtTotal;
    RecyclerView listaCarrito;
    Button btnComprarCarrito, btnCancelarCarrito;

    CardView cardCarritoVacio;
    LottieAnimationView imgCarritoVacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compras);
        setTitle("Mi Carrito");

        //Referencias UI
        toolbar= findViewById(R.id.toolBar);
        listaCarrito= findViewById(R.id.listaCarrito);
        txtTotal= findViewById(R.id.txtTotalCompra);
        btnComprarCarrito= findViewById(R.id.btnCompraAhoraCarrito);
        btnCancelarCarrito= findViewById(R.id.btnCancelarCarrito);
        cardCarritoVacio= findViewById(R.id.card_carritoVacio);
        imgCarritoVacio= findViewById(R.id.imgCarritoVacio);

        imgCarritoVacio.setAnimation(R.raw.carrito_vacio);
        imgCarritoVacio.playAnimation();
        imgCarritoVacio.setRepeatCount(2000);

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

        btnComprarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pago= new Intent(CarritoCompras.this, VentanaPago.class);
                startActivity(pago);
            }
        });

        btnCancelarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Aqui validamos que el Recycler view tenga items, si no los tiene la operacion de "Borar carrito"
                * no podra efectuarse, caso contrario se procedera a ejecutar el codigo del else*/
                if(listaCarrito.getAdapter() != null){
                    //De esta manera sabes si tu RecyclerView está vacío
                    if(listaCarrito.getAdapter().getItemCount()==0) {
                        //Aquí muestras el mensaje
                        Toast.makeText(CarritoCompras.this, "No hay elementos que borrar", Toast.LENGTH_SHORT).show();
                    }else {
                        /*Mostramos un mensaje para alartar al usuario de la accion que esta tomando*/
                        new AlertDialog.Builder(CarritoCompras.this)
                                .setIcon(R.drawable.icon_warning)
                                .setTitle("Advertencia")
                                .setMessage("¿Estas seguro de cancelar esta compra?"
                                        +"\nSi lo haces todos los productos que haya añadido al carrito se eliminaran")
                                //Boton de Si (Se supone: El Usurio es consiente de lo que esta haciendo)
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //Aqui simplemente mandamos a llamar al metodo de eliminar de nuestra BD SQLite
                                        SQLiteOpenHelper base = new SQLiteOpenHelper(CarritoCompras.this);
                                        base.eliminarCarrito(null);
                                        //Al final de la ejecucion de la accion se muestra un mensaje
                                        Toast.makeText(CarritoCompras.this, "El carrito de comprar se ha eliminado", Toast.LENGTH_LONG).show();
                                    }
                                })
                                //Boton de No (Se supone: El Usurio se retracto o entro en cuenta de una mala accion)
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //EN ESTE CASO NO ES NECESARIO HACER NADA
                                    }
                                }).show();
                    }

                }
            }
        });


        CargaProductos datosProductos= new CargaProductos(this, listaCarrito, txtTotal, cardCarritoVacio, imgCarritoVacio);
        datosProductos.listarProductosCarrito(null, "Carrito");
    }


    public void refrescar(View view, String nombre){
        SQLiteOpenHelper base = new SQLiteOpenHelper(view.getContext());
        base.eliminarCarrito(nombre);

        /*CargaProductos datosProductos= new CargaProductos(this, listaCarrito, myTextView);
        datosProductos.listarProductosCarrito(null);*/

        /*FALTA QUE DESPUES DE ELIMINAR SE REFRESQUE LA LISTA AUTOMATICAMENTE*/

        /*¿COMO PUEDO HACER REFERENCIA A UN ELEMENTO FUERA DEL METODO ONCREATE O DESDE OTRA CLASE SIN QUE ME SAlGA LA SIGUIENTE EXEPCION
        * Attempt to invoke virtual method 'java.io.File android.content.Context.getDatabasePath(java.lang.String)' on a null object referenc*/
    }

    public void editarCarrito(View view, String cantidad, String nombre){
        SQLiteOpenHelper base = new SQLiteOpenHelper(view.getContext());
        base.editarCarrito(nombre, cantidad);
    }
}