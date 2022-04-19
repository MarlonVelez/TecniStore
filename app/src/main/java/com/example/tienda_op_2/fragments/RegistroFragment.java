package com.example.tienda_op_2.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.example.tienda_op_2.CarritoCompras;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.SignUp4;
import com.example.tienda_op_2.SplashScream;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.carga_de_datos.CargaProductos;
import com.example.tienda_op_2.carga_de_datos.CargarDatosPagoEnvio;
import com.example.tienda_op_2.carga_de_datos.CargarUsuario;
import com.example.tienda_op_2.weblogin.modelo.Cliente;
import com.example.tienda_op_2.weblogin.utildades.ApiClient;
import com.example.tienda_op_2.weblogin.utildades.ClienteService;
import retrofit2.*;

import java.text.SimpleDateFormat;

import com.example.tienda_op_2.Services.PedidoService;
import com.example.tienda_op_2.api.apiClientes;
import com.example.tienda_op_2.api.apiPedido;
import com.example.tienda_op_2.api.servicioApi;
import com.example.tienda_op_2.modelo.Pedido;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.ArrayList;
import java.util.Date;

import static com.example.tienda_op_2.VentanaPago.ft;


public class RegistroFragment extends Fragment {

    EditText txtCedula, txtNombre, txtApellido, txtFechaNac, txtDireccion, txtTelefono, txtEmail, txtUsuario, txtContraseña;
    EditText txtNumTarjeta, txtMesVencimiento, txtAnioVencimiento, txtCVV;
    Button btnSiguiente, btnP;
    String fechaSeleccionada;
    LottieAnimationView imgRegister;

    ArrayList<com.example.tienda_op_2.modelo.Cliente> listaCliente= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_registro, container, false);
        txtCedula= root.findViewById(R.id.txtCedulaPago);
        txtNombre= root.findViewById(R.id.txtNombrePago);
        txtApellido= root.findViewById(R.id.txtApellidoPago);
        txtFechaNac= root.findViewById(R.id.txtFechaNacPago);
        txtDireccion= root.findViewById(R.id.txtDireccionPago);
        txtTelefono= root.findViewById(R.id.txtTelefonoPago);
        txtEmail= root.findViewById(R.id.txtEmailPago);
        txtUsuario= root.findViewById(R.id.txtNameUsuarioR);
        txtContraseña= root.findViewById(R.id.txtContraseñaUsuarioR);
        txtNumTarjeta= root.findViewById(R.id.txtNumTarjeta);
        txtMesVencimiento=root.findViewById(R.id.txtMesVencimiento);
        txtAnioVencimiento= root.findViewById(R.id.txtAñoVencimiento);
        txtCVV= root.findViewById(R.id.txtCVV);

        btnP= root.findViewById(R.id.btnSiguientePago);

        btnSiguiente= root.findViewById(R.id.btnSiguientePago);

        /*CODIGO GENERADO POR JOSE*/
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dt = txtFechaNac.getText().toString();

                if (!txtCedula.getText().toString().isEmpty() &&
                        !txtNombre.getText().toString().isEmpty() &&
                        !txtApellido.getText().toString().isEmpty() &&
                        !txtFechaNac.getText().toString().isEmpty() &&
                        !txtDireccion.getText().toString().isEmpty() &&
                        !txtTelefono.getText().toString().isEmpty() &&
                        !txtEmail.getText().toString().isEmpty() &&
                        !txtUsuario.getText().toString().isEmpty() &&
                        !txtContraseña.getText().toString().isEmpty() &&
                        !txtNumTarjeta.getText().toString().isEmpty() &&
                        !txtMesVencimiento.getText().toString().isEmpty() &&
                        !txtAnioVencimiento.getText().toString().isEmpty() &&
                        !txtCVV.getText().toString().isEmpty()) {

                    Cliente cliente = new Cliente();
                    cliente.setApellido(txtApellido.getText().toString());
                    cliente.setCedula(txtCedula.getText().toString());
                    cliente.setCorreo(txtEmail.getText().toString());
                    cliente.setDireccion(txtDireccion.getText().toString());
                    cliente.setEstadoCliente("true");
                    cliente.setFechaNacimiento(dt);
                    cliente.setIdCliente(0);
                    cliente.setIdUsuario(8);
                    cliente.setNombre(txtNombre.getText().toString());
                    cliente.setTelefono(txtTelefono.getText().toString());
                    //AQUI REGISTRAMOS LOS DATOS DEL CLIENTE Y TARJETA DENTRO DE UNA BDD TEMPORAL
                    registrarDatosClienteSQL();
                    registrarDatosPago();
                    //addCliente(cliente);
                    limpiarCampos();
                    cambiarFragment();
                }else{
                    Toast.makeText(root.getContext(), "Tienes que llenar todos los campos", Toast.LENGTH_SHORT).show();
                }

                agregarDatosPedido();
            }
        });


        txtFechaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        comprobarUsuario();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /*METODO PARA ABRIR EL DIALOGO QUE CONTIENE EL CALENDARIO PARA ELEGIR LA FECHA*/
    private void showDatePickerDialog() {

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 porque enero es 0
                if (month >= 1 && month <= 9 && day >= 1 && day <= 9){
                    fechaSeleccionada = ""+year + "-0" + (month+1) + "-0" + day+"";
                    txtFechaNac.setText(fechaSeleccionada);
                }else if (month >= 1 && month <= 9 && day > 9){
                    fechaSeleccionada = ""+year + "-0" + (month+1) + "-" + day+"";
                    txtFechaNac.setText(fechaSeleccionada);
                }else if (day >= 1 && day <= 9 && month > 9){
                    fechaSeleccionada = ""+year + "-" + (month+1) + "-0" + day+"";
                    txtFechaNac.setText(fechaSeleccionada);
                }else {
                    fechaSeleccionada = "" + year + "-" + (month + 1) + "-" + day + "";
                    txtFechaNac.setText(fechaSeleccionada);
                }
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    /*METODO PARA GUARDAR UN CLIENTE DENTRO DEL API DEL APLICATIVO*/
    ClienteService clienteService;
    public void addCliente(com.example.tienda_op_2.weblogin.modelo.Cliente cliente) {
        clienteService = ApiClient.getClienteService();
        Call<Cliente> call = clienteService.addCliente(cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
            }
            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

            }
        });
    }

    /*CODIGO GENERADO POR WILLIAM*/
    public void  agregarDatosPedido(){
        int id_cliente = 0;
        double totalGeneral;
        totalGeneral= CargaProductos.total;
        servicioApi serv= new servicioApi();
        serv.listarClientes();
        listaCliente=apiClientes.listacliente;

        System.out.println(totalGeneral+ " totalllllllllll");

        // listaCliente=apiClientes.listacliente;
        System.out.println(listaCliente.size()+ " gggggg");

        for (com.example.tienda_op_2.modelo.Cliente p : listaCliente) {
            if (p.getCedula().contains(txtCedula.getText())) {
                id_cliente=p.getIdCliente();
            }
        }
        System.out.println(id_cliente+ " xxxx");

        Date fecha= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(fecha);
        Pedido p=new Pedido();
        p.setIdCliente(id_cliente);
        p.setFecha(date);
        p.setDespachado("false");
        p.setTotalGeneral(totalGeneral);
        addPedido(p);
    }

    PedidoService pedidoService;
    public void addPedido(Pedido pedido){
        pedidoService= apiPedido.getpedidoService();
        Call<Pedido> call = pedidoService.addPedido(pedido);
        call.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, retrofit2.Response<Pedido> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Pedido agregado automaticamente", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Toast.makeText(getContext(), "Error al agregar usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void limpiarCampos(){
        txtCedula.setText("");
        txtTelefono.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
        txtFechaNac.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtUsuario.setText("");
        txtContraseña.setText("");
    }



    /*CODIGO GENERADO POR MARLON*/

    /*METODO PARA GUARDAR DATOS DEL CLIENTE EN BDD TEMPORAL*/
    private void registrarDatosClienteSQL(){
        SQLiteOpenHelper base= new SQLiteOpenHelper(getContext());

        String nombre =txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String fehcaNac = txtFechaNac.getText().toString();
        String cedula = txtCedula.getText().toString();
        String direccion = txtDireccion.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String email = txtEmail.getText().toString();

        boolean bandera= base.agregarDatosCliente(nombre, apellido, cedula, fehcaNac, email, telefono,direccion);

        if (bandera!=false){
            Toast.makeText(getContext(), "Datos del cliente registrados", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "No se pudo registar sus datos", Toast.LENGTH_SHORT).show();
        }
    }

    /*METODO PARA GUARDAR DATOS DE LA TARJETA EN BDD TEMPORAL*/
    private void registrarDatosPago(){
        SQLiteOpenHelper base= new SQLiteOpenHelper(getContext());

        String numTarjeta= txtNumTarjeta.getText().toString();
        String mesVencmiento= txtMesVencimiento.getText().toString();
        String anioVencimiento= txtAnioVencimiento.getText().toString();
        String cvv= txtCVV.getText().toString();

        boolean bandera= base.agregarDatosPago(numTarjeta, mesVencmiento, anioVencimiento, cvv);

        if (bandera!=false){
            Toast.makeText(getContext(), "Datos del cliente registrados", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "No se pudo registrar la targeta", Toast.LENGTH_SHORT).show();
        }
    }

    /*ESTE METODO SIRVE PARA VERIFICAR SI ES QUE EL USUARIO HAYA GENERADO UNA CUENTA EN EL APLICATIVO*/
    private void comprobarUsuario(){
        CargarUsuario usu= new CargarUsuario(getContext());
        String estadoUsuario= usu.listarUsuarioP().get(0).getEstadoUsuario();

        if (estadoUsuario.equalsIgnoreCase("por registrar")){
            new AlertDialog.Builder(getContext())
                    .setTitle("¡Ups! Parece que no te has registrado aun")
                    .setMessage("Si deseas continuar debes crearte un perfil en la aplicacion."+"\n¿Estas de acuerdo con esto?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent ventanaRegistro= new Intent(getContext(), SignUp4.class);
                            startActivity(ventanaRegistro);
                            //onStop();
                        }
                    })
                    .setNegativeButton("Quizas luego", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent carrito= new Intent(getContext(), CarritoCompras.class);
                            startActivity(carrito);
                        }
                    })
                    .show();
        }else{
            txtUsuario.setText(usu.listarUsuarioP().get(0).getNombreUsuario());
            txtContraseña.setText(usu.listarUsuarioP().get(0).getClaveUsuario());
            cargarDatosExistentes();
        }
    }

    /*MEMTODO PARA CARGAR DATOS SI HUBIESE DATOS REGISTRADOS CON ANTERIORIDAD*/
    private void cargarDatosExistentes(){
        CargarDatosPagoEnvio datos= new CargarDatosPagoEnvio(getContext());
        String numeroTarjeta= datos.listarDatosTargeta();
        ArrayList<com.example.tienda_op_2.modelo.Cliente> cliente= datos.listarDatosCliente();

        if(cliente!=null){
            for (int i = 0; i < cliente.size(); i++) {

                txtCedula.setText(cliente.get(i).getCedula());
                txtNombre.setText(cliente.get(i).getNombre());
                txtApellido.setText(cliente.get(i).getApellido());
                txtTelefono.setText(cliente.get(i).getTelefono());
                txtEmail.setText(cliente.get(i).getCorreo());
                txtFechaNac.setText(parseDateToStrign(cliente.get(i).getFechaNacimiento()));
                txtDireccion.setText(cliente.get(i).getDireccion());
            }
        }

    }

    private void cambiarFragment(){
        Fragment fragmentListaCompras= new ListaComprasFragment();
        ft.replace(R.id.fragmentContenedor, fragmentListaCompras);
        ft.addToBackStack(null);
        ft.commit();
    }

    private String parseDateToStrign(Date fecha){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaComoCadena = sdf.format(fecha);
        return fechaComoCadena;
    }

}