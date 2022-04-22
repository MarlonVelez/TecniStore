package com.example.tienda_op_2.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import org.jetbrains.annotations.NotNull;
import com.example.tienda_op_2.R;
import android.widget.EditText;

public class DialogoPagoFragment extends DialogFragment {

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
        View v= inflater.inflate(R.layout.fragment_dialogo_cofirma_pago,null);
        builder.setView(v);

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