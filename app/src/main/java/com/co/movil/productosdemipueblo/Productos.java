package com.co.movil.productosdemipueblo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.adapters.ProductoAdapter;
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
        selectedProductoItem();
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
        List<Producto[]> productos = new ArrayList<>();
        Producto[] productosVector = {new Producto(R.drawable.master_chief, "Master Chief", 1, "n/a", 0),
                new Producto(R.drawable.callofduty, "CoD", 2, "shooter en primera persona", 0)};
        productos.add(productosVector);
        Producto[] productosVector1 = {new Producto(R.drawable.lastofus, "last of US", 1, "supervivencia", 1),
                new Producto(R.drawable.among, "Impostor", 1, "supervivencia", 1)};
        productos.add(productosVector1);

        productoAdapter = new ProductoAdapter(getApplicationContext(), productos);
        listViewProductos.setAdapter(productoAdapter);
    }

    private void selectedProductoItem() {
        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lanzarActivityDetalleProducto(view);
            }
        });
    }

}