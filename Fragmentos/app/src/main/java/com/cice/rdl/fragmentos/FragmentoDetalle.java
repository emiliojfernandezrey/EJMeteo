package com.cice.rdl.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cice.rdl.fragmentos.Modelo.Cliente;

/**
 * Created by cice on 29/3/17.
 */

public class FragmentoDetalle extends Fragment {
    Cliente cliente_param;
    TextView tvnombre, tvapellido, tvdireccion, tvemail;

    public void setCliente(Cliente cliente) {
        cliente_param = cliente;
    }

    public void refrescar(){
        tvnombre.setText(cliente_param.getNombre());
        tvapellido.setText(cliente_param.getApellido());
        tvdireccion.setText(cliente_param.getDireccion());
        tvemail.setText((cliente_param.getEmail()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate (R.layout.fragmento_detalle, container, false);

        tvnombre = (TextView) v.findViewById(R.id.nombre);
        tvapellido = (TextView) v.findViewById(R.id.apellido);
        tvdireccion = (TextView) v.findViewById(R.id.direccion);
        tvemail = (TextView) v.findViewById(R.id.email);

        if (cliente_param != null) {
            tvnombre.setText(cliente_param.getNombre());
            tvapellido.setText(cliente_param.getApellido());
            tvdireccion.setText(cliente_param.getDireccion());
            tvemail.setText((cliente_param.getEmail()));
        } else {
            tvnombre.setText("");
            tvapellido.setText("");
            tvdireccion.setText("");
            tvemail.setText("");
        }

        return v;
    }
}
