package com.co.movil.productosdemipueblo.views;

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

public class DetalleProducto extends AppCompatActivity {

    private EditText editTextCantidadProducto;
    private int cantidadProducto;
    private ActionBarUtil actionBarUtil;
    private ImageView imageViewProductoDetalle;
    private TextView textViewDescripcionProducto;
    private TextView textViewPrecioProducto;
    private Boolean duplicado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        initComponents();
        setLayout();
    }

    private void initComponents() {
        editTextCantidadProducto = findViewById(R.id.editTextCantidadProducto);
        cantidadProducto = 1;
        duplicado = false;
        editTextCantidadProducto.setText(String.valueOf(cantidadProducto));
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.detalleProducto));
    }

    private void setLayout() {
        imageViewProductoDetalle = findViewById(R.id.imageViewProductoDetalle);
        textViewDescripcionProducto = findViewById(R.id.textViewDescripcionProducto);
        textViewPrecioProducto = findViewById(R.id.textViewPrecioProducto);

        imageViewProductoDetalle.setImageResource(GlobalInfo.PRODUCTO.getImagen());
        textViewDescripcionProducto.setText(GlobalInfo.PRODUCTO.getDescripcion());
        textViewPrecioProducto.setText(String.valueOf(GlobalInfo.PRODUCTO.getPrecio()));
    }

    public void restarUno(View view) {
        cambiarCantidad(false);
    }

    public void agregarUno(View view) {
        cambiarCantidad(true);
    }

    public void cambiarCantidad(boolean sumaOresta) {
        String cantidadString = editTextCantidadProducto.getText().toString();
        if ((cantidadString.isEmpty())) {
            cantidadProducto = 1;
        } else {
            cantidadProducto = Integer.parseInt(cantidadString);
            if (sumaOresta && cantidadProducto >= 0) {
                cantidadProducto++;
            } else if (!sumaOresta && cantidadProducto > 0) {
                cantidadProducto--;
            }
        }
        editTextCantidadProducto.setText(String.valueOf(cantidadProducto));
    }

    private void lanzarActivityProductos() {
        Intent intent = new Intent(this, Productos.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void evitarDuplicados() {
        GlobalInfo.PRODUCTOS.forEach(producto -> {
            if (producto.equals(GlobalInfo.PRODUCTO)) {
                int nuevaCantidad = producto.getCantidad() + GlobalInfo.PRODUCTO.getCantidad();
                producto.setCantidad(nuevaCantidad);
                duplicado = true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void agregarProducto(View view) {
        GlobalInfo.PRODUCTO.setCantidad(cantidadProducto);
        evitarDuplicados();
        if(!duplicado){
            GlobalInfo.PRODUCTOS.add(GlobalInfo.PRODUCTO);
        }
        lanzarActivityProductos();
    }

    public void salirSinAgregar(View view) {
        lanzarActivityProductos();
    }
}