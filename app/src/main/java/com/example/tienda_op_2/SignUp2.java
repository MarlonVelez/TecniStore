package com.example.tienda_op_2;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignUp2 extends AppCompatActivity {

    private String nombreUsuario, apellidoUsuario;
    private ImageView backView;
    private Button btnSiguiente;
    public static String[] listaCliente2 = new String[2];
    private TextInputEditText txt_telefonoU, txt_emailU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        Intent datosSigUp1=getIntent();
        nombreUsuario=datosSigUp1.getStringExtra("nombre_usuario");
        apellidoUsuario=datosSigUp1.getStringExtra("apellido_usuario");

        System.out.println("nombre = " + nombreUsuario);
        System.out.println("apellido = " + apellidoUsuario);


        txt_telefonoU=findViewById(R.id.txt_telefono_usuario);
        txt_emailU=findViewById(R.id.txt_email_usuario);

        backView= findViewById(R.id.btnBackView);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaInicial= new Intent(SignUp2.this, SignUp1.class);
                startActivity(pantallaInicial);
                finish();
            }
        });

        btnSiguiente= findViewById(R.id.btn_siguiente_datos_contacto);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_telefonoU.getText().toString().isEmpty()&&txt_emailU.getText().toString().isEmpty()){
                    Toast.makeText(SignUp2.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    listaCliente2[0]=txt_telefonoU.getText().toString();
                    listaCliente2[1]=txt_emailU.getText().toString();
                    Intent SingUp3= new Intent(SignUp2.this, SignUp3.class);
                    SingUp3.putExtra("nombre_usuario",nombreUsuario);
                    SingUp3.putExtra("apellido_usuario",apellidoUsuario);
                    SingUp3.putExtra("telefono_usuario",txt_telefonoU.getText().toString());
                    SingUp3.putExtra("email_usuario",txt_emailU.getText().toString());
                    startActivity(SingUp3);
                    finish();
                }

            }
        });
    }
}