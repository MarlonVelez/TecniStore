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
import com.example.tienda_op_2.Services.DetalleService;
import com.example.tienda_op_2.Services.PedidoService;
import com.example.tienda_op_2.api.apiClientes;
import com.example.tienda_op_2.api.apiDetallePedido;
import com.example.tienda_op_2.api.apiPedido;
import com.example.tienda_op_2.api.servicioApi;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.carga_de_datos.CargaProductos;
import com.example.tienda_op_2.carga_de_datos.CargarDatosPagoEnvio;
import com.example.tienda_op_2.modelo.Carrito;
import com.example.tienda_op_2.modelo.Cliente;
import com.example.tienda_op_2.modelo.Detalle_pedido;
import com.example.tienda_op_2.modelo.Pedido;
import retrofit2.Call;
import retrofit2.Callback;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.tienda_op_2.VentanaPago.ft;

public class ListaComprasFragment extends Fragment {

    RecyclerView listaCompra;
    TextView labelTotalCompra, labelNombreCliente, labelDireccionCliente, labelTelefonoCliente, labelNumeroTarjeta;
    ArrayList<Cliente> listaCliente= new ArrayList<>();
    String cedulaCliente;
    Button btnConfirmar;

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

        btnConfirmar=root.findViewById(R.id.btnConfirmarPago);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarDatosPedido();
            }
        });


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
        System.out.println("NUM TAR NUM TAR NUM TAR NUM TAR NUM TAR NUM TAR NUM TAR NUM TAR NUM TAR "+numeroTarjeta);
        ArrayList<Cliente> cliente= datos.listarDatosCliente();

        for (int i = 0; i < cliente.size(); i++) {
            labelNombreCliente.setText(cliente.get(i).getNombre()+" "+cliente.get(i).getApellido());
            labelDireccionCliente.setText(cliente.get(i).getDireccion());
            labelTelefonoCliente.setText(cliente.get(i).getTelefono());
            labelNumeroTarjeta.setText(numeroTarjeta);


        }
    }

    //CODIGO GENERADO POR WILLIAM
    public void cargarDatosPedido(){

        listaCliente= apiClientes.listacliente;
        int id_cliente=0;
        cedulaCliente=RegistroFragment.cedula;

        for(int i=0; i<listaCliente.size();i++){
            if(cedulaCliente.equals(listaCliente.get(i).getCedula())) {
                id_cliente = listaCliente.get(i).getIdCliente();
            }
        }
        double totalGeneral;
        totalGeneral= CargaProductos.total;
        Date fecha= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(fecha);
        Pedido p=new Pedido();
        p.setIdCliente(id_cliente);
        p.setFecha(date);
        p.setDespachado("false");
        p.setTotalGeneral(totalGeneral);
        agregarPedido(p);

    }


    public void cargarDatosDetalle(){

        //Listar el pedido desde el Api
        servicioApi ser=new servicioApi();
        ser.listarPedido();
    }

    //Metodo par registrar Pedido
    PedidoService pedidoService;
    public void agregarPedido (Pedido pedido){

        pedidoService= apiPedido.getpedidoService();
        Call<Pedido> call = pedidoService.addPedido(pedido);
        call.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, retrofit2.Response<Pedido> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Pedido agregado automaticamente", Toast.LENGTH_SHORT).show();
                    cargarDatosDetalle();
                }
            }
            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Toast.makeText(getContext(), "Error al agregar usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
