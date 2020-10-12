package com.co.movil.productosdemipueblo.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.productosdemipueblo.R;
import com.co.movil.productosdemipueblo.clases.Cliente;
import com.co.movil.productosdemipueblo.entities.ClienteEntity;
import com.co.movil.productosdemipueblo.persistencia.DataBaseHelper;
import com.co.movil.productosdemipueblo.util.ActionBarUtil;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente);
        initComponents();
        consultarCliente();
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

        Cliente datos = new Cliente(obtener().obtenerTexto(editTextNombreCliente), obtener().obtenerTexto(editTextApellidosCliente),
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
            Toast.makeText(getApplicationContext(), R.string.datosValidos, Toast.LENGTH_SHORT).show();
            if (clienteEntidad != null && insertar) {
                new InsertCliente().execute(clienteEntidad);
            } else if (clienteEntidad != null) {
                new UpdateCliente().execute(clienteEntidad);
            }
        }
    }

    private class InsertCliente extends AsyncTask<ClienteEntity, Void, Void> {

        @Override
        protected Void doInBackground(ClienteEntity... clientes) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).transaccionesCliente().insert(clientes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), R.string.infoClienteGuardada, Toast.LENGTH_SHORT).show();
        }
    }

    private class UpdateCliente extends AsyncTask<ClienteEntity, Void, Void> {

        @Override
        protected Void doInBackground(ClienteEntity... clientes) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).transaccionesCliente().update(clientes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), R.string.infoClienteGuardada, Toast.LENGTH_SHORT).show();
        }
    }


}