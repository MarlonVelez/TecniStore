package com.example.tienda_op_2.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.tienda_op_2.R;

public class PagoFargmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private int totalTabs;
    private boolean datosIngresados= false;

    public PagoFargmentAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
        System.out.println("B");
    }


    @Override
    public Fragment getItem(int position) {
        System.out.println("C");
        switch (position){
            case 0:
                RegistroFragment registroFragment = new RegistroFragment();
                return registroFragment;
            case 1:
                System.out.println("D");
                if(!datosIngresados){
                    ListaComprasFragment listaComprasFragment = new ListaComprasFragment();
                    return listaComprasFragment;
                }else{
                    Toast.makeText(context, "Tienes que llenar todos los campos", Toast.LENGTH_SHORT).show();
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    private void verificar(){

    }

    public boolean isDatosIngresados() {
        return datosIngresados;
    }

    public void setDatosIngresados(boolean datosIngresados) {
        this.datosIngresados = datosIngresados;
    }
}

