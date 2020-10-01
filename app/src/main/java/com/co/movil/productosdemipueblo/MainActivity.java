package com.co.movil.productosdemipueblo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.adapters.NegocioAdapter;
import com.co.movil.productosdemipueblo.clases.Negocio;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listViewNegocios;
    private NegocioAdapter adaptadorNegocio;
    private String nombreNegocio = "";
    public List<Negocio> negocios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewNegocios = findViewById(R.id.listViewNegocios);
        crearListaNegocios();
        selectedNegocioItem();
    }

    private void crearListaNegocios() {
        negocios = new ArrayList<>();
        negocios.add(new Negocio(R.drawable.among, "Among US, trust nobody", "Una nave espacial", "suministros y tareas"));
        negocios.add(new Negocio(R.drawable.callofduty, "Call of Duty, kill everybody", "Un mundo en guerra", "shooter en primera persona"));
        negocios.add(new Negocio(R.drawable.lastofus, "Last of US, survive", "Un mundo post apocaliptico", "supervivencia"));
        negocios.add(new Negocio(R.drawable.master_chief, "Halo, the best of Xbox", "viajes espaciales", "ciencia ficcion"));
        adaptadorNegocio = new NegocioAdapter(getApplicationContext(), negocios);
        listViewNegocios.setAdapter(adaptadorNegocio);
    }

    private void lanzarActivityProductos(View view) {
        Intent intent = new Intent(this, Productos.class);
        intent.putExtra("nombreN",nombreNegocio);
        startActivity(intent);
    }

    private void selectedNegocioItem() {
        listViewNegocios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nombreNegocio = negocios.get(i).getNombre();
                lanzarActivityProductos(view);
            }
        });
    }
}