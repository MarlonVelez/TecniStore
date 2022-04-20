package com.example.tienda_op_2.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.google.android.material.tabs.TabLayout;
import org.jetbrains.annotations.NotNull;
import com.example.tienda_op_2.R;
import android.widget.EditText;

import static com.example.tienda_op_2.VentanaPago.*;

public class DatosPagoFragment extends DialogFragment {

    EditText txtNumTarjeta, txtMesVencimiento, txtAnioVencimiento, txtCVV;
    Activity actividad;
    Button btnAceptarDP;



    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_datos_pago, container, false);
        //return root;

    }*/

    private AlertDialog crearDialogo() {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        LayoutInflater inflater= getActivity().getLayoutInflater();
        View v= inflater.inflate(R.layout.fragment_datos_pago,null);
        builder.setView(v);

        txtNumTarjeta= v.findViewById(R.id.txtNumTarjeta);
        txtMesVencimiento= v.findViewById(R.id.txtMesVencimiento);
        txtAnioVencimiento= v.findViewById(R.id.txtAñoVencimiento);
        txtCVV= v.findViewById(R.id.txtCVV);
        btnAceptarDP= v.findViewById(R.id.btnAceptarDP);
        //eventosBotones();

        btnAceptarDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNumTarjeta.getText().toString().isEmpty()
                        && !txtMesVencimiento.getText().toString().isEmpty()
                        && !txtAnioVencimiento.getText().toString().isEmpty()
                        && !txtCVV.getText().toString().isEmpty()){
                    registrarDatosPago();
                }else {
                    Toast.makeText(getContext(), "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        return builder.create();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad= (Activity) context;
        }else {
            throw new RuntimeException( context.toString()
                    +"must implement OnFragmentInterectionListener");
        }

    }

    private void registrarDatosPago(){
        SQLiteOpenHelper base= new SQLiteOpenHelper(getContext());

        String numTarjeta= txtNumTarjeta.getText().toString();
        String mesVencmiento= txtMesVencimiento.getText().toString();
        String anioVencimiento= txtAnioVencimiento.getText().toString();
        String cvv= txtCVV.getText().toString();

        boolean bandera= base.agregarDatosPago(numTarjeta, mesVencmiento, anioVencimiento, cvv);

        if (bandera!=false){
            Toast.makeText(getContext(), "Targeta Registrada", Toast.LENGTH_SHORT).show();
            dismiss();
        }else {
            Toast.makeText(getContext(), "No se pudo registrar la targeta", Toast.LENGTH_SHORT).show();
        }
    }





}