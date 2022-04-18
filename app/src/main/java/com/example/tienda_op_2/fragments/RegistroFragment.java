package com.example.tienda_op_2.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.weblogin.modelo.Cliente;
import com.example.tienda_op_2.weblogin.utildades.ApiClient;
import com.example.tienda_op_2.weblogin.utildades.ClienteService;
import retrofit2.Call;
import retrofit2.Callback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RegistroFragment extends Fragment {

    EditText txtCedula, txtNombre, txtApellido, txtFechaNac, txtDireccion, txtTelefono, txtEmail;
    Button btnSiguiente;
    String fechaSeleccionada;
    LottieAnimationView imgRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_registro, container, false);
        txtCedula= root.findViewById(R.id.txtCedulaPago);
        txtNombre= root.findViewById(R.id.txtNombrePago);
        txtApellido= root.findViewById(R.id.txtApellidoPago);
        txtFechaNac= root.findViewById(R.id.txtFechaNacPago);
        txtDireccion= root.findViewById(R.id.txtDireccionPago);
        txtTelefono= root.findViewById(R.id.txtTelefonoPago);
        txtEmail= root.findViewById(R.id.txtEmailPago);
        //imgRegister= root.findViewById(R.id.imgRegistir);

        btnSiguiente= root.findViewById(R.id.btnSiguientePago);

        /*imgRegister.setAnimation(R.raw.animate_register);
        imgRegister.playAnimation();
        imgRegister.setRepeatCount(2000);*/

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fec = txtFechaNac.getText().toString();
                if (!txtCedula.getText().toString().isEmpty() &&
                        !txtNombre.getText().toString().isEmpty() &&
                        !txtApellido.getText().toString().isEmpty() &&
                        !txtFechaNac.getText().toString().isEmpty() &&
                        !txtDireccion.getText().toString().isEmpty() &&
                        !txtTelefono.getText().toString().isEmpty() &&
                        !txtEmail.getText().toString().isEmpty()) {

                    Cliente cliente = new Cliente();
                    cliente.setApellido(txtApellido.getText().toString());
                    cliente.setCedula(txtCedula.getText().toString());
                    cliente.setCorreo(txtEmail.getText().toString());
                    cliente.setDireccion(txtDireccion.getText().toString());
                    cliente.setEstadoCliente("true");
                    /*try {
                        cliente.setFechaNacimiento(formato.parse(txtFechaNac.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }*/
                    //cliente.setFechaNacimiento(parseFecha(txtFechaNac.getText().toString()));
                    cliente.setFechaNacimiento(""+fec+"");
                    cliente.setIdCliente(0);
                    cliente.setIdUsuario(8);
                    cliente.setNombre(txtNombre.getText().toString());
                    cliente.setTelefono(txtTelefono.getText().toString());
                    addCliente(cliente);
                    limpiarCampos();
                    //Muestro el Dialogo personalizado
                    /*DatosPagoFragment dialogPago= new DatosPagoFragment();
                    dialogPago.show(getActivity().getSupportFragmentManager(), "Metodo Pafgo");*/
                }else{
                    Toast.makeText(root.getContext(), "Tienes que llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtFechaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        return root;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

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

    ClienteService clienteService;
    public void addCliente(com.example.tienda_op_2.weblogin.modelo.Cliente cliente) {
        clienteService = ApiClient.getClienteService();
        Call<Cliente> call = clienteService.addCliente(cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<com.example.tienda_op_2.weblogin.modelo.Cliente> call, retrofit2.Response<com.example.tienda_op_2.weblogin.modelo.Cliente> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(RegistroFragment.this,"",Toast.LENGTH_LONG).show();
                    System.out.println("USUARIO REGISTRADO.........................................................");
                }
            }

            @Override
            public void onFailure(Call<com.example.tienda_op_2.weblogin.modelo.Cliente> call, Throwable t) {
                //Toast.makeText(RegistroFragment.this, "error al agregar usuario", Toast.LENGTH_SHORT).show();
                System.out.println("ERROR DE AGREGACION ...........................................................");
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
    }
    private Date parseFecha(String fechaCadena){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date outFecReg =  sdf.parse(fechaCadena);
            return outFecReg;
        } catch (ParseException e) {
            System.out.println("ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! "+e);
            return null;
        }
    }
}