package com.co.movil.productosdemipueblo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;

public class DetalleProducto extends AppCompatActivity {

    private EditText editTextCantidadProducto;
    private int cantidadProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        editTextCantidadProducto = findViewById(R.id.editTextCantidadProducto);
        cantidadProducto = 0;
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