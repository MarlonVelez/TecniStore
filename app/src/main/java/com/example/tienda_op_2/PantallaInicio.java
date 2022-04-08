package com.example.tienda_op_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.airbnb.lottie.LottieAnimationView;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;

public class PantallaInicio extends AppCompatActivity {

    private Button signUp;
    private Button signIn;
    private TextView saltar, txtlabelComenzar, txtMensajeInicio;
    private LottieAnimationView imgSplash, btnComenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        Animation animation1= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animation2= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);

        txtlabelComenzar= findViewById(R.id.txtLabelComenzar);
        txtMensajeInicio= findViewById(R.id.txtMensajeInicio);

        imgSplash= findViewById(R.id.imgSplash);
        imgSplash.setAnimation(animation2);
        imgSplash.setAnimation(R.raw.splash);
        imgSplash.playAnimation();
        imgSplash.setRepeatCount(20000);

        txtlabelComenzar.setAnimation(animation1);
        txtMensajeInicio.setAnimation(animation1);

        btnComenzar= findViewById(R.id.btnComenzar);

        //btnComenzar.setRepeatCount(2000);
        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSplash.setAnimation(animation1);
                txtlabelComenzar.setAnimation(animation2);
                txtMensajeInicio.setAnimation(animation2);
                btnComenzar.setAnimation(R.raw.effect_btn_comenzar);
                btnComenzar.playAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent login= new Intent( PantallaInicio.this, Inicio_Login.class);
                        startActivity(login);
                        finish();
                    }
                },3031);
            }
        });
        saltar= findViewById(R.id.btnSaltar);

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