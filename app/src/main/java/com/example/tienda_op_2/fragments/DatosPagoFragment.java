package com.example.tienda_op_2.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import org.jetbrains.annotations.NotNull;
import com.example.tienda_op_2.R;
import android.widget.EditText;

public class DatosPagoFragment extends DialogFragment {

    EditText txtNumTarjeta, txtMesVencimiento, txtAñoVencimiento, txtCVV;
    Activity actividad;

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
        txtAñoVencimiento= v.findViewById(R.id.txtAñoVencimiento);
        txtCVV= v.findViewById(R.id.txtCVV);

        //eventosBotones();
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
}