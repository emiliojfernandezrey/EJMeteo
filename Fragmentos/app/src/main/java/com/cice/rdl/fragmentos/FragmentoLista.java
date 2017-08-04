package com.cice.rdl.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cice.rdl.fragmentos.Modelo.Cliente;
import com.cice.rdl.fragmentos.adapters.ClienteAdapter;
import com.cice.rdl.fragmentos.database.ClientesSQLiteHelper;

import java.util.List;

/**
 * Created by cice on 29/3/17.
 */

public class FragmentoLista extends Fragment {
    MainActivity actividad;

    public void setActividad(MainActivity actividad) {
        this.actividad = actividad;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate (R.layout.fragmento_lista, container, false);

        //BB.DD.
        ClientesSQLiteHelper usdbh = new ClientesSQLiteHelper(container.getContext(), "DBClientes", null, 1);
        final List<Cliente> data = usdbh.getDataClientes();

        // Adapter
        final ListView ListaClientes = (ListView) v.findViewById(R.id.lista);
        ClienteAdapter adapter = new ClienteAdapter(container.getContext(), R.layout.item_lista, data);
        ListaClientes.setAdapter(adapter);

        ListaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Llamada a la activity para que decida qué hacer
                Cliente c = data.get(position);
                actividad.verDetalle(c);
            }
        });

        return v;

    }


}





