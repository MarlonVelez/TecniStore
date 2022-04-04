package com.example.tienda_op_2.adapter;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.MainActivity;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.SignUp4;
import com.example.tienda_op_2.fragments.HomeFragment;
import com.example.tienda_op_2.modelo.Categoria;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Categoria> categoryList;
    private RecyclerView recyclerView;


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
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id;
                System.out.println(categoryList.get(position).getId()+ "   categoria ");
                id=categoryList.get(position).getId();
                HomeFragment h=new HomeFragment();
                h.onCreate(id);
            }
        });

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

