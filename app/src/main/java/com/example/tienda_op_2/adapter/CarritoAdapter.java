package com.example.tienda_op_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.R;
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
    public void onBindViewHolder(@NonNull @NotNull CarritoViewHolder holder, int position) {

        holder.nombre.setText(itemList.get(position).getNombreProducto());
        holder.descripcion.setText(itemList.get(position).getDescripcionProducto());
        holder.precio.setText("$"+String.valueOf(itemList.get(position).getPrecioProducto()));
        holder.cantidad.setText(String.valueOf(itemList.get(position).getCatidadProducto()));

        Picasso.get()
                .load(itemList.get(position).getImgProducto())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProductoCarrito);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public static class CarritoViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre, descripcion, precio, cantidad;
        private ImageView imgProductoCarrito;

        public CarritoViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.txt_nombreProduc_carrito);
            descripcion= itemView.findViewById(R.id.txt_descProduc_carrito);
            precio= itemView.findViewById(R.id.txt_precioProduc_carrito);
            cantidad= itemView.findViewById(R.id.txt_cantidadProductoCarrito);
            imgProductoCarrito= itemView.findViewById(R.id.imagenProductoCarrito);

        }
    }


}
