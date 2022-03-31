package com.example.tienda_op_2;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignUp1 extends AppCompatActivity {

    public static String[] listaCliente = new String[2];
    private ImageView backView;
    private Button btnSiguiente;

    private TextInputEditText txt_nombreU, txt_apellidoU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);

        txt_nombreU=findViewById(R.id.txt_nombre_usuario);
        txt_apellidoU=findViewById(R.id.txt_apellido_usuario);

        backView= findViewById(R.id.btnBackView);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaInicial= new Intent(SignUp1.this, PantallaInicio.class);
                startActivity(pantallaInicial);
                finish();
            }
        });

        btnSiguiente= findViewById(R.id.btn_siguiente_datos_persona);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_nombreU.getText().toString().isEmpty()&&txt_apellidoU.getText().toString().isEmpty()){
                    Toast.makeText(SignUp1.this, "LLene todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    listaCliente[0]=txt_nombreU.getText().toString();
                    listaCliente[1]=txt_apellidoU.getText().toString();

                    Intent SingUp2= new Intent(SignUp1.this, SignUp2.class);
                    SingUp2.putExtra("nombre_usuario",txt_nombreU.getText().toString());
                    SingUp2.putExtra("apellido_usuario",txt_apellidoU.getText().toString());
                    startActivity(SingUp2);
                    finish();
                }

            }
        });
    }
}