package com.example.tienda_op_2;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.tienda_op_2.adapter.ProductoAdapter;
import com.example.tienda_op_2.api.servicioApi;
import com.example.tienda_op_2.fragments.HomeFragment;
import com.example.tienda_op_2.modelo.Producto;
import com.example.tienda_op_2.validaciones.InterfaceRetro;
import com.google.android.material.navigation.NavigationView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    public SearchView txt_buscar;
    private servicioApi api;
    private ProductoAdapter adapter;
    private ArrayList<Producto> listProducto;
    private RecyclerView recyclerlistaProdcutos;
    LottieAnimationView fondoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listProducto =new ArrayList<>();
        getProductos();

        //Referencias UI
        drawerLayout = findViewById(R.id.contenidoPrincipal);
        navigationView = findViewById(R.id.nav_view_bar);
        toolbar = findViewById(R.id.toolBar);

        //Configurar Fragment por defecto (El que aparece al principio)
        getSupportFragmentManager().beginTransaction().add(R.id.contentFrame, new HomeFragment()).commit();
        setTitle("Home");

        //Configuracion de ToolBar
        setSupportActionBar(toolbar);
        toggle = setDrawerToggle();
        drawerLayout.addDrawerListener(toggle);

        //Dar acciones a los items del menu
        navigationView.setNavigationItemSelectedListener(this);

        /**/


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

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (item.getItemId()) {

            case R.id.nav_home:
                ft.replace(R.id.contentFrame, new HomeFragment()).commit();
                break;
            case R.id.nav_configUser:
                Intent configUser = new Intent(this, PerfilUsuario.class);
                startActivity(configUser);
                finish();
                break;
            case R.id.nav_carrito:
                Intent carritoActi = new Intent(this, CarritoCompras.class);
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
        MenuItem menuItem = menu.findItem(R.id.search_button);
        txt_buscar = (SearchView) menuItem.getActionView();
        txt_buscar.setQueryHint("Busca un prodcuto");
        recyclerlistaProdcutos = findViewById(R.id.listaProductos);
        txt_buscar.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       recyclerlistaProdcutos.setAdapter(adapter);
        adapter.filtrado(newText);
        return false;
    }


    private void getProductos() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tecnistoreaapi.rj.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceRetro json = retrofit.create(InterfaceRetro.class);
        Call<List<Producto>> call = json.getPosts();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                List<Producto> post = response.body();
                for (Producto producto : post) {
                    producto.setNombre(producto.getNombre());
                    producto.setDescripcion(producto.getDescripcion());
                    producto.setStock(producto.getStock());
                    producto.setPrecio(producto.getPrecio());
                    producto.setFotoUrl(producto.getFotoUrl());
                    listProducto.add(producto);
                }
                adapter = new ProductoAdapter(getApplicationContext(), listProducto);
            }
            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
            }
        });

    }

}