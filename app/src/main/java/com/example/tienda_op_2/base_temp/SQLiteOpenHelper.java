package com.example.tienda_op_2.base_temp;


import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    private static String NOMBRE_BD= "base_temp";
    private static int VERSION_BD= 1;
    private static String TABLA_CARRITO= "create table carrito (" +
            "id int primary key, " +
            "nombreProducto text, " +
            "descripcionProducto text, " +
            "precioProducto double, " +
            "cantidadCompra int, " +
            "imagenProducto text)";

    public SQLiteOpenHelper(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);

    }

    @Override
    public void onCreate(SQLiteDatabase base) {

        base.execSQL(TABLA_CARRITO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_CARRITO);
        sqLiteDatabase.execSQL(TABLA_CARRITO);
    }

    public boolean agregarCarrito(/*int id,*/ String nombre, String descripcion, String precio, String cantidad, String img){
        SQLiteDatabase bd= getWritableDatabase();

        if (bd!=null){
            try{
                //bd.execSQL("INSERT INTO carrito VALUES('"+id+"','"+cedula+"','"+nombre+"','"+apellido+"','"+telefono+"','"+email+"')");
                bd.execSQL("INSERT INTO carrito VALUES(null,'"+nombre+"','"+descripcion+"',"+precio+","+cantidad+",'"+img+"')");
                bd.close();
                return true;
            }catch (SQLiteConstraintException e){
                return false;
            }

        }else{
            return false;
        }
    }

    public void editarCarrito(String id, String cedula, String nombre, String apellido, String email, String telefono){
        SQLiteDatabase bd= getWritableDatabase();
        bd.execSQL("UPDATE carrito SET cedula='"+cedula+"', nombre='"+nombre+"', apellido='"+apellido+"', email='"+email+"', telefono='"+telefono+"' WHERE id="+id);
        bd.close();
    }

    public void eliminarCarrito(String id){
        SQLiteDatabase bd= getWritableDatabase();
        bd.execSQL("DELETE FROM carrito WHERE id="+id);
        bd.close();
    }
}
