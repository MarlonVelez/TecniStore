package com.example.tienda_op_2.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.example.tienda_op_2.R;

public class RegistroFragment extends Fragment {

    EditText txtCedula, txtNombre, txtApellido, txtFechaNac, txtDireccion, txtTelefono, txtEmail;
    Button btnSiguiente;
    String fechaSeleccionada;

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

        btnSiguiente= root.findViewById(R.id.btnSiguientePago);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatosPagoFragment dialogPago= new DatosPagoFragment();
                dialogPago.show(getActivity().getSupportFragmentManager(), "Metodo Pafgo");
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
                fechaSeleccionada = day + " / " + (month+1) + " / " + year;
                txtFechaNac.setText(fechaSeleccionada);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }
}