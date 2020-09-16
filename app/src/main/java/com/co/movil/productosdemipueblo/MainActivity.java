package com.co.movil.productosdemipueblo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.co.movil.productosdemipueblo.adapters.NegocioAdapter;
import com.co.movil.productosdemipueblo.clases.Negocio;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listViewNegocios;
    private NegocioAdapter adaptadorNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewNegocios = findViewById(R.id.listViewNegocios);
        crearListaNegocios();
        selectedNegocioItem();
    }

    private void crearListaNegocios() {
        List<Negocio> negocios = new ArrayList<>();
        negocios.add(new Negocio(R.drawable.among, "EL primer negocio", "Una nave espacial", "suministros y tareas"));
        negocios.add(new Negocio(R.drawable.callofduty, "EL Segundo negocio", "Un mundo en guerra", "shooter en primera persona"));
        negocios.add(new Negocio(R.drawable.lastofus, "EL Tercer negocio", "Un mundo post apocaliptico", "supervivencia"));
        adaptadorNegocio = new NegocioAdapter(getApplicationContext(), negocios);
        listViewNegocios.setAdapter(adaptadorNegocio);
    }

    private void lanzarActivityProductos(View view) {
        Intent intent = new Intent(this, Productos.class);
        startActivity(intent);
    }

    private void selectedNegocioItem() {
        listViewNegocios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lanzarActivityProductos(view);
            }
        });
    }
}