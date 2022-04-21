package com.example.tienda_op_2;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
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
    int id_producto;

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
        id_producto= i.getIntExtra("id",0);
        System.out.println(id_producto+" dddddddddddddddddddddddddddddddddddd");

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
                if (!cantidad.getText().toString().equals(stockProdcuto.getText().toString())) {
                    sumCantidad();
                }
            }
        });

        btn_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!cantidad.getText().toString().equals("1")){
                    resCantidad();
                }
            }
        });
        /*VALIDACION DE ENTRADA DE CATIDAD PRODCUTO*/
        cantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*Validadciones de entrada de cantidad de producto a comprar*/
                try{
                    /*Transformamos las entradas de los edit text en enteros para moyor comodidad*/
                    int stock;
                    int cantidadInput;
                    stock= Integer.parseInt(stockProdcuto.getText().toString());
                    if (cantidad.getText().toString().equalsIgnoreCase("")) { //If para controlar exepcion al dejar el edit text vacio
                        cantidadInput=0;
                    }else {
                        cantidadInput= Integer.parseInt(cantidad.getText().toString());
                    }

                    /*Validamos que la cantidad ingresada no supere al stock de la tienda*/
                    if (cantidadInput>stock){
                        new AlertDialog.Builder(DetalleProducto.this)
                                .setIcon(R.drawable.icon_warning)
                                .setTitle("¡Stock superado!")
                                .setMessage("El stock de este producto ha sido superado, por favor ingrese una cantidad menor o igual a la de las unidades disponibles")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                })
                                .show();
                        btnAñadirCarrito.setEnabled(false);
                        btnAñadirCarrito.setTextColor(R.color.rojo_eliminar);
                    }else {
                        btnAñadirCarrito.setEnabled(true);
                        btnAñadirCarrito.setTextColor(R.color.white);
                    }

                }catch (NumberFormatException ex){ /*capturamos el error si es que el usuario ingrese cualquier valor que no sea un numero entero*/
                    new AlertDialog.Builder(DetalleProducto.this)
                            .setIcon(R.drawable.icon_warning)
                            .setTitle("¡Valor no soportado!")
                            .setMessage("Por favor ingrese solo numeros enteros")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .show();
                    cantidad.setText("1");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //////////////////////////////////////////////////////////////////////////////////////////////

        //ACCION AL BOTON DE AÑADIR CARRITO //////////////////////

        btnAñadirCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cantidad.getText().toString().equalsIgnoreCase("") || cantidad.getText().toString().equalsIgnoreCase("0")) {
                    new AlertDialog.Builder(DetalleProducto.this)
                            .setIcon(R.drawable.icon_warning)
                            .setTitle("¡Cantidad nula!")
                            .setMessage("Antes de añadir al carrito asegurate de ingresar una catidad diferente de 0")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .show();
                }else{
                    addCarrito(true);
                }

            }
        });


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

    private void addCarrito(boolean estado){

        SQLiteOpenHelper base= new SQLiteOpenHelper(this);

        //String id= idCliente.getText().toString();

        String nombreP= nombreProdcuto.getText().toString();
        String descP= descripcionProducto.getText().toString();
        String precioP= precioProdcuto.getText().toString().substring(1,precioProdcuto.getText().toString().length());
        String cantidadCompra= cantidad.getText().toString();
        String imagen= image;
        int stock= Integer.parseInt(stockProdcuto.getText().toString());

        System.out.println(id_producto+ " 77777777777777777777777777");



        boolean bandera= base.agregarCarrito(id_producto,nombreP, descP, precioP, cantidadCompra, imagen, stock, estado);


        if (estado!=false){
            if (bandera!=false){
                Toast.makeText(this, "Prodcuto añadido al carrito", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Este producto ya fue registrado", Toast.LENGTH_LONG).show();
            }
        }
    }


}