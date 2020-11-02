package com.co.movil.productosdemipueblo.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.adapters.NegocioAdapter;
import com.co.movil.productosdemipueblo.clases.Negocio;
import com.co.movil.productosdemipueblo.clases.Producto;
import com.co.movil.productosdemipueblo.util.ActionBarUtil;
import com.co.movil.productosdemipueblo.util.GlobalAction;
import com.co.movil.productosdemipueblo.util.GlobalInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listViewNegocios;
    private NegocioAdapter adaptadorNegocio;
    public List<Negocio> negocios;
    private List<Producto> productos;
    private ActionBarUtil actionBarUtil;
    private Button buttonCambiarNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        mostrarOcultarCambiarNegocio();
        crearListaNegocios();
        selectedNegocioItem();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.texto_inicio));
        listViewNegocios = findViewById(R.id.listViewNegocios);
        buttonCambiarNegocio = findViewById(R.id.buttonCambiarNegocio);
    }

    private void mostrarOcultarCambiarNegocio() {
        if (GlobalInfo.NEGOCIOSELECCIONADO) {
            buttonCambiarNegocio.setVisibility(View.VISIBLE);
        } else {
            buttonCambiarNegocio.setVisibility(View.GONE);
        }
    }

    private void crearListaNegocios() {

        if (!GlobalInfo.NEGOCIOSELECCIONADO) {
            productos = new ArrayList<>();
            productos.add(new Producto(R.drawable.master_chief, "Master Chief", 1, "Spartans", 4000));
            productos.add(new Producto(R.drawable.callofduty, "CoD", 1, "shooter en primera persona", 5000));
            productos.add(new Producto(R.drawable.gearsofwar, "Lokus", 1, "Covenant", 2200));
            productos.add(new Producto(R.drawable.among, "Impostor", 1, "supervivencia", 2000));
            productos.add(new Producto(R.drawable.lastofus, "last of US", 1, "supervivencia", 3500));

            List<Producto> productos1 = productos.subList(0, productos.size() - 1);
            List<Producto> productos2 = productos.subList(0, productos.size() - 2);
            List<Producto> productos3 = productos.subList(0, productos.size() - 3);
            List<Producto> productos4 = productos.subList(0, productos.size() - 4);

            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.among, "Among US, trust nobody", "Una nave espacial", "suministros y tareas", productos3));
            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.callofduty, "Call of Duty, kill everybody", "Un mundo en guerra", "shooter en primera persona", productos));
            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.lastofus, "Last of US, survive", "Un mundo post apocaliptico", "supervivencia", productos1));
            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.master_chief, "Halo, the best of Xbox", "viajes espaciales", "ciencia ficcion", productos4));
            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.gearsofwar, "Gears, Kill the Covenant", "guerras espaciales", "ciencia ficcion", productos2));

            negocios = GlobalInfo.NEGOCIOS;
        } else {
            negocios = new ArrayList<>();
            negocios.add(GlobalInfo.NEGOCIO);
        }

        adaptadorNegocio = new NegocioAdapter(getApplicationContext(), negocios);
        listViewNegocios.setAdapter(adaptadorNegocio);
    }

    private void lanzarActivityProductos() {
        Intent intent = new Intent(this, Productos.class);
        startActivity(intent);
    }

    private void selectedNegocioItem() {
        listViewNegocios.setOnItemClickListener((adapterView, view, i, l) -> {
            GlobalInfo.NEGOCIO = negocios.get(i);
            GlobalInfo.NEGOCIOSELECCIONADO = true;
            lanzarActivityProductos();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void seleccionarOtroNegocio(View view) {
        createNewDialogCambiarNegocio().show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Dialog createNewDialogCambiarNegocio() {
        AlertDialog dlg = new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.cambiarNegocioPregunta)
                .setMessage(R.string.confirmacionCambiarNegocio)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    GlobalAction.reiniciarValores();
                    mostrarOcultarCambiarNegocio();
                    crearListaNegocios();
                })
                .setNegativeButton(R.string.cancelar, (dialog, id) -> {
                })
                .create();
        return dlg;
    }
}