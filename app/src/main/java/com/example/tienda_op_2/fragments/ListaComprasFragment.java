package com.example.tienda_op_2.fragments;

import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.carga_de_datos.CargaProductos;

public class ListaComprasFragment extends Fragment {

    RecyclerView listaCompra;
    TextView txtTotalCompra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_lista_compras, container, false);

        listaCompra= root.findViewById(R.id.listaProductosCompra);
        txtTotalCompra= root.findViewById(R.id.txtTotalListaCompra);

        CargaProductos datosProductos= new CargaProductos(getContext(), listaCompra, txtTotalCompra);
        datosProductos.listarProductosCarrito(null, "Lista Compras");
        return root;
    }
}
