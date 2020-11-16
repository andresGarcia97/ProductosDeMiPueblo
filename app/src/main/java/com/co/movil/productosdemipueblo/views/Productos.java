package com.co.movil.productosdemipueblo.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.adapters.ProductoAdapter;
import com.co.movil.productosdemipueblo.clases.Producto;
import com.co.movil.productosdemipueblo.util.ActionBarUtil;
import com.co.movil.productosdemipueblo.util.GlobalInfo;

import java.util.List;

public class Productos extends AppCompatActivity {

    public ListView listViewProductos;
    private ProductoAdapter productoAdapter;
    private TextView nombreNegocioProductos;
    private ActionBarUtil actionBarUtil;
    private List<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        initComponents();
        crearListaProductos();
        selectedProductoItem();
    }

    @Override
    protected void onResume() {
        super.onResume();
        crearListaProductos();
        selectedProductoItem();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.productosDisponibles));
        listViewProductos = findViewById(R.id.listViewProductos);
        nombreNegocioProductos = findViewById(R.id.nombreNegocioProductos);
        nombreNegocioProductos.setText(GlobalInfo.NEGOCIO.getNombre());
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
        if (GlobalInfo.NEGOCIO.getProductosDisponibles() == null || GlobalInfo.NEGOCIO.getProductosDisponibles().isEmpty()) {
            finish();
        } else {
            productos = GlobalInfo.NEGOCIO.getProductosDisponibles();
            productoAdapter = new ProductoAdapter(getApplicationContext(), productos);
            listViewProductos.setAdapter(productoAdapter);
        }
    }

    private void selectedProductoItem() {
        listViewProductos.setOnItemClickListener((adapterView, view, i, l) -> {
            GlobalInfo.PRODUCTO = productoAdapter.getItem(i);
            lanzarActivityDetalleProducto(view);
        });
    }

    @Override
    public String toString() {
        return "Productos{" +
                "productos=" + productos +
                '}';
    }
}