package com.example.tienda_op_2;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tienda_op_2.api.apiUsuario;
import com.example.tienda_op_2.modelo.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUp4 extends AppCompatActivity {

    private ImageView backView;
    private Button btnSiguiente;

    private EditText txt_usu, txt_contra, txt_confir_contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up4);

        txt_usu=findViewById(R.id.txt_usuarioRregistro);
        txt_contra=findViewById(R.id.txt_contraseñaRegistro);
        txt_confir_contra=findViewById(R.id.txt_confirmarRegistro);
        backView= findViewById(R.id.btnBackView);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaInicial= new Intent(SignUp4.this, SignUp3.class);
                startActivity(pantallaInicial);
                finish();
            }
        });


        btnSiguiente= findViewById(R.id.btn_siguiente_datos_usuario);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txt_usu.getText().toString().isEmpty()&&txt_contra.getText().toString().isEmpty()&&txt_confir_contra.getText().toString().isEmpty()){
                    Toast.makeText(SignUp4.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    //postDatosVolley2(txt_usu.getText().toString(),txt_contra.getText().toString());
                    ArrayList<Usuario> datosUsuario = new ArrayList<>();
                    Usuario miusuario = new Usuario();

                    /*miusuario.setIdCliente(1);
                    miusuario.setUsuario(txt_usu.getText().toString());
                    miusuario.setClave(txt_contra.getText().toString());*/
                    datosUsuario.add(miusuario);
                    apiUsuario serviUsuario = new apiUsuario(SignUp4.this);

                    boolean bandera = serviUsuario.postDatosVolley2(datosUsuario);
                    if(bandera!=false){
                            Intent home= new Intent(SignUp4.this, MainActivity.class);
                            startActivity(home);
                            finish();
                    }else{
                        Toast.makeText(SignUp4.this, "El usuario no se pudo registrar", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    /*private void postDatosVolley2(String usuario, String contraseña){

        String url="https://tecnistoreaapi.rj.r.appspot.com:443/usuario";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST,url, new com.android.volley.Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                txt_usu.setText("");
                txt_contra.setText("");

                Toast.makeText(SignUp4.this, "Datos Guardados Correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new com.android.volley.Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(SignUp4.this,"Falla al obtener la respuesta = " + error,Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usuario",usuario );
                params.put("clave", contraseña);

                return params;

            }
        };

        queue.add(request);
    }*/
}