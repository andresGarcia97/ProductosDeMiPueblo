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

public class ProductoAdapter extends BaseAdapter {

    private final List<Producto> listaProdutosIN;
    private final List<Producto> listaProductosOUT;
    private final LayoutInflater layoutInflater;

    public ProductoAdapter(Context context, List<Producto> productos) {
        this.listaProdutosIN = productos;
        this.listaProductosOUT = productos;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaProdutosIN.size();
    }

    @Override
    public Object getItem(int i) {
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
            view = layoutInflater.inflate(R.layout.producto_layaout, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.imagen.setImageResource(listaProductosOUT.get(i).getImagen());
        holder.productoName.setText(listaProductosOUT.get(i).getNombre());
       /* if(listaProductosOUT.size()>(i+1)) {


            holder.imagen2.setImageResource(listaProductosOUT.get(i + 1).getImagen());
            holder.productoName2.setText(listaProductosOUT.get(i + 1).getNombre());
        }
*/
        return view;
    }
    class ViewHolder {
        ImageView imagen;
        TextView productoName;
        ImageView imagen2;
        TextView productoName2;

        public ViewHolder(View view) {
            super();
            imagen = view.findViewById(R.id.imagen);
            productoName = view.findViewById(R.id.producto);
            imagen2 = view.findViewById(R.id.imagen2);
            productoName2 = view.findViewById(R.id.producto2);

        }
    }
}
