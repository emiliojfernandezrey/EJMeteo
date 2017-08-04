package com.cice.rdl.fragmentos.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cice.rdl.fragmentos.Modelo.Cliente;
import com.cice.rdl.fragmentos.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cice on 28/3/17.
 */

public class ClientesSQLiteHelper extends SQLiteOpenHelper { //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateTable1 = "CREATE TABLE Usuario (usuario TEXT, contrasena TEXT)";
    String sqlCreateTable2 = "CREATE TABLE Cliente (nombre TEXT, apellido TEXT, direccion TEXT, email TEXT)";

    public ClientesSQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos las tablas
        db.execSQL(sqlCreateTable1);
        db.execSQL(sqlCreateTable2);
        // Rellenamos usuarios
        for(int i=1; i<=5; i++) {
            //Generamos los datos
            String usuario = "usuario" + i;
            String contrasena = "usuario" + i;
            //Insertamos los datos en la tabla Usuario
            db.execSQL("INSERT INTO Usuario (usuario, contrasena) " + "VALUES ('" + usuario + "', '" + contrasena +"');");
        }
        // Rellenamos clientes
        for(int i=1; i<=5; i++) {
            //Generamos los datos
            String nombre = "nombre" + i;
            String apellido = "apellido" + i;
            String direccion = "dirección" + i;
            String email = "email" + i;
            //Insertamos los datos en la tabla Clientes
            db.execSQL("INSERT INTO Cliente (nombre, apellido, direccion, email) " + "VALUES ('" + nombre + "', '" + apellido + "', '" + direccion + "', '" + email +"');");
        }
        //Cerramos la base de datos
        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        // Se crea la nueva versión de la tabla
        // No hay cambios
    }


    public List<Cliente> getDataClientes() {
        //Abrimos la base de datos 'DBClientes' en modo escritura

        SQLiteDatabase db = getWritableDatabase();

        //Creamos la lista de clientes
        List<Cliente> listaClientes=new ArrayList<>();

        //Si hemos abierto correctamente la base de datos
        if(db != null) {
            String[] campos = new String[] {"nombre", "apellido", "direccion", "email"};

            Cursor c = db.query("Cliente", campos, null, null, null, null, null);

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    String nombre= c.getString(0);
                    String apellido = c.getString(1);
                    String direccion = c.getString(2);
                    String telefono = c.getString(3);
                    listaClientes.add(new Cliente(nombre, apellido, direccion, telefono));
                } while(c.moveToNext());
            }
            c.close();
        }
        return listaClientes;
    }

    public List<Usuario> getDataUsuarios() {
        //Abrimos la base de datos 'DBClientes' en modo escritura

        SQLiteDatabase db = getWritableDatabase();

        //Creamos la lista de usuarios
        List<Usuario> listaUsuarios=new ArrayList<>();

        //Si hemos abierto correctamente la base de datos
        if(db != null) {
            String[] campos = new String[] {"usuario", "contrasena"};

            Cursor c = db.query("Usuario", campos, null, null, null, null, null);

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    String usuario= c.getString(0);
                    String contrasena = c.getString(1);
                    listaUsuarios.add(new Usuario(usuario, contrasena));
                } while(c.moveToNext());
            }
            c.close();
        }
        return listaUsuarios;
    }

    public String getContrasena(String usuario) {
        //Abrimos la base de datos 'DBClientes' en modo escritura

        SQLiteDatabase db = getWritableDatabase();
        String contrasena = "";

        //Si hemos abierto correctamente la base de datos
        if(db != null) {
            String[] campos = new String[] {"contrasena"};
            String[] args = new String[] {usuario};

            Cursor c = db.query("Usuario", campos, "usuario=?" ,args, null, null, null);

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    contrasena = c.getString(0);
                } while(c.moveToNext());
            }
            c.close();
        }
        return contrasena;
    }
}

