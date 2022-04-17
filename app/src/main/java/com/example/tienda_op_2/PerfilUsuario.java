package com.example.tienda_op_2;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;

public class PerfilUsuario extends AppCompatActivity {

    LottieAnimationView imgbackgroundUserProfile, imgbackgroundUserProfile2, imgbackgroundUserProfile3;
    TextInputEditText txtUserNameProfile;
    TextInputEditText txtEmailProfile;
    TextInputEditText txtClaveProfile;
    TextView btnEditarDatos;
    Button btnGuardarCambios;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        toolbar= findViewById(R.id.toolBar);

        //Configuracion de ToolBar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_btn_back_view));
        Intent home= new Intent(this, MainActivity.class);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                startActivity(home);
                finish();
            }
        });



        imgbackgroundUserProfile= findViewById(R.id.img_userProfileBackground);
        imgbackgroundUserProfile.setAnimation(R.raw.background_user_profile);
        imgbackgroundUserProfile.playAnimation();
        imgbackgroundUserProfile.setRepeatCount(2000);

        imgbackgroundUserProfile2= findViewById(R.id.img_userProfileBackground2);
        imgbackgroundUserProfile2.setAnimation(R.raw.background_user_profile);
        imgbackgroundUserProfile2.playAnimation();
        imgbackgroundUserProfile2.setRepeatCount(2000);

        imgbackgroundUserProfile3= findViewById(R.id.img_userProfileBackground3);
        imgbackgroundUserProfile3.setAnimation(R.raw.background_user_profile);
        imgbackgroundUserProfile3.playAnimation();
        imgbackgroundUserProfile3.setRepeatCount(2000);

        txtUserNameProfile= findViewById(R.id.txtNameUserProfile);
        txtEmailProfile= findViewById(R.id.txtEmailUserProfile);
        txtClaveProfile= findViewById(R.id.txtClaveUserProfile);

        btnEditarDatos= findViewById(R.id.btnEditarDatos);
        btnGuardarCambios= findViewById(R.id.btnGuardarCambios);

        txtUserNameProfile.setEnabled(false);
        txtEmailProfile.setEnabled(false);
        txtClaveProfile.setEnabled(false);
        btnGuardarCambios.setEnabled(false);

        btnEditarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtUserNameProfile.setEnabled(true);
                txtEmailProfile.setEnabled(true);
                txtClaveProfile.setEnabled(true);
                btnGuardarCambios.setEnabled(true);
            }
        });

    }
}