package com.co.movil.productosdemipueblo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.clases.Producto;

import java.util.List;

public class SolicitudProductoAdapter extends BaseAdapter {

    private final List<Producto> listaProductosIN;
    private final List<Producto> listaProductosOUT;
    private final LayoutInflater layoutInflater;

    public SolicitudProductoAdapter(Context context, List<Producto> productos) {
        listaProductosIN = productos;
        listaProductosOUT = productos;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaProductosIN.size();
    }

    @Override
    public Producto getItem(int i) {
        return listaProductosOUT.get(i);
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
            view = layoutInflater.inflate(R.layout.solicitar_producto_layout, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.imagen.setImageResource(listaProductosOUT.get(i).getImagen());
        holder.nombre.setText("Nombre:  ".concat(listaProductosOUT.get(i).getNombre()));
        holder.cantidad.setText("Cantidad: ".concat(String.valueOf(listaProductosOUT.get(i).getCantidad())));
        holder.precio.setText("Precio:   ".concat(String.valueOf(listaProductosOUT.get(i).getPrecio())));
        return view;
    }

    class ViewHolder {
        ImageView imagen;
        TextView nombre;
        TextView cantidad;
        TextView precio;

        public ViewHolder(View view) {
            super();
            imagen = view.findViewById(R.id.imageViewProductoSolicitud);
            nombre = view.findViewById(R.id.textViewNombreProducto);
            cantidad = view.findViewById(R.id.textViewcantidadProducto);
            precio = view.findViewById(R.id.textViewPrecioProducto);
        }
    }
}
