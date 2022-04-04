package com.example.tienda_op_2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.CarritoCompras;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.carga_de_datos.CargaProductos;
import com.example.tienda_op_2.modelo.Carrito;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>{

    Context context;
    List<Carrito> itemList;

    public CarritoAdapter(Context context, List<Carrito> itemList) {
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
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "CHECK!! "+holder.nombre.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //BARRA DE CANTIDAD///////////////////////////////////////////////////////////////////
        holder.btn_mas_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int total= Integer.parseInt( holder.cantidad.getText().toString());

                if (total<itemList.get(position).getStock()) {
                    total= total+1;
                    holder.cantidad.setText(String.valueOf(total));
                }else{
                    Toast.makeText(context, "No hay mas stock", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.btn_menos_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total= Integer.parseInt(holder.cantidad.getText().toString());
                if (total!=0){
                    total= total-1;
                    holder.cantidad.setText(String.valueOf(total));
                }

            }
        });
        //////////////////////////////////////f///////////////////////////////////////////////////////

        holder.eliminarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            nombre= itemView.findViewById(R.id.txt_nombreProduc_carrito);
            descripcion= itemView.findViewById(R.id.txt_descProduc_carrito);
            precio= itemView.findViewById(R.id.txt_precioProduc_carrito);
            cantidad= itemView.findViewById(R.id.txt_cantidadProductoCarrito);
            imgProductoCarrito= itemView.findViewById(R.id.imagenProductoCarrito);
            checkBox= itemView.findViewById(R.id.checkbox_verificar);
            btn_mas_carrito= itemView.findViewById(R.id.btn_mas_carrito);
            btn_menos_carrito= itemView.findViewById(R.id.btn_menos_carrito);
            eliminarCarrito= itemView.findViewById(R.id.btn_quitarCarrito);
            listaCart= itemView.findViewById(R.id.listaCarrito);
        }
    }

}
