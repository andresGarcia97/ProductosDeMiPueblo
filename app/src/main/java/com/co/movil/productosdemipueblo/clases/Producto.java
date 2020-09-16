package com.co.movil.productosdemipueblo.clases;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Producto {

    private int imagen;
    private String nombre;
    private int cantidad;
    private String descripcion;
    private int precio;
}
