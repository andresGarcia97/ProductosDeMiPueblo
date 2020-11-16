package com.co.movil.productosdemipueblo.views;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.clases.Cliente;
import com.co.movil.productosdemipueblo.clases.Producto;
import com.co.movil.productosdemipueblo.entities.ClienteEntity;
import com.co.movil.productosdemipueblo.persistencia.DataBaseHelper;
import com.co.movil.productosdemipueblo.util.ActionBarUtil;
import com.co.movil.productosdemipueblo.util.GlobalAction;
import com.co.movil.productosdemipueblo.util.GlobalInfo;

import java.util.List;

import static com.co.movil.productosdemipueblo.util.UtilTexto.obtener;

public class DatosCliente extends AppCompatActivity {

    private EditText editTextNombreCliente;
    private EditText editTextApellidosCliente;
    private EditText editTextIdentificacionCliente;
    private EditText editTextDireccionCliente;
    private EditText editTextTelefonoCliente;
    private DataBaseHelper db;
    private ActionBarUtil actionBarUtil;
    private ClienteEntity clienteEntidad;
    protected Cliente clienteBuscado;
    private boolean insertar = false;
    Cliente datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente);
        initComponents();
        consultarCliente();
        verificarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        consultarCliente();
        verificarLista();
    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.infoPersonal));
        editTextNombreCliente = findViewById(R.id.editTextNombreCliente);
        editTextApellidosCliente = findViewById(R.id.editTextApellidosCliente);
        editTextIdentificacionCliente = findViewById(R.id.editTextIdentificacionCliente);
        editTextDireccionCliente = findViewById(R.id.editTextDireccionCliente);
        editTextTelefonoCliente = findViewById(R.id.editTextTelefonoCliente);
    }

    private void verificarLista() {
        if (GlobalInfo.PRODUCTOS.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.solicitudProductosVacia, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void consultarCliente() {
        List<ClienteEntity> clientes = db.transaccionesCliente().listar();
        if (clientes == null || clientes.isEmpty() || clientes.contains(null)) {
            insertar = true;
        } else {
            clienteEntidad = clientes.get(0);
            clienteBuscado = converterClienteEntityToCliente(clienteEntidad, clienteBuscado);
            cambiarDatosCliente(clienteBuscado);
        }
    }

    private Cliente converterClienteEntityToCliente(ClienteEntity entity, Cliente cliente) {
        if (cliente == null) {
            return new Cliente(entity.getNombre(), entity.getApellidos(), entity.getIdentificacion(), entity.getDireccion(), entity.getTelefono());
        } else {
            return null;
        }
    }

    private ClienteEntity converterClienteToClienteEntity(Cliente cliente) {
        if (cliente != null) {
            ClienteEntity entidad = new ClienteEntity();
            entidad.setId(0);
            entidad.setNombre(cliente.getNombre());
            entidad.setApellidos(cliente.getApellidos());
            entidad.setIdentificacion(cliente.getIdentificacion());
            entidad.setDireccion(cliente.getDireccion());
            entidad.setTelefono(cliente.getTelefono());
            return entidad;
        } else {
            return null;
        }
    }

    protected void cambiarDatosCliente(Cliente cliente) {
        if (cliente != null) {
            editTextNombreCliente.setText(cliente.getNombre());
            editTextApellidosCliente.setText(cliente.getApellidos());
            editTextIdentificacionCliente.setText(cliente.getIdentificacion());
            editTextDireccionCliente.setText(cliente.getDireccion());
            editTextTelefonoCliente.setText(cliente.getTelefono());
        }
    }

    public void enviarDatosPersonales(View view) {

        boolean envio = true;

        this.datos = new Cliente(obtener().obtenerTexto(editTextNombreCliente), obtener().obtenerTexto(editTextApellidosCliente),
                obtener().obtenerTexto(editTextIdentificacionCliente), obtener().obtenerTexto(editTextDireccionCliente),
                obtener().obtenerTexto(editTextTelefonoCliente));

        if (datos.getNombre().isEmpty()) {
            editTextNombreCliente.setError(getString(R.string.campoRequerido).concat(" el Nombre"));
            envio = false;
        }
        if (datos.getApellidos().isEmpty()) {
            editTextApellidosCliente.setError(getString(R.string.campoRequerido).concat(" un Apellido"));
            envio = false;
        }
        if (datos.getIdentificacion().isEmpty()) {
            editTextIdentificacionCliente.setError(getString(R.string.campoRequerido).concat(" la Identificación"));
            envio = false;
        }
        if (datos.getDireccion().isEmpty()) {
            editTextDireccionCliente.setError(getString(R.string.campoRequerido).concat(" la dirección"));
            envio = false;
        }
        if (datos.getTelefono().isEmpty()) {
            editTextTelefonoCliente.setError(getString(R.string.campoRequerido).concat(" el Telefono"));
            envio = false;
        }
        if (envio) {
            clienteEntidad = converterClienteToClienteEntity(datos);
            if (clienteEntidad != null) {
                boolean install = appInstallOrNot("com.whatsapp");
                if(install){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone="+GlobalInfo.NEGOCIO.getTelefono()+"&text="+messageWhatsApp()));
                    startActivity(intent);

                }else{
                    Toast.makeText(DatosCliente.this, "Wta no", Toast.LENGTH_SHORT).show();
                }
                new RepositoryCliente(insertar).execute(clienteEntidad);
                GlobalAction.reiniciarValores();
               // lanzarActivityMain();
                //finish();
            }
        }
    }

    private void lanzarActivityMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private class RepositoryCliente extends AsyncTask<ClienteEntity, Void, Void> {

        public boolean operacion;

        public RepositoryCliente(boolean operacion) {
            super();
            this.operacion = operacion;
        }

        @Override
        protected Void doInBackground(ClienteEntity... clientes) {
            if (operacion) {
                DataBaseHelper.getSimpleDB(getApplicationContext()).transaccionesCliente().insert(clientes[0]);
            } else {
                DataBaseHelper.getSimpleDB(getApplicationContext()).transaccionesCliente().update(clientes[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), R.string.datosValidos, Toast.LENGTH_SHORT).show();
        }
    }
    private boolean appInstallOrNot(String url){
        PackageManager packageManager =getPackageManager();
        boolean appInstall = false;
        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            appInstall = true;

        }catch (PackageManager.NameNotFoundException e){

        }
        return appInstall;
    }
    private String messageWhatsApp(){
        String products = getProductList();
        String cliente = getDataClient();
        return "*PRODUCTOS DE MI PUEBLO*\n"
                .concat("*Negocio:* ").concat(GlobalInfo.NEGOCIO.getNombre()).concat("\n\n")
                .concat(products).concat("\n")
                .concat(cliente);

    }
    private String getProductList(){
        String productos = "*_Lista productos:_*\n";
        int p=1;
        int total =0;
            for (Producto producto1: GlobalInfo.PRODUCTOS) {

                int totalProducto = producto1.getCantidad()*producto1.getPrecio();

                productos = productos
                        .concat("*Producto"+p+":* ").concat(producto1.getNombre()).concat("\n")
                        .concat("*Descripcion:* ").concat(producto1.getDescripcion()).concat("\n")
                        .concat("*Cantidad:* ").concat(String.valueOf(producto1.getCantidad())).concat("\n")
                        .concat("*Precio:* ").concat(String.valueOf(producto1.getPrecio())).concat("\n")
                        .concat("*Total producto:* ").concat(String.valueOf(totalProducto)).concat("\n")
                        .concat("*------------*").concat("\n");
                       total = total+ totalProducto;
                p++;
            }
        return productos.concat("*_Total pedido:_* ").concat(String.valueOf(total)).concat("\n");
    }
    private String getDataClient(){
        return "*_DATOS SOLICITANTE:_* \n"
                .concat("*Nombre:* ").concat(this.datos.getNombre()).concat("\n")
                .concat("*Apellidos:* ").concat(this.datos.getApellidos()).concat("\n")
                .concat("*Cédula:* ").concat(this.datos.getIdentificacion()).concat("\n")
                .concat("*Direccion:* ").concat(this.datos.getDireccion()).concat("\n")
                .concat("*Teléfono:* ").concat(this.datos.getTelefono()).concat("\n");
    }

}