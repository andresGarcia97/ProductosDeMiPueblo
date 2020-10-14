package com.co.movil.productosdemipueblo.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.adapters.SolicitudProductoAdapter;
import com.co.movil.productosdemipueblo.clases.Producto;
import com.co.movil.productosdemipueblo.util.ActionBarUtil;
import com.co.movil.productosdemipueblo.util.DialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class SolicitarProductos extends AppCompatActivity {

    public ListView listViewSolicitudProductos;
    private SolicitudProductoAdapter adaptadorSolicitudes;
    private TextView textViewTotal;
    protected List<Producto> productos = new ArrayList<>();
    private ActionBarUtil actionBarUtil;

    @Override
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
        productos.add(new Producto(R.drawable.java, "Java version 8", 1, "El mejor lenguaje de back", 50000));
        productos.add(new Producto(R.drawable.typescript, "Typescript", 1, "mejoras de Javascript", 20000));
        productos.add(new Producto(R.drawable.html5, "HTML version 5", 2, "Maquetado web", 40000));
        productos.add(new Producto(R.drawable.net, ".net", 1, "mejor que C++, entorno horrible", 30000));
        productos.add(new Producto(R.drawable.java, "Java version 8", 1, "El mejor lenguaje de back", 50000));
        adaptadorSolicitudes = new SolicitudProductoAdapter(getApplicationContext(), productos);
        listViewSolicitudProductos.setAdapter(adaptadorSolicitudes);
    }

    private void calcularTotal() {
        textViewTotal = findViewById(R.id.textViewTotal);
        int total = 0;
        for (Producto producto : productos) {
            total = total + producto.getCantidad() * producto.getPrecio();
        }
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

    public Dialog createNewDialog(final int posicion) {
        Producto elementoSeleccionado = productos.get(posicion);
        AlertDialog dlg = new AlertDialog.Builder(SolicitarProductos.this)
                .setTitle(R.string.eliminarProducto)
                .setMessage(elementoSeleccionado.getNombre())
                .setPositiveButton(R.string.aceptarEliminado, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        productos.remove(posicion);
                        adaptadorSolicitudes = new SolicitudProductoAdapter(getApplicationContext(), productos);
                        listViewSolicitudProductos.setAdapter(adaptadorSolicitudes);
                        calcularTotal();
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setIcon(elementoSeleccionado.getImagen())
                .create();
        return dlg;
    }

    private void productoSeleccionadoEliminar() {
        listViewSolicitudProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                createNewDialog(i).show();
            }
        });
    }

}