package com.co.movil.productosdemipueblo.clases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private int imagen;
    private String nombre;
    private int cantidad;
    private String descripcion;
    private int precio;
    private int cantidadDisponible;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return imagen == producto.imagen &&
                precio == producto.precio &&
                nombre.equals(producto.nombre) &&
                descripcion.equals(producto.descripcion);
    }

}
