package com.example.tienda_op_2;

import android.content.Intent;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.example.tienda_op_2.adapter.CarritoAdapter;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.squareup.picasso.Picasso;

public class DetalleProducto extends AppCompatActivity {

    Toolbar toolbar;
    ImageView img, btn_mas, btn_menos;
    TextView nombreProdcuto, precioProdcuto, descripcionProducto, stockProdcuto;
    EditText cantidad;
    Button btnAñadirCarrito;

    String nombre, precio, descripcion, stock;
    String image;

    CarritoAdapter carritoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        setTitle("Detalle del Producto");

        btn_mas=findViewById(R.id.btnMasDetPro);
        btn_menos=findViewById(R.id.btnMenosDetPro);
        cantidad=findViewById(R.id.txt_cantidadProductoDetPro);

        btnAñadirCarrito=findViewById(R.id.btnAñadirCarrito);

        //Referencias UI
        toolbar= findViewById(R.id.toolBar);

        //CONFIGURACION DEL TOOL BAR Y FLECHA DE RETORNO AL HOME //////////////////////
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
        //////////////////////////////////////////////////////////////////////////////////////////////

        //RECIVO DATOS MANDADOS AL SELECCIONAR UN PRODUCTO EN LA LISTA DEL HOME //////////////////////
        Intent i = getIntent();


        nombre = i.getStringExtra("name");
        image = i.getStringExtra("image");
        precio = i.getStringExtra("price");
        descripcion = i.getStringExtra("desc");
        stock = i.getStringExtra("qty");

        nombreProdcuto = findViewById(R.id.productName);
        descripcionProducto = findViewById(R.id.prodDesc);
        precioProdcuto = findViewById(R.id.prodPrice);
        img = findViewById(R.id.big_image);
        stockProdcuto = findViewById(R.id.qty);

        nombreProdcuto.setText(nombre);
        precioProdcuto.setText(precio);
        descripcionProducto.setText(descripcion);
        stockProdcuto.setText(stock);

        Picasso.get()
                .load(image)
                .error(R.mipmap.ic_launcher)
                .into(img);
        ////////////////////////////////////////////////////////////////////////////////////////////////

        //ACCIONES DE LA BARRA DE CANTIDAD DE PRODUCTO //////////////////////
        btn_mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cantidad.getText().equals(stock)) {
                    sumCantidad();
                }
            }
        });

        btn_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cantidad.getText().equals("1")){
                    resCantidad();
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////

        //ACCION AL BOTON DE AÑADIR CARRITO //////////////////////

        btnAñadirCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCarrito();
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////

    }

    private void sumCantidad(){
        int num= Integer.parseInt(cantidad.getText().toString());
        int suma= num+1;
        cantidad.setText(String.valueOf(suma));
    }

    private void resCantidad(){
        int num= Integer.parseInt(cantidad.getText().toString());
        int resta= num-1;
        cantidad.setText(String.valueOf(resta));
    }

    private void addCarrito(){

        SQLiteOpenHelper base= new SQLiteOpenHelper(this);

        //String id= idCliente.getText().toString();
        String nombreP= nombreProdcuto.getText().toString();
        String descP= descripcionProducto.getText().toString();
        String precioP= precioProdcuto.getText().toString().substring(1,precioProdcuto.getText().toString().length());
        String cantidadCompra= cantidad.getText().toString();
        String imagen= image;
        int stock= Integer.parseInt(stockProdcuto.getText().toString());

        boolean bandera= base.agregarCarrito(nombreP, descP, precioP, cantidadCompra, imagen, stock);

        if (bandera!=false){

            Toast.makeText(this, "Prodcuto añadido al carrito", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "El cliente con este id ya se encuentra registrado", Toast.LENGTH_LONG).show();

        }

    }

}