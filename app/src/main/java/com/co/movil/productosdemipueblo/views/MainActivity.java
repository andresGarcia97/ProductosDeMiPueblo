package com.co.movil.productosdemipueblo.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.adapters.NegocioAdapter;
import com.co.movil.productosdemipueblo.clases.Negocio;
import com.co.movil.productosdemipueblo.clases.Producto;
import com.co.movil.productosdemipueblo.cliente.ClienteNegocioImpl;
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
    private Button buttonSincronizar;

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

    @Override
    protected void onRestart() {
        super.onRestart();
        mostrarOcultarCambiarNegocio();
        crearListaNegocios();
        selectedNegocioItem();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.texto_inicio));
        listViewNegocios = findViewById(R.id.listViewNegocios);
        buttonCambiarNegocio = findViewById(R.id.buttonCambiarNegocio);
        buttonSincronizar = findViewById(R.id.buttonSincronizar);
    }

    private void mostrarOcultarCambiarNegocio() {
        if (GlobalInfo.NEGOCIOSELECCIONADO) {
            buttonCambiarNegocio.setVisibility(View.VISIBLE);
            buttonSincronizar.setVisibility(View.GONE);
        } else {
            buttonCambiarNegocio.setVisibility(View.GONE);
            buttonSincronizar.setVisibility(View.VISIBLE);
        }
    }

    public void sincronizarCantidades(View view) {
        ClienteNegocioImpl cantidades = new ClienteNegocioImpl();
        cantidades.getCantidadesOfNegocio();
        if (GlobalInfo.SINCRONIZACION_EXITOSA) {
            Toast.makeText(MainActivity.this, R.string.SincronizacionExito, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, R.string.SincronizacionFail, Toast.LENGTH_SHORT).show();
        }
    }

    private void crearListaNegocios() {
        GlobalInfo.NEGOCIOS.clear();

        if (!GlobalInfo.NEGOCIOSELECCIONADO) {
            productos = new ArrayList<>();
            productos.add(new Producto(R.drawable.master_chief, "Master Chief", 1, "Spartans", 4000, 1));
            productos.add(new Producto(R.drawable.callofduty, "CoD", 1, "shooter en primera persona", 5000, 1));
            productos.add(new Producto(R.drawable.gearsofwar, "Lokus", 1, "Covenant", 2200, 1));
            productos.add(new Producto(R.drawable.among, "Impostor", 1, "supervivencia", 2000, 1));
            productos.add(new Producto(R.drawable.lastofus, "last of US", 1, "supervivencia", 3500, 1));

            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.among, "Among US, trust nobody", "Una nave espacial", "suministros y tareas", productos, "+573137528917"));
            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.callofduty, "Call of Duty, kill everybody", "Un mundo en guerra", "shooter en primera persona", productos, "+573007081275"));
            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.lastofus, "Last of US, survive", "Un mundo post apocaliptico", "supervivencia", productos, "+573007081275"));
            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.master_chief, "Halo, the best of Xbox", "viajes espaciales", "ciencia ficcion", productos, "+573137528917"));
            GlobalInfo.NEGOCIOS.add(new Negocio(R.drawable.gearsofwar, "Gears, Kill the Covenant", "guerras espaciales", "ciencia ficcion", productos, "+573137528917"));

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