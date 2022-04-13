package com.example.tienda_op_2;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda_op_2.adapter.ProductoAdapter;
import com.example.tienda_op_2.api.servicioApi;
import com.example.tienda_op_2.fragments.HomeFragment;
import com.example.tienda_op_2.modelo.Producto;
import com.google.android.material.navigation.NavigationView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    public SearchView txt_buscar;
    private servicioApi api;
    private  ProductoAdapter adapter;
    private  ArrayList<Producto> listProducto;
    private   RecyclerView recyclerlistaProdcutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencias UI
        drawerLayout= findViewById(R.id.contenidoPrincipal);
        navigationView= findViewById(R.id.nav_view_bar);
        toolbar= findViewById(R.id.toolBar);


        //Configurar Fragment por defecto (El que aparece al principio)
        getSupportFragmentManager().beginTransaction().add(R.id.contentFrame, new HomeFragment()).commit();
        setTitle("Home");

        //Configuracion de ToolBar
        setSupportActionBar(toolbar);
        toggle= setDrawerToggle();
        drawerLayout.addDrawerListener(toggle);

        //Dar acciones a los items del menu
        navigationView.setNavigationItemSelectedListener(this);
    }


    private ActionBarDrawerToggle setDrawerToggle() {
        return new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        listerSelectedItem(item);
        return true;
    }

    private void listerSelectedItem(MenuItem item) {

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        switch (item.getItemId()){

            case R.id.nav_home:
                ft.replace(R.id.contentFrame, new HomeFragment()).commit();
                break;
            case R.id.nav_configUser:

                break;
            case R.id.nav_carrito:
                Intent carritoActi= new Intent(this, CarritoCompras.class);
                startActivity(carritoActi);
                finish();
                break;
        }
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_button, menu);
        MenuItem menuItem= menu.findItem(R.id.search_button);
        txt_buscar = (SearchView) menuItem.getActionView();
        txt_buscar.setQueryHint("Busca un prodcuto");
        recyclerlistaProdcutos=findViewById(R.id.listaProductos);
        //  LinearLayoutManager manager= new LinearLayoutManager(this);
        // recyclerlistaProdcutos.setLayoutManager(manager);
        listProducto =new ArrayList<>();
        Producto p= new Producto(1,"P1",10,null,"hola a todos","100");
        Producto p1= new Producto(1,"P3",10,null,"hola a todos","100");
        Producto p2= new Producto(1,"P2",10,null,"hola a todos","100");
        Producto p3= new Producto(1,"P4",10,null,"hola a todos","100");
        listProducto.add(p);
        listProducto.add(p1);
        listProducto.add(p2);
        listProducto.add(p3);
        /*  api=new servicioApi(this);
        listProducto=api.listarProductos(recyclerlistaProdcutos);*/

        System.out.println(listProducto.size()+" aquiiiiiiiiiiii");
        adapter=new ProductoAdapter(getApplicationContext(),listProducto);
        recyclerlistaProdcutos.setAdapter(adapter);
        txt_buscar.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //adapter=new ProductoAdapter(getApplicationContext(),listProducto);
        recyclerlistaProdcutos.setAdapter(adapter);
        adapter.filtrado(newText);
        return false;
    }

}