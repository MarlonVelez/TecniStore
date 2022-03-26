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
import com.example.tienda_op_2.modelo.Categoria;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Categoria> categoryList;

    public CategoryAdapter(Context context, List<Categoria> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    public CategoryAdapter() {
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rows_categorias, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        //holder.categoryImage.setImageResource(categoryList.get(position).getImgId());
        holder.txtNombewCategoria.setText(categoryList.get(position).getNombre());

        Picasso.get()
                .load(categoryList.get(position).getImgId())
                .error(R.mipmap.ic_launcher)
                .into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public  static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;
        TextView txtNombewCategoria;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.categoryImage);
            txtNombewCategoria= itemView.findViewById(R.id.txtNombreCategoria);

        }
    }

}

// lets import all the category images