package com.co.movil.productosdemipueblo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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
        int cambio = (cantidadProducto == 0) ? 0 : (cantidadProducto--);
        String cantidad = String.valueOf(cambio);
        editTextCantidadProducto.setText(cantidad);
    }

    public void agregarUno(View view) {
        cantidadProducto++;
        String cantidad = String.valueOf(cantidadProducto);
        editTextCantidadProducto.setText(cantidad);
    }

    public void lanzarActivityProductos(View view) {
        Intent intent = new Intent(this, Productos.class);
        startActivity(intent);
    }
}