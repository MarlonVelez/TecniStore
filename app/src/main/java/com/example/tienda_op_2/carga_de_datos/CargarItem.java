package com.example.tienda_op_2.carga_de_datos;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tienda_op_2.CarritoCompras;
import com.example.tienda_op_2.R;
import com.example.tienda_op_2.base_temp.SQLiteOpenHelper;
import com.example.tienda_op_2.modelo.ItemLayout;

import java.util.ArrayList;

public class CargarItem {
    Context context;
    TextView txtTotal;


    public void prueba(){
        ArrayList<ItemLayout> arrayListItem= new ArrayList<>();
        SQLiteOpenHelper base= new SQLiteOpenHelper(context);
        SQLiteDatabase open= base.getReadableDatabase();
        Cursor fila=null;
        fila = open.rawQuery("select * from item",null );

        if (fila.moveToFirst()){
            do{
                ItemLayout item= new ItemLayout();


                item.setIdItem(fila.getInt(0));
                item.setNombreLayout(fila.getString(1));

                arrayListItem.add(item);
            }while (fila.moveToNext());

            for (int i = 0; i < arrayListItem.size(); i++) {
                System.out.println("ID ITEM: "+arrayListItem.get(i).getIdItem());

                //txtTotal=c.findViewById(arrayListItem.get(i).getIdItem());

                txtTotal.setText("LO LOGRAMOS");
            }

            base.close();
            open.close();

        }else{
            Toast.makeText(context, "No hay items registrados", Toast.LENGTH_LONG).show();
        }
    }
}
