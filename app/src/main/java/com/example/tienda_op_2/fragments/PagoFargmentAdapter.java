package com.example.tienda_op_2.fragments;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagoFargmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private int totalTabs;

    public PagoFargmentAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RegistroFragment registroFragment = new RegistroFragment();
                return registroFragment;
            case 1:
                DatosPagoFragment datosPagoFragment = new DatosPagoFragment();
                return datosPagoFragment;
            case 2:
                ListaComprasFragment listaComprasFragment = new ListaComprasFragment();
                return listaComprasFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}

