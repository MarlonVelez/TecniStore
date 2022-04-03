package com.example.tienda_op_2.carga_de_datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.modelo.Carrito;
import com.example.tienda_op_2.modelo.Usuario;

import java.util.ArrayList;

public class CargarUsuario {
    
    private Context context;
    private ArrayList<Usuario> usuarioArrayList= new ArrayList<>();

    public CargarUsuario(Context context) {
        this.context = context;
    }

    public CargarUsuario() {
    }

    public String listarProductosCarrito(){
        SQLiteOpenHelper base= new SQLiteOpenHelper(context);
        SQLiteDatabase open= base.getReadableDatabase();

        Cursor fila= open.rawQuery("select * from usuario",null );

        if (fila.moveToFirst()){
            do{
                Usuario carrito= new Usuario();


                carrito.setNombreUsuario(fila.getString(1));
                carrito.setTipoUsuario(fila.getString(2));
                carrito.setClaveUsuario(fila.getString(3));
                carrito.setEstadoUsuario(fila.getString(4));

                usuarioArrayList.add(carrito);
            }while (fila.moveToNext());
            base.close();
            open.close();

            for (int i = 0; i < usuarioArrayList.size(); i++) {
                System.out.println(usuarioArrayList.get(i).getEstadoUsuario());
            }

            return usuarioArrayList.get(0).getEstadoUsuario();

            //mostrarCarrito(usuarioArrayList);
            //carritoAdapter.notifyDataSetChanged();

        }else{
            Toast.makeText(context, "No hay datos registrados", Toast.LENGTH_LONG).show();
            return "U";
        }
    }


}
