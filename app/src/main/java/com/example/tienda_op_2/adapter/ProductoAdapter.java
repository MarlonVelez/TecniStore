package com.example.tienda_op_2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.DetalleProducto;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.modelo.Producto;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    Context context;
     List<Producto> prodcutoList;
     List<Producto> listaOriginal;


    public ProductoAdapter(Context context, List<Producto> prodcutoList) {
        this.context = context;
        this.prodcutoList = prodcutoList;
        this.listaOriginal = new ArrayList<>();
        listaOriginal.addAll(prodcutoList);
        System.out.println(listaOriginal.size() + " contructor");
    }



    public void filtrado(String txt_buscar) {

        int longitud = txt_buscar.length();

        if (longitud == 0) {
            prodcutoList.clear();
            prodcutoList.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Producto> coleccion = prodcutoList.stream().filter(i -> i.getNombre().toLowerCase().
                        contains(txt_buscar.toLowerCase())).collect(Collectors.toList());
                prodcutoList.clear();
                prodcutoList.addAll(coleccion);
                System.out.println(coleccion.size() + " coleeciio");
                System.out.println(prodcutoList.size() + " pproducto tamño");
            } else {
                for (Producto p : listaOriginal) {
                    if (p.getNombre().toLowerCase().contains(txt_buscar.toLowerCase())) {
                        prodcutoList.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nombreProdcuto.setText(prodcutoList.get(position).getNombre());
        holder.detalleProducto.setText(prodcutoList.get(position).getDescripcion());
        holder.precioProdcuto.setText(String.valueOf(prodcutoList.get(position).getPrecio()));

        Picasso.get()
                .load(prodcutoList.get(position).getFotoUrl())
                .error(R.mipmap.ic_launcher)
                .into(holder.productoImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, DetalleProducto.class);
                i.putExtra("id",prodcutoList.get(position).getIdProducto());
                i.putExtra("name", prodcutoList.get(position).getNombre());
                i.putExtra("image", prodcutoList.get(position).getFotoUrl());
                i.putExtra("price", prodcutoList.get(position).getPrecio());
                i.putExtra("desc", prodcutoList.get(position).getDescripcion());
                i.putExtra("qty", String.valueOf(prodcutoList.get(position).getStock()));
                System.out.println("STOCK¡¡: " + prodcutoList.get(position).getStock());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return prodcutoList.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {

        ImageView productoImg;
        TextView nombreProdcuto;
        TextView detalleProducto;
        TextView precioProdcuto;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            productoImg = itemView.findViewById(R.id.imgProducto);
            nombreProdcuto = itemView.findViewById(R.id.txtNombreProducto);
            detalleProducto = itemView.findViewById(R.id.txtDetalleProdcuto);
            precioProdcuto = itemView.findViewById(R.id.txtPrecioProducto);

        }
    }


}
