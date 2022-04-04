package com.example.tienda_op_2;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;


public class SignUp3 extends AppCompatActivity {

    //public static String[] listaClientes3 = new String[2];

    private String nombre_usuario, apellido_usuario, telefono_usuario;
    private EditText txt_cedulaU, txt_domiciolioU;
    private ImageView backView;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);

        backView= findViewById(R.id.btnBackView);
        txt_cedulaU=findViewById(R.id.txt_cedula_usuario);
        txt_domiciolioU=findViewById(R.id.txt_domicilio_usuario);


        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaInicial= new Intent(SignUp3.this, SignUp2.class);

                startActivity(pantallaInicial);
                finish();
            }
        });

        btnSiguiente= findViewById(R.id.btn_siguiente_datos_pago);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_cedulaU.getText().toString().isEmpty()&&txt_domiciolioU.getText().toString().isEmpty()){
                    Toast.makeText(SignUp3.this, "Llene todos los campos ", Toast.LENGTH_SHORT).show();
                }else{
                    postDatosVolley(txt_cedulaU.getText().toString(),txt_domiciolioU.getText().toString());
                }
                Intent SingUp4= new Intent(SignUp3.this, SignUp4.class);
                startActivity(SingUp4);
                finish();
            }
        });
    }

    private void postDatosVolley(String cedula, String direccion){
        //URL de la API para cargar la informacion
        String url="https://tecnistoreaapi.rj.r.appspot.com:443/usuario";



        StringRequest request = new StringRequest(Request.Method.POST,url, new com.android.volley.Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                
                txt_cedulaU.setText("");
                txt_domiciolioU.setText("");

                Toast.makeText(SignUp3.this, "Datos Guardados Correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new com.android.volley.Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(SignUp3.this,"Falla al obtener la respuesta = " + error,Toast.LENGTH_SHORT).show();
            }
        });

    }

}