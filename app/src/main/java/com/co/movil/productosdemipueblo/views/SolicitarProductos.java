package com.co.movil.productosdemipueblo.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.adapters.SolicitudProductoAdapter;
import com.co.movil.productosdemipueblo.clases.Producto;
import com.co.movil.productosdemipueblo.util.ActionBarUtil;
import com.co.movil.productosdemipueblo.util.DialogBuilder;
import com.co.movil.productosdemipueblo.util.GlobalInfo;

import java.util.ArrayList;
import java.util.List;

public class SolicitarProductos extends AppCompatActivity {

    public ListView listViewSolicitudProductos;
    private SolicitudProductoAdapter adaptadorSolicitudes;
    private TextView textViewTotal;
    protected List<Producto> productos = new ArrayList<>();
    private ActionBarUtil actionBarUtil;
    private int total = 0;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_productos);
        initComponents();
        crearListaProductos();
        calcularTotal();
        productoSeleccionadoEliminar();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.productosSeleccionados));
    }

    private void crearListaProductos() {
        listViewSolicitudProductos = findViewById(R.id.ListViewSolicitudProductos);
        productos = GlobalInfo.PRODUCTOS;
        adaptadorSolicitudes = new SolicitudProductoAdapter(getApplicationContext(), productos);
        listViewSolicitudProductos.setAdapter(adaptadorSolicitudes);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calcularTotal() {
        textViewTotal = findViewById(R.id.textViewTotal);
        total = productos.stream().mapToInt(producto -> producto.getCantidad() * producto.getPrecio()).sum();
        textViewTotal.setText("Total: " + total);
    }

    public void lanzarActivityDatosCliente(View view) {
        if (productos.isEmpty()) {
            DialogBuilder.obtener().createNewAlertDialog(this, R.string.solicitudProductosVacia).show();
        } else {
            Intent intent = new Intent(this, DatosCliente.class);
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Dialog createNewDialog(final int posicion) {
        Producto elementoSeleccionado = productos.get(posicion);
        AlertDialog dlg = new AlertDialog.Builder(SolicitarProductos.this)
                .setTitle(R.string.eliminarProducto)
                .setMessage(elementoSeleccionado.getNombre())
                .setPositiveButton(R.string.aceptarEliminado, (dialog, id) -> {
                    productos.remove(posicion);
                    adaptadorSolicitudes = new SolicitudProductoAdapter(getApplicationContext(), productos);
                    listViewSolicitudProductos.setAdapter(adaptadorSolicitudes);
                    calcularTotal();
                })
                .setNegativeButton(R.string.cancelar, (dialog, id) -> {
                })
                .setIcon(elementoSeleccionado.getImagen())
                .create();
        return dlg;
    }

    private void productoSeleccionadoEliminar() {
        listViewSolicitudProductos.setOnItemClickListener((adapterView, view, i, l) -> createNewDialog(i).show());
    }

}