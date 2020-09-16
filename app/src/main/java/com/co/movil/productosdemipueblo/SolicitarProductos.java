package com.co.movil.productosdemipueblo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.adapters.SolicitudProductoAdapter;
import com.co.movil.productosdemipueblo.clases.Producto;

import java.util.ArrayList;
import java.util.List;

public class SolicitarProductos extends AppCompatActivity {

    public ListView listViewSolicitudProductos;
    private SolicitudProductoAdapter adaptadorSolicitudes;
    private TextView textViewTotal;
    protected List<Producto> productos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_productos);
        crearListaProductos();
        calcularTotal();
    }

    private void crearListaProductos () {
        listViewSolicitudProductos = findViewById(R.id.ListViewSolicitudProductos);
        productos.add(new Producto(R.drawable.java,"Java version 8",1, "El mejor lenguaje de back",50000));
        productos.add(new Producto(R.drawable.html5,"HTML version 5",1, "Maquetado web",40000));
        productos.add(new Producto(R.drawable.net,".net",1, "mejor que C++, entorno horrible",30000));
        productos.add(new Producto(R.drawable.typescript,"Typescript",1, "mejoras de Javascript",20000));
        adaptadorSolicitudes = new SolicitudProductoAdapter(getApplicationContext(),productos);
        listViewSolicitudProductos.setAdapter(adaptadorSolicitudes);
    }

    private void calcularTotal () {
        textViewTotal = findViewById(R.id.textViewTotal);
        int total = 0;
        for(Producto producto: productos) {
            total = total + producto.getCantidad()*producto.getPrecio();
        }
        textViewTotal.setText("Total: "+String.valueOf(total));
    }

    public void lanzarActivityDatosCliente(View view) {
        Intent intent = new Intent(this, DatosCliente.class);
        startActivity(intent);
    }
}