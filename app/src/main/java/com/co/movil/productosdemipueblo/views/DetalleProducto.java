package com.co.movil.productosdemipueblo.views;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.util.ActionBarUtil;
import com.co.movil.productosdemipueblo.util.GlobalInfo;

import java.util.concurrent.atomic.AtomicBoolean;

public class DetalleProducto extends AppCompatActivity {

    private EditText editTextCantidadProducto;
    private int cantidadProducto = 1;
    private ActionBarUtil actionBarUtil;
    private ImageView imageViewProductoDetalle;
    private TextView textViewDescripcionProducto;
    private TextView textViewPrecioProducto;
    private TextView textViewCantidadDisponible;
    int cantidadDisponible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        initComponents();
        setLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLayout();
    }

    private void initComponents() {
        editTextCantidadProducto = findViewById(R.id.editTextCantidadProducto);
        editTextCantidadProducto.setText(String.valueOf(cantidadProducto));
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.detalleProducto));
        imageViewProductoDetalle = findViewById(R.id.imageViewProductoDetalle);
        textViewDescripcionProducto = findViewById(R.id.textViewDescripcionProducto);
        textViewPrecioProducto = findViewById(R.id.textViewPrecioProducto);
        textViewCantidadDisponible = findViewById(R.id.textViewCantidadDisponible);
    }

    private void setLayout() {
        cantidadDisponible =  GlobalInfo.NEGOCIO.getProductosDisponibles().get(GlobalInfo.INDEX_PRODUCTO_SELECCIONADO).getCantidadDisponible();
        if (GlobalInfo.PRODUCTO.getImagen() == 0 || GlobalInfo.PRODUCTO.getDescripcion() == null
                || GlobalInfo.PRODUCTO.getPrecio() == 0 || cantidadDisponible < 0) {
            finish();
        } else {
            imageViewProductoDetalle.setImageResource(GlobalInfo.PRODUCTO.getImagen());
            textViewDescripcionProducto.setText(GlobalInfo.PRODUCTO.getDescripcion());
            textViewPrecioProducto.setText(String.valueOf(GlobalInfo.PRODUCTO.getPrecio()));
            textViewCantidadDisponible.setText(getString(R.string.disponible).concat(" ").concat(String.valueOf(cantidadDisponible)));
        }
    }

    private int obtenerCantidadProducto() {
        String cantidadString = editTextCantidadProducto.getText().toString();
        return cantidadString.isEmpty() ? 0 : Integer.parseInt(cantidadString);
    }

    public void restarUno(View view) {
        cambiarCantidad(false);
    }

    public void agregarUno(View view) {
        cambiarCantidad(true);
    }

    public void cambiarCantidad(boolean sumaOresta) {
        cantidadProducto = obtenerCantidadProducto();
        if (!sumaOresta && cantidadProducto > 0) {
            cantidadProducto--;
        } else if (cantidadProducto >= cantidadDisponible) {
            alertaCantidad().show();
        } else if (sumaOresta && cantidadProducto >= 0) {
            cantidadProducto++;
        }
        editTextCantidadProducto.setText(String.valueOf(cantidadProducto));
    }

    private AlertDialog alertaCantidad() {
        AlertDialog dlg = new AlertDialog.Builder(DetalleProducto.this)
                .setTitle(R.string.cantidadMayorDisponible)
                .setMessage(R.string.escogerCantidadNoDisponible)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                }).create();
        return dlg;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void evitarDuplicados() {
        AtomicBoolean duplicado = new AtomicBoolean(false);
        GlobalInfo.PRODUCTOS.forEach(producto -> {
            if (producto.equals(GlobalInfo.PRODUCTO)) {
                producto.setCantidad(producto.getCantidad() + cantidadProducto);
                duplicado.set(true);
            }
        });
        if (!duplicado.get()) {
            GlobalInfo.PRODUCTO.setCantidad(cantidadProducto);
            GlobalInfo.PRODUCTOS.add(GlobalInfo.PRODUCTO);
        }
    }

    private void restarCantidadSeleccionada() {
        int nuevaCantidad = cantidadDisponible - cantidadProducto;
        if(cantidadDisponible > 0){
            GlobalInfo.NEGOCIO.getProductosDisponibles().get(GlobalInfo.INDEX_PRODUCTO_SELECCIONADO).setCantidadDisponible(nuevaCantidad);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void agregarProducto(View view) {
        cantidadProducto = obtenerCantidadProducto();
        if (cantidadProducto > 0 && cantidadDisponible > 0) {
            evitarDuplicados();
            restarCantidadSeleccionada();
        }
        finish();
    }

    public void salirSinAgregar(View view) {
        finish();
    }
}