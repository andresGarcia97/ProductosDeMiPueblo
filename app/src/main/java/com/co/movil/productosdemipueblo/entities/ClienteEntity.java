package com.co.movil.productosdemipueblo.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.co.movil.productosdemipueblo.persistencia.Tabla;

@Entity(tableName = Tabla.CLIENTES)
public class ClienteEntity {

    //siempre cero, solo se necesita una fila de la tabla
    @PrimaryKey()
    @ColumnInfo(name = "id")
    @NonNull
    private Integer id;

    @ColumnInfo(name = "idcliente")
    private String identificacion;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @ColumnInfo(name = "direccion")
    private String direccion;

    @ColumnInfo(name = "telefono")
    private String telefono;

    @NonNull
    public Integer getId() {
        return id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
