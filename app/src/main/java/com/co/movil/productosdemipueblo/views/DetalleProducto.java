package com.co.movil.productosdemipueblo.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.util.ActionBarUtil;

public class DetalleProducto extends AppCompatActivity {

    private EditText editTextCantidadProducto;
    private int cantidadProducto;
    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        initComponents();
    }

    private void initComponents(){
        editTextCantidadProducto = findViewById(R.id.editTextCantidadProducto);
        cantidadProducto = 0;
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.detalleProducto));
    }

    public void restarUno(View view) {
        cambiarCantidad(false);
    }

    public void agregarUno(View view) {
        cambiarCantidad(true);
    }

    public void cambiarCantidad(boolean sumaOresta) {
        String cantidadString = editTextCantidadProducto.getText().toString();
        if (("".equals(cantidadString))) {
            cantidadProducto = 0;
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

    public void lanzarActivityProductos(View view) {
        Intent intent = new Intent(this, Productos.class);
        startActivity(intent);
    }
}