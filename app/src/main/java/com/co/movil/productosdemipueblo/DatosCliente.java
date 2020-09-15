package com.co.movil.productosdemipueblo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import static com.co.movil.productosdemipueblo.util.UtilTexto.obtener;

import com.co.movil.productosdemipueblo.clases.Cliente;

public class DatosCliente extends AppCompatActivity {

    private EditText editTextNombreCliente;
    private EditText editTextApellidosCliente;
    private EditText editTextIdentificacionCliente;
    private EditText editTextDireccionCliente;
    private EditText editTextTelefonoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente);
    }

    public void enviarDatosPersonales(View view) {
        editTextNombreCliente = findViewById(R.id.editTextNombreCliente);
        editTextApellidosCliente = findViewById(R.id.editTextApellidosCliente);
        editTextIdentificacionCliente = findViewById(R.id.editTextIdentificacionCliente);
        editTextDireccionCliente = findViewById(R.id.editTextDireccionCliente);
        editTextTelefonoCliente = findViewById(R.id.editTextTelefonoCliente);

        Cliente datos = new Cliente(obtener().obtenerTexto(editTextNombreCliente), obtener().obtenerTexto(editTextApellidosCliente),
                obtener().obtenerTexto(editTextIdentificacionCliente), obtener().obtenerTexto(editTextDireccionCliente),
                obtener().obtenerTexto(editTextTelefonoCliente));

        if (datos.getNombre().isEmpty()) {
            editTextNombreCliente.setError(getString(R.string.campoRequerido));
        }
        else if (datos.getApellidos().isEmpty()) {
            editTextApellidosCliente.setError(getString(R.string.campoRequerido));
        }
        else if (datos.getIdentificacion().isEmpty()) {
            editTextIdentificacionCliente.setError(getString(R.string.campoRequerido));
        }
        else if (datos.getDireccion().isEmpty()) {
            editTextDireccionCliente.setError(getString(R.string.campoRequerido));
        }
        else if(datos.getTelefono().isEmpty()){
            editTextTelefonoCliente.setError(getString(R.string.campoRequerido));
        }
        else {
            Toast.makeText(getApplicationContext(),R.string.datosValidos,Toast.LENGTH_SHORT).show();
        }
    }


}