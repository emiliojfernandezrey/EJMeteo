package com.cice.rdl.fragmentos.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cice.rdl.fragmentos.Modelo.Cliente;
import com.cice.rdl.fragmentos.R;

import java.util.List;

/**
 * Created by cice on 28/3/17.
 */

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private View.OnClickListener listener;

    private Context contexto;
    private List<Cliente> datos;


    public ClienteAdapter(@NonNull Context context, @LayoutRes int resource, List<Cliente> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.datos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View itemView = inflater.inflate(R.layout.item_lista, null);

        TextView nombre = (TextView) itemView.findViewById(R.id.nombre);
        nombre.setText(datos.get(position).getNombre());
        TextView apellido = (TextView) itemView.findViewById(R.id.apellido);
        apellido.setText(datos.get(position).getApellido());

        return itemView;
    }
}

