package com.example.tienda_op_2.adapter;


import android.os.AsyncTask;


import com.example.tienda_op_2.Inicio_Login;
import com.example.tienda_op_2.MainActivity;
import com.example.tienda_op_2.modelo.Login;
import com.example.tienda_op_2.Services.Validacion_user;

import java.util.ArrayList;


public class LoginAdapter extends  AsyncTask < Object,Void, Boolean> {

    private Validacion_user comunicacion;

    ArrayList<Login> array= Inicio_Login.arrayDatos;

    //Contructor
    public LoginAdapter(Validacion_user comunicacion) {
        this.comunicacion = comunicacion;
    }

    // Codigo que se ejcuta antes de comenzar el hilo
    @Override
    protected void onPreExecute() {
        comunicacion.toggleProgressBar(true);
    }


    // Codigo de segundo plano evaluar credenciales de usuario
    @Override
    protected Boolean doInBackground(Object... objects) {
        try{
        Thread.sleep((int)objects[2]);
        String user=objects[0].toString();
        String pass=objects[1].toString();
            for (int i=0; i<array.size();i++){
                if (array.get(i).getUsuario().equals(user) && array.get(i).getClave().equals(pass)){
                    return true;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


    // Codigo despues del hilo
    @Override
    protected void onPostExecute(Boolean bo) {
        if (bo){
            this.comunicacion.lanzarActividad(MainActivity.class);
            this.comunicacion.showMessage("Datos Correctos");
            this.comunicacion.toggleProgressBar(false);
        }else{
            this.comunicacion.showMessage("Datos Incorrectos");
            this.comunicacion.toggleProgressBar(false);
        }
    }


}
