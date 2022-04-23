package com.example.tienda_op_2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.tienda_op_2.CarritoCompras;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.carga_de_datos.CargaProductos;
import com.example.tienda_op_2.modelo.Carrito;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>{

    Context context;
    List<Carrito> itemList;
    ArrayList<Carrito> carrito=new ArrayList<>();

    public CarritoAdapter(Context context, List<Carrito> itemList) {
        System.out.println(" Carrito de comapras");
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @NotNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false);
        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CarritoViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.nombre.setText(itemList.get(position).getNombreProducto());
        holder.descripcion.setText(itemList.get(position).getDescripcionProducto());
        holder.precio.setText("$"+String.valueOf(itemList.get(position).getPrecioProducto()));
        holder.cantidad.setText(String.valueOf(itemList.get(position).getCatidadProducto()));
        holder.cantidad.setEnabled(false);



        Picasso.get()
                .load(itemList.get(position).getImgProducto())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProductoCarrito);

        //EVENTOS

        //BARRA DE CANTIDAD///////////////////////////////////////////////////////////////////
        holder.btn_mas_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Se obtiene el valor dentro de la barra de cantidad*/
               int total= Integer.parseInt( holder.cantidad.getText().toString());
                /*Se valida que el total que se obtenga de la barra de cantidad no sea mayor al stock*/
                if (total<itemList.get(position).getStock()) {
                    total= total+1;
                    holder.cantidad.setText(String.valueOf(total));

                    //Actualizamos datos en la base temporal
                    CarritoCompras carritoCompras= new CarritoCompras();
                    carritoCompras.editarCarrito(view, String.valueOf(total), holder.nombre.getText().toString());
                    CargaProductos cp= new CargaProductos();
                    carrito=cp.actualizarProductosCarrito(null);
                    cp.actulizarTotal();

                }else{
                    Toast.makeText(context, "No hay mas stock", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.btn_menos_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Se obtiene el valor dentro de la barra de cantidad*/
                int total= Integer.parseInt(holder.cantidad.getText().toString());


                /*Se valida que el total que se obtenga de la barra de cantidad no sea cero
                * ya que obviamente no se puede comprar cero articulos*/
                if (total!=0){
                    total= total-1;
                    holder.cantidad.setText(String.valueOf(total));

                    //Actualizamos datos en la base temporal
                    CarritoCompras carritoCompras= new CarritoCompras();
                    carritoCompras.editarCarrito(view, String.valueOf(total), holder.nombre.getText().toString());

                   CargaProductos cp= new CargaProductos();
                    carrito=cp.actualizarProductosCarrito(null);
                    cp.actulizarTotal();

                }

            }
        });
        //////////////////////////////////////f///////////////////////////////////////////////////////

        holder.eliminarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Este metodo solo es para poder obtener el nombre del producto
                * del item en el que le dimos eliminar****/
                CarritoCompras carritoCompras= new CarritoCompras();
                carritoCompras.refrescar(view, holder.nombre.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public static class CarritoViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre, descripcion, precio, cantidad;
        private EditText txtTotal;
        private ImageView imgProductoCarrito;
        private CheckBox checkBox;
        private ImageView btn_mas_carrito, btn_menos_carrito;
        private Button eliminarCarrito;
        private RecyclerView listaCart;

        public CarritoViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //GUARDAR ITEMS
            SQLiteOpenHelper base= new SQLiteOpenHelper(itemView.getContext());

            nombre= itemView.findViewById(R.id.txt_nombreProduc_carrito);

            descripcion= itemView.findViewById(R.id.txt_descProduc_carrito);

            precio= itemView.findViewById(R.id.txt_precioProduc_carrito);

            cantidad= itemView.findViewById(R.id.txt_cantidadProductoCarrito);

            imgProductoCarrito= itemView.findViewById(R.id.imagenProductoCarrito);

            btn_mas_carrito= itemView.findViewById(R.id.btn_mas_carrito);

            btn_menos_carrito= itemView.findViewById(R.id.btn_menos_carrito);

            eliminarCarrito= itemView.findViewById(R.id.btn_quitarCarrito);
            //base.agregarItem(R.id.btn_quitarCarrito, "itemCarrito");

            listaCart= itemView.findViewById(R.id.listaCarrito);

        }
    }

    private void EditarCarrito(){

    }

}
