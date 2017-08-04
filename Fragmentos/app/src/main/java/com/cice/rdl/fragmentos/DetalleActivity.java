package com.cice.rdl.fragmentos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cice.rdl.fragmentos.Modelo.Cliente;

public class DetalleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        // Fragmento
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        FragmentoDetalle fragmento_detalle = new FragmentoDetalle();
        transaction.add(R.id.frame_layout_detalle_detalle, fragmento_detalle);
        transaction.commit();

        // Recuperar el parámetro
        String nombre = (String)getIntent().getExtras().getSerializable("nombre");
        String apellido = (String)getIntent().getExtras().getSerializable("apellido");
        String direccion = (String)getIntent().getExtras().getSerializable("direccion");
        String email = (String)getIntent().getExtras().getSerializable("email");
        Cliente cliente_param = new Cliente(nombre, apellido, direccion, email);
        fragmento_detalle.setCliente(cliente_param);
    }
}
