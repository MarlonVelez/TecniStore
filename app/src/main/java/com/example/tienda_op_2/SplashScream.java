package com.example.tienda_op_2;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.carga_de_datos.CargarUsuario;

public class SplashScream extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_scream);
        SQLiteOpenHelper bd= new SQLiteOpenHelper(this);

        Animation animation1= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animation2= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);

        TextView textNombreApp= findViewById(R.id.titulo_tienda);
        TextView textSloganApp= findViewById(R.id.sub_titulo_tienda);
        ImageView logo= findViewById(R.id.logo_tienda);

        textNombreApp.setAnimation(animation2);
        textSloganApp.setAnimation(animation2);
        logo.setAnimation(animation1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                CargarUsuario usu= new CargarUsuario(SplashScream.this);
                String bandera= usu.listarProductosCarrito();
                if (bandera.equalsIgnoreCase("por registrar")) {
                    Intent vist_home= new Intent(SplashScream.this, MainActivity.class);
                    startActivity(vist_home);
                    finish();
                }else{
                    Intent vista_login= new Intent(SplashScream.this, PantallaInicio.class);
                    startActivity(vista_login);
                    finish();
                }
            }
        },2000);


    }
}