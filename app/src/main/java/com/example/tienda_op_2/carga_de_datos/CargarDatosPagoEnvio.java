package com.example.tienda_op_2.carga_de_datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.modelo.Carrito;
import com.example.tienda_op_2.modelo.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CargarDatosPagoEnvio {

    private Context context;
    private ArrayList<Cliente> miCliente= new ArrayList<>();

    public CargarDatosPagoEnvio(Context context) {
        this.context = context;
    }

    /* private static String TABLA_DATOS_CLIENTE= "create table datos_cliente (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nombre text, " +
            "apellido text, " +
            "cedula text, " +
            "fecha_nac text, " +
            "email text, " +
            "telefono text, " +
            "direccion text)";*/

    public ArrayList<Cliente> listarDatosCliente(){
        SQLiteOpenHelper base= new SQLiteOpenHelper(context);
        SQLiteDatabase open= base.getReadableDatabase();
        Cursor fila= open.rawQuery("select * from datos_cliente",null );

        if (fila.moveToFirst()) {

            do {
                Cliente cliente = new Cliente();
                cliente.setNombre(fila.getString(1));
                cliente.setApellido(fila.getString(2));
                cliente.setCedula(fila.getString(3));
                cliente.setFechaNacimiento(parseDate(fila.getString(4)));
                cliente.setCorreo(fila.getString(5));
                cliente.setTelefono(fila.getString(6));
                cliente.setDireccion(fila.getString(7));

                miCliente.add(cliente);
                base.close();
                open.close();
                return miCliente;

            } while (fila.moveToNext());
        }else {
            base.close();
            open.close();
            return null;
        }
    }

    public String listarDatosTargeta(){
        SQLiteOpenHelper base= new SQLiteOpenHelper(context);
        SQLiteDatabase open= base.getReadableDatabase();
        Cursor fila= open.rawQuery("select * from datos_cliente",null );

        if (fila.moveToFirst()) {

            do {

                String numTarjeta= fila.getString(1);

                base.close();
                open.close();
                return numTarjeta;

            } while (fila.moveToNext());
        }else {
            base.close();
            open.close();
            return null;
        }
    }



    private Date parseDate(String fechaCadena){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = formato.parse(fechaCadena);
            return fecha;
        } catch (ParseException e) {
            System.out.println("ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! ERROR!! "+e);
            return null;
        }
    }

}
