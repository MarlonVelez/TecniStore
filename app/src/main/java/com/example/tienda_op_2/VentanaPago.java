package com.example.tienda_op_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.example.tienda_op_2.fragments.ListaComprasFragment;
import com.example.tienda_op_2.fragments.PagoFargmentAdapter;
import com.example.tienda_op_2.fragments.RegistroFragment;
import com.google.android.material.tabs.TabLayout;

public class VentanaPago extends AppCompatActivity  {

    private static TabLayout tab;
    public static FragmentManager fm;
    Toolbar toolbar;
    public static FrameLayout fragmentContenedor;
    public static FragmentTransaction ft;
    Fragment fragmentRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_pago);
        setTitle("Formulario de Pago y Envio");

        tab= findViewById(R.id.tab_layout);
        tab.addTab(tab.newTab().setText("Datos de Envio"));
        tab.addTab(tab.newTab().setText("Lista de Compras"));
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        fm= getSupportFragmentManager();
        fragmentContenedor= findViewById(R.id.fragmentContenedor);

        fragmentRegistro= new RegistroFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContenedor, fragmentRegistro).commit();
        ft= getSupportFragmentManager().beginTransaction();

        final PagoFargmentAdapter adapter= new PagoFargmentAdapter(fm, this, tab.getTabCount());
        /*CONFIGURACION TOOL BAR*/

        toolbar= findViewById(R.id.toolBar);

        //CONFIGURACION DEL TOOL BAR Y FLECHA DE RETORNO AL CARRITO
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_btn_back_view));
        Intent carrito= new Intent(this, CarritoCompras.class);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                startActivity(carrito);
                finish();
            }
        });
    }
}