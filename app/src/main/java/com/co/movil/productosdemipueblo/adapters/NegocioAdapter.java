package com.co.movil.productosdemipueblo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.clases.Negocio;

import java.util.List;

public class NegocioAdapter extends BaseAdapter {

    private final List<Negocio> listaNegociosIN;
    private final List<Negocio> listaNegociosOUT;
    private final LayoutInflater layoutInflater;

    public NegocioAdapter(Context context, List<Negocio> negocios) {
        listaNegociosIN = negocios;
        listaNegociosOUT = negocios;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaNegociosIN.size();
    }

    @Override
    public Negocio getItem(int i) {
        return listaNegociosOUT.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.negocio_layaout, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.imagen.setImageResource(listaNegociosOUT.get(i).getImagen());
        holder.nombre.setText(listaNegociosOUT.get(i).getNombre());
        holder.direccion.setText(listaNegociosOUT.get(i).getDireccion());
        holder.descripcion.setText(listaNegociosOUT.get(i).getDescripcion());
        return view;
    }

    class ViewHolder {
        ImageView imagen;
        TextView nombre;
        TextView direccion;
        TextView descripcion;

        public ViewHolder(View view) {
            super();
            imagen = view.findViewById(R.id.imagen);
            nombre = view.findViewById(R.id.nombre);
            direccion = view.findViewById(R.id.producto);
            descripcion = view.findViewById(R.id.descripcion);
        }
    }

}
