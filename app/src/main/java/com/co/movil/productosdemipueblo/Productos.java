package com.co.movil.productosdemipueblo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.co.movil.productosdemipueblo.R;

public class Productos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
    }

    public void lanzarActivitySolicitarProductos(View view) {
        Intent intent = new Intent(this, SolicitarProductos.class);
        startActivity(intent);
    }

    public void lanzarActivityDetalleProducto(View view) {
        Intent intent = new Intent(this, DetalleProducto.class);
        startActivity(intent);
    }
}