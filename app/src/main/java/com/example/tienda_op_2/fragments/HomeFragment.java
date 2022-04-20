package com.example.tienda_op_2.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.adapter.ProductoAdapter;
import com.example.tienda_op_2.api.servicioApi;
import com.example.tienda_op_2.modelo.Producto;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ProductoAdapter adapter;
    private ArrayList<Producto> listProducto;
    private RecyclerView  listaCategorias;
    public static RecyclerView listaProdcutos;
    private  servicioApi api;
    private View view;



    public HomeFragment() {
        // Required empty public constructor
    }

    @NotNull
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        listaProdcutos= view.findViewById(R.id.listaProductos);
        listaCategorias= view.findViewById(R.id.itemsCategoria);

        api= new servicioApi(getContext(), "producto");
        api.datosList(listaProdcutos);

        api= new servicioApi(getContext(), "categoria");
        api.datosList(listaCategorias);
        return view;
    }

    public void actualizar(int id) {
        api= new servicioApi();
        listProducto= api.listarCategorias(listaProdcutos,id);
        System.out.println(listProducto.size()+ " lista actulizar");
        adapter=new ProductoAdapter(getContext(),listProducto);
        listaProdcutos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}