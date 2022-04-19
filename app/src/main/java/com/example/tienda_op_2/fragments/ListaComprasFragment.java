package com.example.tienda_op_2.fragments;

import android.widget.Button;
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
import com.example.tienda_op_2.carga_de_datos.CargarDatosPagoEnvio;
import com.example.tienda_op_2.modelo.Cliente;

import java.util.ArrayList;

import static com.example.tienda_op_2.VentanaPago.ft;

public class ListaComprasFragment extends Fragment {

    RecyclerView listaCompra;
    TextView labelTotalCompra, labelNombreCliente, labelDireccionCliente, labelTelefonoCliente, labelNumeroTarjeta;
    TextView btnEditarCarrito, btnEditarDatosPago;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_lista_compras, container, false);

        listaCompra= root.findViewById(R.id.listaProductosCompra);
        labelTotalCompra= root.findViewById(R.id.txtTotalListaCompra);

        labelNombreCliente= root.findViewById(R.id.txtNombreClientePago);
        labelDireccionCliente= root.findViewById(R.id.txtDireccionClientePago);
        labelTelefonoCliente= root.findViewById(R.id.txtTelefonoClientePago);
        labelNumeroTarjeta= root.findViewById(R.id.txtNumTarjetaClientePago);

        btnEditarCarrito= root.findViewById(R.id.btnEditarCarrito);
        btnEditarDatosPago= root.findViewById(R.id.btnEditarDatosPago);

        cargarListaCompras();
        cargarDatosPago();

        return root;
    }

    private void cargarListaCompras(){
        CargaProductos datosProductos= new CargaProductos(getContext(), listaCompra, labelTotalCompra, null, null);
        datosProductos.listarProductosCarrito(null, "Lista Compras");
    }

    private void  cargarDatosPago(){

        CargarDatosPagoEnvio datos= new CargarDatosPagoEnvio(getContext());
        String numeroTarjeta= datos.listarDatosTargeta();
        ArrayList<Cliente> cliente= datos.listarDatosCliente();

        for (int i = 0; i < cliente.size(); i++) {
            labelNombreCliente.setText(cliente.get(i).getNombre()+" "+cliente.get(i).getApellido());
            labelDireccionCliente.setText(cliente.get(i).getDireccion());
            labelTelefonoCliente.setText(cliente.get(i).getTelefono());
            labelNumeroTarjeta.setText(numeroTarjeta);
        }
    }

    public void OnClick(View view){
        switch (view.getId()){

            case R.id.btnEditarCarrito:

                break;
            case R.id.btnEditarDatosPago:
                Fragment resgitro= new RegistroFragment();
                ft.replace(R.id.fragmentContenedor, resgitro);
                ft.addToBackStack(null);
                ft.commit();
                break;

        }
    }
}
