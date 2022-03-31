package com.example.tienda_op_2.api;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tienda_op_2.SignUp4;
import com.example.tienda_op_2.modelo.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class apiUsuario {

    private boolean bandera=false;
    private Context contexto;
    private String nombreU;

    public apiUsuario(Context contexto) {
        this.contexto = contexto;
    }

    public boolean postDatosVolley2(ArrayList<Usuario> usuario){

        String url="https://tecnistoreaapi.rj.r.appspot.com:443/usuario";
        RequestQueue queue = Volley.newRequestQueue(contexto);
        StringRequest request = new StringRequest(Request.Method.POST,url, new com.android.volley.Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                //txt_usu.setText("");
                //txt_contra.setText("");
                bandera=true;
                //txt_nombreU.setText();
                //Toast.makeText(SignUp4.this, "Datos Guardados Correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new com.android.volley.Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                bandera=false;
                //Toast.makeText(SignUp4.this,"Falla al obtener la respuesta = " + error,Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                for (int i = 0; i < usuario.size(); i++) {
                    params.put("usuario", usuario.get(i).getUsuario() );
                    params.put("clave", usuario.get(i).getClave());
                    //params.put("idUsuario", String.valueOf(usuario.get(i).getIdUsuario()));
                    params.put("tipoUsuario", "cliente");
                    //params.put("idCliente", String.valueOf(usuario.get(i).getIdCliente()));
                }
                return params;

            }
        };

        queue.add(request);
        return bandera;
    }

}

