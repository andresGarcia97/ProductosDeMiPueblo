package com.co.movil.productosdemipueblo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.adapters.NegocioAdapter;
import com.co.movil.productosdemipueblo.adapters.ProductoAdapter;
import com.co.movil.productosdemipueblo.adapters.SolicitudProductoAdapter;
import com.co.movil.productosdemipueblo.clases.Negocio;
import com.co.movil.productosdemipueblo.clases.Producto;

import java.util.ArrayList;
import java.util.List;

public class Productos extends AppCompatActivity {
    public ListView listViewProductos;
    private ProductoAdapter productoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        listViewProductos = findViewById(R.id.listViewProductos);

        crearListaProductos();
    }

    public void lanzarActivitySolicitarProductos(View view) {
        Intent intent = new Intent(this, SolicitarProductos.class);
        startActivity(intent);
    }

    public void lanzarActivityDetalleProducto(View view) {
        Intent intent = new Intent(this, DetalleProducto.class);
        startActivity(intent);

    }

    private void crearListaProductos() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(R.drawable.among, "Impostor", 1, "n/a",0));
        productos.add(new Producto(R.drawable.callofduty, "Car", 2, "shooter en primera persona", 0));
        productos.add(new Producto(R.drawable.lastofus, "last", 1, "supervivencia",1));
        productos.add(new Producto(R.drawable.among, "last", 1, "supervivencia",1));


        productoAdapter = new ProductoAdapter(getApplicationContext(), productos);
        listViewProductos.setAdapter(productoAdapter);
    }


}