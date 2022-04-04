package com.example.tienda_op_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;

public class PantallaInicio extends AppCompatActivity {

    private Button signUp;
    private Button signIn;
    private TextView saltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);
        signIn= findViewById(R.id.btnSignIn);
        signUp= findViewById(R.id.btnSignUp);
        saltar= findViewById(R.id.btnSaltar);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp1= new Intent(PantallaInicio.this, SignUp1.class);
                startActivity(signUp1);
                finish();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login= new Intent( PantallaInicio.this, Inicio_Login.class);
                startActivity(login);
                finish();
            }
        });

        saltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Mensaje de aletar de salto del registro*/
                new AlertDialog.Builder(PantallaInicio.this)
                        .setTitle("¡Alerta!")
                        .setIcon(R.drawable.icon_warning)
                        .setMessage("Si saltas el inicio de sesion o el registro, tendras que hacerlo luego si quieres comprar algun producto \n"
                                +"\n ¿Estas de acuerdo con esto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                /*Si es que salta del registro se abrira directamente el Home
                                * y se creara un usuario temporal mutable para evitar volver a mostrar la ventana de
                                * registro*/
                                Intent main= new Intent( PantallaInicio.this, MainActivity.class);
                                /*Se llama al metodo para crear usuario temporal dentro de mi BD SQLite*/
                                SQLiteOpenHelper bd= new SQLiteOpenHelper(PantallaInicio.this);
                                bd.agregarUsuario("Invitado", "cliente", "000", "por registrar");
                                startActivity(main);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(PantallaInicio.this, "Porcede a iniciar sesion o registrarte", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

    }

}