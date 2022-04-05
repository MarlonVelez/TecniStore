package com.example.tienda_op_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.modelo.Carrito;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListaComprasAdapter extends RecyclerView.Adapter<ListaComprasAdapter.ListasComprasViewHolder>{

    Context context;
    List<Carrito> itemList;

    public ListaComprasAdapter(Context context, List<Carrito> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @NotNull
    @Override
    public ListasComprasViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista_compras, parent, false);

        return new ListaComprasAdapter.ListasComprasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListasComprasViewHolder holder, int position) {

        holder.nombre.setText(itemList.get(position).getNombreProducto());
        holder.cantidad.setText("Unidades por comprar: "+String.valueOf(itemList.get(position).getCatidadProducto()));
        holder.precio.setText("$"+String.valueOf(itemList.get(position).getPrecioProducto()));

        Picasso.get()
                .load(itemList.get(position).getImgProducto())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProducto);

    }

    @Override
    public int getItemCount() {
         return itemList.size();
    }

    public static class ListasComprasViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre, cantidad, precio;
        private ImageView imgProducto;

        public ListasComprasViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.txtNombreProductoLista);
            cantidad= itemView.findViewById(R.id.txtCantidadProductoLista);
            precio= itemView.findViewById(R.id.txtPrecioProductoLista);
            imgProducto= itemView.findViewById(R.id.imgProductoLista);
        }
    }
}
