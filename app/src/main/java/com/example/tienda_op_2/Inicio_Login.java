package com.example.tienda_op_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tienda_op_2.adapter.LoginAdapter;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.modelo.Login;
import com.example.tienda_op_2.Services.Validacion_user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Inicio_Login extends AppCompatActivity implements Validacion_user {

    public static ArrayList<Login> arrayDatos =new ArrayList<Login>();

    private ProgressBar progressBar;
    private Button btn_ingresa;
    private TextView txtUsuario,txtClave;
    private TextView btnRegistrarse;
    private LottieAnimationView imgLoadding, labelLoadding;
    private CardView cardLoadding;

    // HOLA MUNDO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_ingresa= findViewById(R.id.btn_ingresar);
        imgLoadding= findViewById(R.id.imgLoadding);
        labelLoadding= findViewById(R.id.labelLoadding);
        cardLoadding= findViewById(R.id.cardLoadding);
        cardLoadding.setVisibility(View.GONE);

        txtUsuario = findViewById(R.id.txt_usuarioSignUp);
        txtClave = findViewById(R.id.txt_contraseña);
        progressBar=findViewById(R.id.progressBar);
        btnRegistrarse= findViewById(R.id.btn_registrarse);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp= new Intent(Inicio_Login.this, SignUp4.class);
                startActivity(signUp);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        btn_ingresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerDatos();
                new LoginAdapter(Inicio_Login.this).execute(txtUsuario.getText(), txtClave.getText(),3000);
            }
        });
    }

    private void obtenerDatos(){

        String direccion="http://10.0.2.2:8080/usuario";
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(direccion, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                pasarJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        Request<JSONArray> add = Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void pasarJson( JSONArray array) {

        for (int i = 0; i < array.length(); i++) {
            Login post = new Login();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                post.setUsuario(json.getString("usuario"));
                post.setClave(json.getString("clave"));
                arrayDatos.add(post);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void toggleProgressBar(boolean status) {
        if (status) {
            btn_ingresa.setVisibility(View.GONE);

            cardLoadding.setVisibility(View.VISIBLE);
            imgLoadding.setAnimation(R.raw.effect_loadding);
            imgLoadding.playAnimation();
            imgLoadding.setRepeatCount(10);

            labelLoadding.setAnimation(R.raw.label_loadding);
            labelLoadding.playAnimation();
            labelLoadding.setRepeatCount(10);

            //progressBar.setVisibility(View.VISIBLE);

        } else {
            cardLoadding.setVisibility(View.GONE);
            btn_ingresa.setVisibility(View.VISIBLE);
            //progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void lanzarActividad(Class<?> tipoActividad) {
        /*AQUI GUARDAMOS LOS DATOS DEL USUARIO PARA PODER VALIDAD EL INGRESO A LA APP*/
        String usuario="", clave="";

        for (int i = 0; i < arrayDatos.size(); i++) {
            if (arrayDatos.get(i).getUsuario().equalsIgnoreCase(txtUsuario.getText().toString())){
                usuario= arrayDatos.get(i).getUsuario();
                clave= arrayDatos.get(i).getClave();
            }
        }

        SQLiteOpenHelper bd= new SQLiteOpenHelper(Inicio_Login.this);
        bd.agregarUsuario(usuario, "cliente", clave, "registrado");

        Intent intent = new Intent(this, tipoActividad);
        startActivity(intent);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
