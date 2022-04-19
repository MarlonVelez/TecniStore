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

    public ArrayList<Usuario> listarUsuarioP(){
        System.out.println("A");
        SQLiteOpenHelper base= new SQLiteOpenHelper(context);
        SQLiteDatabase open= base.getReadableDatabase();

        Cursor fila= open.rawQuery("select * from usuario",null );

        if (fila.moveToFirst()){
            System.out.println("B");
            do{
                Usuario usuario= new Usuario();

                usuario.setNombreUsuario(fila.getString(1));
                usuario.setTipoUsuario(fila.getString(2));
                usuario.setClaveUsuario(fila.getString(3));
                usuario.setEstadoUsuario(fila.getString(4));

                usuarioArrayList.add(usuario);
            }while (fila.moveToNext());
            base.close();
            open.close();

            return usuarioArrayList;

        }else{
            System.out.println("C");
            Toast.makeText(context, "No olvides registrarte", Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
