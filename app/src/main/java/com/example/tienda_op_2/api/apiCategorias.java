package com.example.tienda_op_2.api;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.adapter.CategoryAdapter;
import com.example.tienda_op_2.modelo.Categoria;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class apiCategorias{

    CategoryAdapter categoriaAdapter;
    ArrayList<Categoria> categorias= new ArrayList<>();
    Context context;
    RecyclerView listCategorias;

    public apiCategorias(Context context, RecyclerView listCategorias) {
        this.context = context;
        this.listCategorias = listCategorias;
    }

    public apiCategorias() {
        //setCategoryRecycler(categorias);
    }

    public void parseJSON(JSONArray myJSON) throws JSONException {
        for (int i =0; i<myJSON.length(); i++){
            JSONObject jsonObject= null;

            Categoria categoria= new Categoria();

            jsonObject= myJSON.getJSONObject(i);

            categoria.setNombre(jsonObject.getString("nombre"));
            categoria.setImgId(jsonObject.getString("fotoUrl"));
            categoria.setId(jsonObject.getInt("idCategoria"));

            categorias.add(categoria);

        }
        mostrarCategorias(categorias);
        categoriaAdapter.notifyDataSetChanged();
    }

    public void mostrarCategorias(List<Categoria> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        listCategorias.setLayoutManager(layoutManager);
        categoriaAdapter = new CategoryAdapter(context,categoryDataList);
        listCategorias.setAdapter(categoriaAdapter);
    }
}
