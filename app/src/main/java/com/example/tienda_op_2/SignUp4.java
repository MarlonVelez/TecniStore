package com.example.tienda_op_2;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.example.tienda_op_2.api.apiUsuario;
import com.example.tienda_op_2.modelo.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SignUp4 extends AppCompatActivity {

    private ImageView backView;
    private LottieAnimationView imgSignUp;
    private Button btnRegistrarme;


    private EditText txt_usu, txt_contra, txt_confir_contra;
    private TextInputLayout labelUsuario, labelContraseña, labelConfirmContraseña;
    private ConstraintLayout layoutInputDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up4);

        imgSignUp= findViewById(R.id.ingSignUp);
        labelUsuario=findViewById(R.id.labelUsuarioSignup);
        labelContraseña=findViewById(R.id.labelContraseñaSignUp);
        labelConfirmContraseña=findViewById(R.id.labelCofirmContraseñaSignUp);
        layoutInputDatos= findViewById(R.id.layoutSignUp);
        setAnimation();

        txt_usu=findViewById(R.id.txt_usuarioSignUp);
        txt_contra=findViewById(R.id.txt_contraseñaSignUp);
        txt_confir_contra=findViewById(R.id.txt_contraseñaConfirmarSignUp);

        backView= findViewById(R.id.btnBackView);



        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginBack= new Intent(SignUp4.this, Inicio_Login.class);
                startActivity(loginBack);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                finish();
            }
        });




        btnRegistrarme= findViewById(R.id.btnRegistrarme);
        btnRegistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txt_usu.getText().toString().isEmpty()&&txt_contra.getText().toString().isEmpty()&&txt_confir_contra.getText().toString().isEmpty()){
                    Toast.makeText(SignUp4.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    //postDatosVolley2(txt_usu.getText().toString(),txt_contra.getText().toString());
                    ArrayList<Usuario> datosUsuario = new ArrayList<>();
                    Usuario miusuario = new Usuario();

                    //miusuario.setIdCliente(1);
                    miusuario.setNombreUsuario(txt_usu.getText().toString());
                    miusuario.setClaveUsuario(txt_contra.getText().toString());
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

    private void setAnimation(){
        Animation desplazamientoIzquierdaDerecha= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_izquierda_derecha);

        labelUsuario.setAnimation(desplazamientoIzquierdaDerecha);
        labelContraseña.setAnimation(desplazamientoIzquierdaDerecha);
        labelConfirmContraseña.setAnimation(desplazamientoIzquierdaDerecha);

        Animation desplazamientoAbajo= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);

        layoutInputDatos.setAnimation(desplazamientoAbajo);

        ///AMIMACION DE IMAGEN ENCABEZADO DE ACTIVITY SIGN UP
        imgSignUp.setAnimation(desplazamientoIzquierdaDerecha);
        imgSignUp.setAnimation(R.raw.effect_sigup_img);
        imgSignUp.playAnimation();
        imgSignUp.setRepeatCount(2000);
    }
}