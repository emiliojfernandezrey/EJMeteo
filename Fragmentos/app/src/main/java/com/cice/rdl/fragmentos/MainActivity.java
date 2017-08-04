package com.cice.rdl.fragmentos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.cice.rdl.fragmentos.Modelo.Cliente;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    FrameLayout frameLayout;
    FragmentoDetalle fragmento_detalle;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        fragmentManager = getFragmentManager();

        transaction = fragmentManager.beginTransaction();

        FragmentoLista fragmento_lista = new FragmentoLista();
        fragmento_lista.setActividad(this);
        transaction.add(R.id.frame_layout_listado, fragmento_lista);

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout_detalle);
        if (frameLayout != null)
        {
            // Está en landscape
            fragmento_detalle = new FragmentoDetalle();
            transaction.add(R.id.frame_layout_detalle, fragmento_detalle);
        }

        transaction.commit();
    }

    public void verDetalle (Cliente cliente) {
        if (frameLayout != null) {
            // Está en landscape, mostramos los datos del cliente
            fragmento_detalle.setCliente (cliente);
            fragmento_detalle.refrescar();
        } else {
            // Está en portrait, llamamos a la actividad Detalle
            Intent i = new Intent(context, DetalleActivity.class);
            i.putExtra("nombre", cliente.getNombre());
            i.putExtra("apellido", cliente.getApellido());
            i.putExtra("direccion", cliente.getDireccion());
            i.putExtra("email", cliente.getEmail());
            startActivity(i);
        }
    }
}
